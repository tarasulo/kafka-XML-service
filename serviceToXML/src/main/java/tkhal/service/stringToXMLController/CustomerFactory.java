package tkhal.service.stringToXMLController;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.LinkedList;

public class CustomerFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerFactory.class);
    private static final String BRACE = "\"";

    public static LinkedList<Customer> create(String message) {
        LinkedList<Customer> list = new LinkedList<>();
        String[] records = message.split("\n");
        for (String record : records) {
            String symbol = record.substring(record.length() - 1);
            if (symbol.equals(",")) {
                record += "?";
            }
            String[] value = validData(record);
            if (value.length < 23) {
                LOGGER.error("Invalid message \n" + Arrays.toString(value));
            } else {
                Customer customer = new Customer();
                if (empty(value[0])) customer.setAirline(value[0]);
                if (empty(value[1])) customer.setFlightNumber(value[1]);
                if (empty(value[2])) customer.setAcType(value[2]);
                if (empty(value[3])) customer.setValidTime(value[3]);
                if (empty(value[4])) customer.setCrossModelId(value[4]);
                if (empty(value[5])) customer.setTailNumber(value[5]);
                if (empty(value[6])) customer.setDepIcaoSiteId(value[6]);
                if (empty(value[7])) customer.setDepIcaoSiteId(value[7]);
                if (empty(value[8])) try {
                    customer.setCopyNum(Integer.parseInt(value[8]));
                } catch (NumberFormatException e) {
                    LOGGER.error("error in element copyNum" + e);
                }
                if (empty(value[9])) try {
                    customer.setLat(Double.parseDouble(value[9]));
                } catch (NumberFormatException e) {
                    LOGGER.error("Error in element lat" + e);
                }
                if (empty(value[10])) try {
                    customer.setLon(Double.parseDouble(value[10]));
                } catch (NumberFormatException e) {
                    LOGGER.error("Error in element lon" + e);
                }
                if (empty(value[11])) customer.setEventTime(value[11]);
                if (empty(value[12])) try {
                    customer.setFlightLevel(Integer.parseInt(value[12]));
                } catch (NumberFormatException e) {
                    LOGGER.error("error in element flightLevel" + e);
                }
                if (empty(value[13])) try {
                    customer.setTemperature(Double.parseDouble(value[13]));
                } catch (NumberFormatException e) {
                    LOGGER.error("Error in element temperature" + e);
                }
                if (empty(value[14])) try {
                    customer.setWindDirection(Integer.parseInt(value[14]));
                } catch (NumberFormatException e) {
                    LOGGER.error("error in element windDirection" + e);
                }
                if (empty(value[15])) try {
                    customer.setWindSpeed(Integer.parseInt(value[15]));
                } catch (NumberFormatException e) {
                    LOGGER.error("error in element windSpeed" + e);
                }
                if (empty(value[16])) customer.setIcingPlaceholder(value[16]);
                if (empty(value[17])) customer.setEncOutput(value[17]);
                if (empty(value[18])) try {
                    customer.setEdrMean(Double.parseDouble(value[18]));
                } catch (NumberFormatException e) {
                    LOGGER.error("Error in element edrMean" + e);
                }
                if (empty(value[19])) try {
                    customer.setEdrPeak(Double.parseDouble(value[19]));
                } catch (NumberFormatException e) {
                    LOGGER.error("Error in element edrPeak" + e);
                }
                if (empty(value[20])) try {
                    customer.setWvEncoding(Double.parseDouble(value[20]));
                } catch (NumberFormatException e) {
                    LOGGER.error("error in element wvEncoding" + e);
                }
                if (empty(value[21])) try {
                    customer.setWvStatus(Double.parseDouble(value[21]));
                } catch (NumberFormatException e) {
                    LOGGER.error("error in element wvStatus" + e);
                }
                if (empty(value[22])) try {
                    customer.setRmsva(Double.parseDouble(value[22]));
                } catch (NumberFormatException e) {
                    LOGGER.error("Error in element rmsva" + e);
                }
                if (customer.getLat() == null || customer.getLon() == null) {
                    LOGGER.error("Error in elements lat or lon");
                } else {
                    customer.setLocation();
                }
                list.add(customer);
            }
        }
        return list;
    }

    private static String[] validData(String record) {
        String[] value = record.split(",");
        for (int i = 0; i < value.length; i++) {
            if (value[i].length() > 0) {
                if (value[i].substring(0, 1).equals(BRACE)) {
                    String lastChar = value[i].substring(value[i].length() - 1);
                    if (!lastChar.equals(BRACE)) {
                        value[i] += "," + value[i + 1];
                        value = ArrayUtils.removeElement(value, value[i + 1]);
                    }
                }
            }
        }
        return value;
    }

    private static boolean empty(String rec) {
        if (rec.equals("?") || rec.length() == 0) return false;
        return true;
    }
}
