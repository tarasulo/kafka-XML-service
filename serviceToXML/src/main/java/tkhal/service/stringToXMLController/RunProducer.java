package tkhal.service.stringToXMLController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tkhal.service.KafkaServiceProducer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.LinkedList;

public class RunProducer implements Runnable{

    private KafkaServiceProducer kafkaServiceProducer;
    private final Logger LOGGER = LoggerFactory.getLogger(StringToXMLController.class);
    private String producerTopicName;
    private String xmlString;

    public RunProducer(KafkaServiceProducer kafkaServiceProducer, String producerTopicName) {
        this.kafkaServiceProducer = kafkaServiceProducer;
        this.producerTopicName = producerTopicName;
    }

    @Override
    public void run() {
        while (true) {
            if(Storage.getList().size() > 0) {
                LinkedList<Customer> list = Storage.getList();
                for (Customer customer: list) {
                    try {
                        JAXBContext context = JAXBContext.newInstance(Customer.class);
                        Marshaller m = context.createMarshaller();

                        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML

                        StringWriter sw = new StringWriter();
                        m.marshal(customer, sw);
                        xmlString = sw.toString();

                    } catch (JAXBException e) {
                        LOGGER.error(String.valueOf(e));
                    }
                    kafkaServiceProducer.send(xmlString, producerTopicName);
                    LOGGER.info("StringToXMLController send new xmlString record" + xmlString);
                    Storage.clear(customer);
                }
            }
        }
    }
}
