package tkhal.service.stringToXMLController;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tkhal.service.KafkaServiceConsumer;
import tkhal.service.KafkaServiceProducer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.time.Duration;
import java.util.LinkedList;

import static java.lang.System.getenv;

public class TestController {
    private KafkaServiceConsumer kafkaServiceConsumer;
    private KafkaServiceProducer kafkaServiceProducer;
    private static KafkaConsumer<String, String> consumer;
    private static Producer<String, String> producer;
    private String consumerTopicName;
    private String producerTopicName;
    private Customer customer;
    private Marshaller marshaller;

    private final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
    private String xmlString;

    private TestController() {
        this.kafkaServiceConsumer = new KafkaServiceConsumer();
        this.kafkaServiceProducer = new KafkaServiceProducer();
    }

    public static void main(String[] args) {
        TestController testController = new TestController();
        testController.run();
    }

    private void run() {
        consumerTopicName = getenv("CONSUMER_TOPIC");
        producerTopicName = getenv("PRODUCER_TOPIC");
        consumer = kafkaServiceConsumer.startConsumer(consumerTopicName);
        producer = kafkaServiceProducer.createProducer();
        try {
            JAXBContext context = JAXBContext.newInstance(Customer.class);
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty("com.sun.xml.bind.xmlDeclaration", Boolean.FALSE);
        } catch (JAXBException e) {
            LOGGER.error(String.valueOf(e));
        }

        while (true) {
            ConsumerRecords<String, String> messages = consumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> message : messages) {
                customer = CustomerFactory.create(message.value());
                Storage.setList(customer);
            }
            if (Storage.getList().size() > 0) {
                LinkedList<Customer> list = Storage.getList();
                for (Customer customer : list) {
                    StringWriter sw = new StringWriter();
                    sw.write("<root>\n");
                    try {
                        marshaller.marshal(customer, sw);
                    } catch (JAXBException e) {
                        LOGGER.error(String.valueOf(e));
                    }
                    sw.write("\n</root>");
                    xmlString = sw.toString();
                    kafkaServiceProducer.send(xmlString, producerTopicName);
                    LOGGER.info("StringToXMLController send new xmlString record" + xmlString);
                    Storage.clear(customer);
                }
            }
        }
    }
}
