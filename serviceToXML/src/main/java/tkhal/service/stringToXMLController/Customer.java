package tkhal.service.stringToXMLController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Customer {
    private String airline;
    private String flightNumber;
    private String acType;
    private String validTime;
    private String crossModelId;
    private String tailNumber;
    private String depIcaoSiteId;
    private String destIcaoSiteId;
    private int copyNum;
    private Double lat;
    private Double lon;
    private String eventTime;
    private int flightLevel;
    private double temperature;
    private int windDirection;
    private int windSpeed;
    private String icingPlaceholder;
    private String encOutput;
    private double edrMean;
    private double edrPeak;
    private int wvEncoding;
    private int wvStatus;
    private double rmsva;
    private String location;
    private final Logger LOGGER = LoggerFactory.getLogger(StringToXMLController.class);

    public Customer(String message) {
        String[] value = message.split(",");
        if (value[0] != "?") setAirline(value[0]);
        if (value[1] != "?") setFlightNumber(value[1]);
        if (value[2] != "?") setAcType(value[2]);
        if (value[3] != "?") setValidTime(value[3]);
        if (value[4] != "?") setCrossModelId(value[4]);
        if (value[5] != "?") setTailNumber(value[5]);
        if (value[6] != "?") setDepIcaoSiteId(value[6]);
        if (value[7] != "?") setDepIcaoSiteId(value[7]);
        if (value[8] != "?") try {
            setCopyNum(Integer.parseInt(value[8]));
        } catch (NumberFormatException e) {
            LOGGER.error("error in element copyNum" + e);
        }
        if (value[9] != "?") try {
            setLat(Double.parseDouble(value[9]));
        } catch (NumberFormatException e) {
            LOGGER.error("Error in element lat" + e);
        }
        if (value[10] != "?") try {
            setLon(Double.parseDouble(value[10]));
        } catch (NumberFormatException e) {
            LOGGER.error("Error in element lon" + e);
        }
        if (value[11] != "?") setEventTime(value[11]);
        if (value[12] != "?") try {
            setFlightLevel(Integer.parseInt(value[12]));
        } catch (NumberFormatException e) {
            LOGGER.error("error in element flightLevel" + e);
        }
        if (value[13] != "?") try {
            setTemperature(Double.parseDouble(value[13]));
        } catch (NumberFormatException e) {
            LOGGER.error("Error in element temperature" + e);
        }
        if (value[14] != "?") try {
            setWindDirection(Integer.parseInt(value[14]));
        } catch (NumberFormatException e) {
            LOGGER.error("error in element windDirection" + e);
        }
        if (value[15] != "?") try {
            setWindSpeed(Integer.parseInt(value[15]));
        } catch (NumberFormatException e) {
            LOGGER.error("error in element windSpeed" + e);
        }
        if (value[16] != "?") setIcingPlaceholder(value[16]);
        if (value[17] != "?") setEncOutput(value[17]);
        if (value[18] != "?") try {
            setEdrMean(Double.parseDouble(value[18]));
        } catch (NumberFormatException e) {
            LOGGER.error("Error in element edrMean" + e);
        }
        if (value[19] != "?") try {
            setEdrPeak(Double.parseDouble(value[19]));
        } catch (NumberFormatException e) {
            LOGGER.error("Error in element edrPeak" + e);
        }
        if (value[20] != "?") try {
            setWvEncoding(Integer.parseInt(value[20]));
        } catch (NumberFormatException e) {
            LOGGER.error("error in element wvEncoding" + e);
        }
        if (value[21] != "?") try {
            setWvStatus(Integer.parseInt(value[21]));
        } catch (NumberFormatException e) {
            LOGGER.error("error in element wvStatus" + e);
        }
        if (value[22] != "?") try {
            setRmsva(Double.parseDouble(value[22]));
        } catch (NumberFormatException e) {
            LOGGER.error("Error in element rmsva" + e);
        }
        if (lat != null && lon != null) {
            setLocation();
        } else {
            LOGGER.error("Error in elements lat or lon");
        }

    }

    @XmlElement(name = "airline")
    public void setAirline(String airline) {
        this.airline = airline;
    }

    @XmlElement(name = "flight_number")
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    @XmlElement(name = "ac_type")
    public void setAcType(String acType) {
        this.acType = acType;
    }

    @XmlElement(name = "valid_time")
    public void setValidTime(String validTime) {
        this.validTime = validTime;
    }

    @XmlElement(name = "cross_model_id")
    public void setCrossModelId(String crossModelId) {
        this.crossModelId = crossModelId;
    }

    @XmlElement(name = "tail_number")
    public void setTailNumber(String tailNumber) {
        this.tailNumber = tailNumber;
    }

    @XmlElement(name = "dep_icao_site_id")
    public void setDepIcaoSiteId(String depIcaoSiteId) {
        this.depIcaoSiteId = depIcaoSiteId;
    }

    @XmlElement(name = "dest_icao_site_id")
    public void setDestIcaoSiteId(String destIcaoSiteId) {
        this.destIcaoSiteId = destIcaoSiteId;
    }

    @XmlElement(name = "copy_num")
    public void setCopyNum(int copyNum) {
        this.copyNum = copyNum;
    }

    @XmlElement(name = "lat")
    public void setLat(double lat) {
        this.lat = lat;
    }

    @XmlElement(name = "lon")
    public void setLon(double lon) {
        this.lon = lon;
    }

    @XmlElement(name = "event_time")
    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    @XmlElement(name = "flight_level")
    public void setFlightLevel(int flightLevel) {
        this.flightLevel = flightLevel;
    }

    @XmlElement(name = "temperature")
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @XmlElement(name = "temperature")
    public void setWindDirection(int windDirection) {
        this.windDirection = windDirection;
    }

    @XmlElement(name = "wind_speed")
    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    @XmlElement(name = "icing_placeholder")
    public void setIcingPlaceholder(String icingPlaceholder) {
        this.icingPlaceholder = icingPlaceholder;
    }

    @XmlElement(name = "enc_output")
    public void setEncOutput(String encOutput) {
        this.encOutput = encOutput;
    }

    @XmlElement(name = "edr_mean")
    public void setEdrMean(double edrMean) {
        this.edrMean = edrMean;
    }

    @XmlElement(name = "edr_peak")
    public void setEdrPeak(double edrPeak) {
        this.edrPeak = edrPeak;
    }

    @XmlElement(name = "wv_encoding")
    public void setWvEncoding(int wvEncoding) {
        this.wvEncoding = wvEncoding;
    }

    @XmlElement(name = "wv_status")
    public void setWvStatus(int wvStatus) {
        this.wvStatus = wvStatus;
    }

    @XmlElement(name = "rmsva")
    public void setRmsva(double rmsva) {
        this.rmsva = rmsva;
    }

    @XmlElement(name = "location")
    public void setLocation() {
        this.location = "POINT(" + lon + " " + lat + ")";
    }

}
