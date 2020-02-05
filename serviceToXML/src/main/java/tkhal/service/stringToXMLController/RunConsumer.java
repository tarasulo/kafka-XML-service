package tkhal.service.stringToXMLController;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;

public class RunConsumer extends Thread {
    private static KafkaConsumer<String, String> consumer;
    private Customer customer;

    public RunConsumer(KafkaConsumer<String, String> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void run() {
        while (true) {
            ConsumerRecords<String, String> messages = consumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> message : messages) {
                customer = CustomerFactory.create(message.value());
                Storage.setList(customer);
            }
        }
    }
}
