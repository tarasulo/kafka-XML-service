package tkhal.service.stringToXMLController;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Producer;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import tkhal.service.KafkaServiceConsumer;
import tkhal.service.KafkaServiceProducer;

import java.time.Duration;

public class StrToXMLControllerTest {

    @Before
    public void initialZero() {
        KafkaServiceProducer kafkaServiceProducer = new KafkaServiceProducer();
        String topicName = "topic1";
        Producer<String, String> producer = kafkaServiceProducer.createProducer();
        String message = "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
        try {
            kafkaServiceProducer.send(message, topicName);
        } catch (Exception e) {}

        RunStrToXMLController runStrToXMLController = new RunStrToXMLController();
        runStrToXMLController.start();
    }

    @Test
    public void zeroTest(){
        String zero = "<root>\n" +
                "<taps_records>\n" +
                "    <copy_num>0</copy_num>\n" +
                "    <flight_level>0</flight_level>\n" +
                "    <temperature>0.0</temperature>\n" +
                "    <temperature>0</temperature>\n" +
                "    <wind_speed>0</wind_speed>\n" +
                "    <edr_mean>0.0</edr_mean>\n" +
                "    <edr_peak>0.0</edr_peak>\n" +
                "    <wv_encoding>0.0</wv_encoding>\n" +
                "    <wv_status>0.0</wv_status>\n" +
                "    <rmsva>0.0</rmsva>\n" +
                "</taps_records>\n" +
                "</root>";
        KafkaServiceConsumer kafkaServiceConsumer = new KafkaServiceConsumer();
        KafkaConsumer<String, String> consumer = kafkaServiceConsumer.startConsumer("topic2");
        ConsumerRecords<String, String> messages = consumer.poll(Duration.ofSeconds(1));
        for (ConsumerRecord<String, String> msg : messages) {
            Assertions.assertEquals(zero, msg);
        }
    }

    @Test
    public void validData() {
        String validValue = "<root>\n" +
                "<taps_records>\n" +
                "    <airline>UAL</airline>\n" +
                "    <flight_number>UA59</flight_number>\n" +
                "    <ac_type>B773</ac_type>\n" +
                "    <valid_time>20200129T154200Z</valid_time>\n" +
                "    <cross_model_id>SB0122BA</cross_model_id>\n" +
                "    <tail_number>N2332U</tail_number>\n" +
                "    <dep_icao_site_id>KSFO</dep_icao_site_id>\n" +
                "    <copy_num>876</copy_num>\n" +
                "    <lat>69.262</lat>\n" +
                "    <lon>-31.849</lon>\n" +
                "    <event_time>1542Z</event_time>\n" +
                "    <flight_level>329</flight_level>\n" +
                "    <temperature>-62.7</temperature>\n" +
                "    <temperature>206</temperature>\n" +
                "    <wind_speed>9</wind_speed>\n" +
                "    <icing_placeholder>'0'</icing_placeholder>\n" +
                "    <enc_output>10IH-</enc_output>\n" +
                "    <edr_mean>0.011</edr_mean>\n" +
                "    <edr_peak>0.015</edr_peak>\n" +
                "    <wv_encoding>0.0</wv_encoding>\n" +
                "    <wv_status>0.0</wv_status>\n" +
                "    <rmsva>0.008</rmsva>\n" +
                "    <location>POINT(-31.849 69.262)</location>\n" +
                "</taps_records>\n" +
                "</root>";
        KafkaServiceProducer kafkaServiceProducer = new KafkaServiceProducer();
        String topicName = "topic1";
        Producer<String, String> producer = kafkaServiceProducer.createProducer();
        String message = "UAL,UA59,B773,20200129T154200Z,SB0122BA,N2332U,EDDF,KSFO,876,69.262,-31.849,1542Z,329,-62.7,206,9,\"0\",\"10IH-\",0.011,0.015,0,0,0.008";
        try {
            kafkaServiceProducer.send(message, topicName);
        } catch (Exception e) {}

        RunStrToXMLController runStrToXMLController = new RunStrToXMLController();
        runStrToXMLController.start();

        KafkaServiceConsumer kafkaServiceConsumer = new KafkaServiceConsumer();
        KafkaConsumer<String, String> consumer = kafkaServiceConsumer.startConsumer("topic2");
        ConsumerRecords<String, String> messages = consumer.poll(Duration.ofSeconds(1));
        for (ConsumerRecord<String, String> msg : messages) {
            Assertions.assertEquals(validValue, msg);
        }
    }
}
