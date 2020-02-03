package tkhal.service.stringProducer;

import org.apache.kafka.clients.producer.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tkhal.service.KafkaServiceProducer;

public class StringKafkaProducer {
    private final Logger LOGGER = LoggerFactory.getLogger(StringKafkaProducer.class);
    private String topicName;
    private KafkaServiceProducer kafkaServiceProducer;
    private Producer<String, String> producer;

    public StringKafkaProducer(String topicName) {
        this.kafkaServiceProducer = new KafkaServiceProducer();
        this.topicName = topicName;
    }

    public static void main(String[] args) {
        StringKafkaProducer stringKafkaProducer = new StringKafkaProducer("topic1");
        stringKafkaProducer.run();
    }

    private void run() {
        producer = kafkaServiceProducer.createProducer();
        String message = "UAL,UA59,B773,20200129T154200Z,SB0122BA,N2332U,EDDF,KSFO,876,69.262,-31.849,1542Z,329,-62.7,206,9,\"0\",\"10IH-\",0.011,0.015,0,0,0.006";
        try {
            kafkaServiceProducer.send(message, topicName);
            LOGGER.info(message);
        } catch (Exception e) {
            LOGGER.error("Test String Failed " + e);
        }
    }
}
