package tkhal.service.stringToXMLController;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.LinkedList;

public class CustomerFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerFactory.class);

    public static LinkedList<Customer> create(String message) {
        LinkedList<Customer> list = new LinkedList<>();
        String[] records = message.split("\n");
        for (String record : records) {
            String symbol = record.substring(record.length()-1);
            if (symbol.equals(",")) {
                record += "?";
            }
            String[] value = record.split(",");
            if (value[18].equals("\"")) {
                value[17] += ",\"";
                value = ArrayUtils.removeElement(value, value[18]);
            }
            if (value.length < 23) {
                LOGGER.error("Invalid message \n" + Arrays.toString(value));
            } else {
                Customer customer = new Customer();
                if (!(value[0].equals("?"))) customer.setAirline(value[0]);
                if (!(value[1].equals("?"))) customer.setFlightNumber(value[1]);
                if (!(value[2].equals("?"))) customer.setAcType(value[2]);
                if (!(value[3].equals("?"))) customer.setValidTime(value[3]);
                if (!(value[4].equals("?"))) customer.setCrossModelId(value[4]);
                if (!(value[5].equals("?"))) customer.setTailNumber(value[5]);
                if (!(value[6].equals("?"))) customer.setDepIcaoSiteId(value[6]);
                if (!(value[7].equals("?"))) customer.setDepIcaoSiteId(value[7]);
                if (!(value[8].equals("?") || value[8].length() == 0)) try {
                    customer.setCopyNum(Integer.parseInt(value[8]));
                } catch (NumberFormatException e) {
                    LOGGER.error("error in element copyNum" + e);
                }
                if (!(value[9].equals("?") || value[9].length() == 0)) try {
                    customer.setLat(Double.parseDouble(value[9]));
                } catch (NumberFormatException e) {
                    LOGGER.error("Error in element lat" + e);
                }
                if (!(value[10].equals("?") || value[10].length() == 0)) try {
                    customer.setLon(Double.parseDouble(value[10]));
                } catch (NumberFormatException e) {
                    LOGGER.error("Error in element lon" + e);
                }
                if (!(value[11].equals("?"))) customer.setEventTime(value[11]);
                if (!(value[12].equals("?") || value[12].length() == 0)) try {
                    customer.setFlightLevel(Integer.parseInt(value[12]));
                } catch (NumberFormatException e) {
                    LOGGER.error("error in element flightLevel" + e);
                }
                if (!(value[13].equals("?") || value[13].length() == 0)) try {
                    customer.setTemperature(Double.parseDouble(value[13]));
                } catch (NumberFormatException e) {
                    LOGGER.error("Error in element temperature" + e);
                }
                if (!(value[14].equals("?") || value[14].length() == 0)) try {
                    customer.setWindDirection(Integer.parseInt(value[14]));
                } catch (NumberFormatException e) {
                    LOGGER.error("error in element windDirection" + e);
                }
                if (!(value[15].equals("?") || value[15].length() == 0)) try {
                    customer.setWindSpeed(Integer.parseInt(value[15]));
                } catch (NumberFormatException e) {
                    LOGGER.error("error in element windSpeed" + e);
                }
                if (!(value[16].equals("?"))) customer.setIcingPlaceholder(value[16]);
                if (!(value[17].equals("?"))) customer.setEncOutput(value[17]);
                if (!(value[18].equals("?") || value[18].length() == 0)) try {
                    customer.setEdrMean(Double.parseDouble(value[18]));
                } catch (NumberFormatException e) {
                    LOGGER.error("Error in element edrMean" + e);
                }
                if (!(value[19].equals("?") || value[19].length() == 0)) try {
                    customer.setEdrPeak(Double.parseDouble(value[19]));
                } catch (NumberFormatException e) {
                    LOGGER.error("Error in element edrPeak" + e);
                }
                if (!(value[20].equals("?") || value[20].length() == 0)) try {
                    customer.setWvEncoding(Double.parseDouble(value[20]));
                } catch (NumberFormatException e) {
                    LOGGER.error("error in element wvEncoding" + e);
                }
                if (!(value[21].equals("?") || value[21].length() == 0)) try {
                    customer.setWvStatus(Double.parseDouble(value[21]));
                } catch (NumberFormatException e) {
                    LOGGER.error("error in element wvStatus" + e);
                }
                if (!(value[22].equals("?") || value[22].length() == 0)) try {
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
}
