package tkhal.service.stringToXMLController;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Producer;
import tkhal.service.KafkaServiceConsumer;
import tkhal.service.KafkaServiceProducer;

import static java.lang.System.getenv;

public class MultyThreadController {
        private KafkaServiceConsumer kafkaServiceConsumer;
        private KafkaServiceProducer kafkaServiceProducer;
        private static KafkaConsumer<String, String> consumer;
        private static Producer<String, String> producer;
        private String consumerTopicName;
        private String producerTopicName;

        private MultyThreadController() {
                this.kafkaServiceConsumer = new KafkaServiceConsumer();
                this.kafkaServiceProducer = new KafkaServiceProducer();
        }

        public static void main(String[] args) {
                MultyThreadController multyThreadController = new MultyThreadController();
                multyThreadController.run();
        }

        private void run() {
                consumerTopicName = getenv("CONSUMER_TOPIC");
                producerTopicName = getenv("PRODUCER_TOPIC");
                consumer = kafkaServiceConsumer.startConsumer(consumerTopicName);
                producer = kafkaServiceProducer.createProducer();
                RunConsumer runConsumer = new RunConsumer(consumer);
                runConsumer.start();
                Thread runProducer = new Thread(new RunProducer(kafkaServiceProducer, producerTopicName));
                runProducer.start();
        }
}
