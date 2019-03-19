package ado.sabgil.incheonariport.remote.openapi.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item")
public class PassengerDeparturesWItem {

    @Element
    private String airline;

    @Element
    private String flightId;

    @Element
    private String scheduleDateTime;

    @Element
    private String estimatedDateTime;

    @Element
    private String airport;

    @Element(name = "gatenumber")
    private String gateNumber;

    @Element
    private String airportCode;

    @Element(required = false)
    private String remark;

    @Element(name = "yoil", required = false)
    private String dayOfWeek;

    @Element(name = "chkinrange", required = false)
    private String checkInRange;

    @Element(name = "himidity", required = false)
    private String humidity;

    @Element(name = "wimage", required = false)
    private String weatherImage;

    @Element(required = false)
    private String wind;

    @Element(name = "maxtem", required = false)
    private String maxTemperature;

    @Element(name = "mintem", required = false)
    private String minTemperature;

    @Element(name = "terminalid")
    private String terminalId;

    public String getAirline() {
        return airline;
    }

    public String getFlightId() {
        return flightId;
    }

    public String getScheduleDateTime() {
        return scheduleDateTime;
    }

    public String getEstimatedDateTime() {
        return estimatedDateTime;
    }

    public String getAirport() {
        return airport;
    }

    public String getGateNumber() {
        return gateNumber;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public String getRemark() {
        return remark;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String getCheckInRange() {
        return checkInRange;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getWeatherImage() {
        return weatherImage;
    }

    public String getWind() {
        return wind;
    }

    public String getMaxTemperature() {
        return maxTemperature;
    }

    public String getMinTemperature() {
        return minTemperature;
    }

    public String getTerminalId() {
        return terminalId;
    }

    @Override
    public String toString() {
        return "PassengerDeparturesWItem{" +
                "airline='" + airline + '\'' +
                ", flightId='" + flightId + '\'' +
                ", scheduleDateTime='" + scheduleDateTime + '\'' +
                ", estimatedDateTime='" + estimatedDateTime + '\'' +
                ", airport='" + airport + '\'' +
                ", gateNumber='" + gateNumber + '\'' +
                ", airportCode='" + airportCode + '\'' +
                ", remark='" + remark + '\'' +
                ", dayOfWeek='" + dayOfWeek + '\'' +
                ", checkInRange='" + checkInRange + '\'' +
                ", humidity='" + humidity + '\'' +
                ", weatherImage='" + weatherImage + '\'' +
                ", wind='" + wind + '\'' +
                ", maxTemperature='" + maxTemperature + '\'' +
                ", minTemperature='" + minTemperature + '\'' +
                ", terminalId='" + terminalId + '\'' +
                '}';
    }

    public PassengerDeparturesWItem(String airline, String flightId, String scheduleDateTime, String estimatedDateTime, String airport, String gateNumber, String airportCode, String remark, String dayOfWeek, String checkInRange, String humidity, String weatherImage, String wind, String maxTemperature, String minTemperature, String terminalId) {
        this.airline = airline;
        this.flightId = flightId;
        this.scheduleDateTime = scheduleDateTime;
        this.estimatedDateTime = estimatedDateTime;
        this.airport = airport;
        this.gateNumber = gateNumber;
        this.airportCode = airportCode;
        this.remark = remark;
        this.dayOfWeek = dayOfWeek;
        this.checkInRange = checkInRange;
        this.humidity = humidity;
        this.weatherImage = weatherImage;
        this.wind = wind;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
        this.terminalId = terminalId;
    }
}
