package tkhal.service.stringToXMLController;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "taps_records")
@XmlAccessorType(XmlAccessType.FIELD)
public class Customer {
    @XmlElement(name = "airline")
    private String airline;
    @XmlElement(name = "flight_number")
    private String flightNumber;
    @XmlElement(name = "ac_type")
    private String acType;
    @XmlElement(name = "valid_time")
    private String validTime;
    @XmlElement(name = "cross_model_id")
    private String crossModelId;
    @XmlElement(name = "tail_number")
    private String tailNumber;
    @XmlElement(name = "dep_icao_site_id")
    private String depIcaoSiteId;
    @XmlElement(name = "dest_icao_site_id")
    private String destIcaoSiteId;
    @XmlElement(name = "copy_num")
    private int copyNum;
    @XmlElement(name = "lat")
    private Double lat;
    @XmlElement(name = "lon")
    private Double lon;
    @XmlElement(name = "event_time")
    private String eventTime;
    @XmlElement(name = "flight_level")
    private int flightLevel;
    @XmlElement(name = "temperature")
    private double temperature;
    @XmlElement(name = "temperature")
    private int windDirection;
    @XmlElement(name = "wind_speed")
    private int windSpeed;
    @XmlElement(name = "icing_placeholder")
    private String icingPlaceholder;
    @XmlElement(name = "enc_output")
    private String encOutput;
    @XmlElement(name = "edr_mean")
    private double edrMean;
    @XmlElement(name = "edr_peak")
    private double edrPeak;
    @XmlElement(name = "wv_encoding")
    private double wvEncoding;
    @XmlElement(name = "wv_status")
    private double wvStatus;
    @XmlElement(name = "rmsva")
    private double rmsva;
    @XmlElement(name = "location")
    private String location;

    public Customer() {
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setAcType(String acType) {
        this.acType = acType;
    }

    public void setValidTime(String validTime) {
        this.validTime = validTime;
    }

    public void setCrossModelId(String crossModelId) {
        this.crossModelId = crossModelId;
    }

    public void setTailNumber(String tailNumber) {
        this.tailNumber = tailNumber;
    }

    public void setDepIcaoSiteId(String depIcaoSiteId) {
        this.depIcaoSiteId = depIcaoSiteId;
    }

    public void setCopyNum(int copyNum) {
        this.copyNum = copyNum;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public void setFlightLevel(int flightLevel) {
        this.flightLevel = flightLevel;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setWindDirection(int windDirection) {
        this.windDirection = windDirection;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setIcingPlaceholder(String icingPlaceholder) {
        this.icingPlaceholder = icingPlaceholder;
    }

    public void setEncOutput(String encOutput) {
        this.encOutput = encOutput;
    }

    public void setEdrMean(double edrMean) {
        this.edrMean = edrMean;
    }

    public void setEdrPeak(double edrPeak) {
        this.edrPeak = edrPeak;
    }

    public void setWvEncoding(double wvEncoding) {
        this.wvEncoding = wvEncoding;
    }

    public void setWvStatus(double wvStatus) {
        this.wvStatus = wvStatus;
    }

    public void setRmsva(double rmsva) {
        this.rmsva = rmsva;
    }

    public void setLocation() {
        this.location = "POINT(" + lon + " " + lat + ")";
    }

    public String getAirline() {
        return airline;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getAcType() {
        return acType;
    }

    public String getValidTime() {
        return validTime;
    }

    public String getCrossModelId() {
        return crossModelId;
    }

    public String getTailNumber() {
        return tailNumber;
    }

    public String getDepIcaoSiteId() {
        return depIcaoSiteId;
    }

    public String getDestIcaoSiteId() {
        return destIcaoSiteId;
    }

    public int getCopyNum() {
        return copyNum;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLon() {
        return lon;
    }

    public String getEventTime() {
        return eventTime;
    }

    public int getFlightLevel() {
        return flightLevel;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getWindDirection() {
        return windDirection;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public String getIcingPlaceholder() {
        return icingPlaceholder;
    }

    public String getEncOutput() {
        return encOutput;
    }

    public double getEdrMean() {
        return edrMean;
    }

    public double getEdrPeak() {
        return edrPeak;
    }

    public double getWvEncoding() {
        return wvEncoding;
    }

    public double getWvStatus() {
        return wvStatus;
    }

    public double getRmsva() {
        return rmsva;
    }

    public String getLocation() {
        return location;
    }
}
