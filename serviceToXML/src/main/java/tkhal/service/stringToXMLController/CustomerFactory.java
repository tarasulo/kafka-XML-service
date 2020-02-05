package tkhal.service.stringToXMLController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerFactory.class);

    public static Customer create(String message) {
        Customer customer = new Customer();
        String[] value = message.split(",");
        if (value[0] != "?") customer.setAirline(value[0]);
        if (value[1] != "?") customer.setFlightNumber(value[1]);
        if (value[2] != "?") customer.setAcType(value[2]);
        if (value[3] != "?") customer.setValidTime(value[3]);
        if (value[4] != "?") customer.setCrossModelId(value[4]);
        if (value[5] != "?") customer.setTailNumber(value[5]);
        if (value[6] != "?") customer.setDepIcaoSiteId(value[6]);
        if (value[7] != "?") customer.setDepIcaoSiteId(value[7]);
        if (value[8] != "?") try {
            customer.setCopyNum(Integer.parseInt(value[8]));
        } catch (NumberFormatException e) {
            LOGGER.error("error in element copyNum" + e);
        }
        if (value[9] != "?") try {
            customer.setLat(Double.parseDouble(value[9]));
        } catch (NumberFormatException e) {
            LOGGER.error("Error in element lat" + e);
        }
        if (value[10] != "?") try {
            customer.setLon(Double.parseDouble(value[10]));
        } catch (NumberFormatException e) {
            LOGGER.error("Error in element lon" + e);
        }
        if (value[11] != "?") customer.setEventTime(value[11]);
        if (value[12] != "?") try {
            customer.setFlightLevel(Integer.parseInt(value[12]));
        } catch (NumberFormatException e) {
            LOGGER.error("error in element flightLevel" + e);
        }
        if (value[13] != "?") try {
            customer.setTemperature(Double.parseDouble(value[13]));
        } catch (NumberFormatException e) {
            LOGGER.error("Error in element temperature" + e);
        }
        if (value[14] != "?") try {
            customer.setWindDirection(Integer.parseInt(value[14]));
        } catch (NumberFormatException e) {
            LOGGER.error("error in element windDirection" + e);
        }
        if (value[15] != "?") try {
            customer.setWindSpeed(Integer.parseInt(value[15]));
        } catch (NumberFormatException e) {
            LOGGER.error("error in element windSpeed" + e);
        }
        if (value[16] != "?") customer.setIcingPlaceholder(value[16]);
        if (value[17] != "?") customer.setEncOutput(value[17]);
        if (value[18] != "?") try {
            customer.setEdrMean(Double.parseDouble(value[18]));
        } catch (NumberFormatException e) {
            LOGGER.error("Error in element edrMean" + e);
        }
        if (value[19] != "?") try {
            customer.setEdrPeak(Double.parseDouble(value[19]));
        } catch (NumberFormatException e) {
            LOGGER.error("Error in element edrPeak" + e);
        }
        if (value[20] != "?") try {
            customer.setWvEncoding(Integer.parseInt(value[20]));
        } catch (NumberFormatException e) {
            LOGGER.error("error in element wvEncoding" + e);
        }
        if (value[21] != "?") try {
            customer.setWvStatus(Integer.parseInt(value[21]));
        } catch (NumberFormatException e) {
            LOGGER.error("error in element wvStatus" + e);
        }
        if (value[22] != "?") try {
            customer.setRmsva(Double.parseDouble(value[22]));
        } catch (NumberFormatException e) {
            LOGGER.error("Error in element rmsva" + e);
        }
        if (customer.getLat() != null && customer.getLon() != null) {
            customer.setLocation();
        } else {
            LOGGER.error("Error in elements lat or lon");
        }
        return customer;
    }
}
