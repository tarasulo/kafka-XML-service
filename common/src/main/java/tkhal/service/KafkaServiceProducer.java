package tkhal.service;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

import static java.lang.System.getenv;

public class KafkaServiceProducer {
    private final static Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);
    private Producer<String, String> producer;

    public KafkaServiceProducer() {
    }

    public Producer<String, String> createProducer() {
        Properties propsRedirect = new Properties();
        propsRedirect.put("bootstrap.servers", getenv("HOST"));
        propsRedirect.put("key.serializer", getenv("KEY_SERIALIZER"));
        propsRedirect.put("value.serializer", getenv("VALUE_SERIALIZER"));
        // creating new Kafka producer
        producer = new KafkaProducer<>(propsRedirect);
        return producer;
    }

    public void send(String record, String topicName) {
        /**
         * This is the method which sending message
         * to the topic
         */
        try {
            producer.send(new ProducerRecord<>(topicName, record));
            Thread.sleep(1000L);
        } catch (Exception e) {
            LOGGER.error("Resend failed " + e);
        }
    }
}

