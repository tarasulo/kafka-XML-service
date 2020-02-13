package tkhal.service.receiver;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tkhal.service.KafkaServiceConsumer;

import java.time.Duration;

import static java.lang.System.getenv;

public class TestKafkaReceiver {
    private final Logger LOGGER = LoggerFactory.getLogger(TestKafkaReceiver.class);
    private String topicName;
    private KafkaServiceConsumer kafkaServiceConsumer;
    private KafkaConsumer<String, String> consumer;

    private TestKafkaReceiver() {
        this.kafkaServiceConsumer = new KafkaServiceConsumer();
        this.topicName = getenv("CONSUMER_TOPIC");
    }

    public static void main(String[] args) throws InterruptedException {
        TestKafkaReceiver testKafkaReceiver = new TestKafkaReceiver();
        testKafkaReceiver.run();
    }

    private void run() throws InterruptedException {
        consumer = kafkaServiceConsumer.startConsumer(topicName);
        while (true) {
            ConsumerRecords<String, String> messages = consumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> message : messages) {
                LOGGER.info("Received message: \n" + message.value());
                Thread.sleep(500L);
            }
        }

    }
}
