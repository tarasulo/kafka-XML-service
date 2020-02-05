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
    private Marshaller marshaller;

    public RunProducer(KafkaServiceProducer kafkaServiceProducer, String producerTopicName) {
        this.kafkaServiceProducer = kafkaServiceProducer;
        this.producerTopicName = producerTopicName;
    }

    @Override
    public void run() {
        try {
            JAXBContext context = JAXBContext.newInstance(Customer.class);
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty("com.sun.xml.bind.xmlDeclaration", Boolean.FALSE);
        } catch (JAXBException e) {
            LOGGER.error(String.valueOf(e));
        }
        while (true) {
            if(Storage.getList().size() > 0) {
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
                    LOGGER.info("StringToXMLController send new xmlString record \n" + xmlString);
                    Storage.clear(customer);
                }
            }
        }
    }
}
