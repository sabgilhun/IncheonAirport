package ado.sabgil.incheonariport.remote.openapi.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item")
public class PassengerDeparturesItem {

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

    @Element(name = "chkinrange")
    private String checkInRange;

    @Element(name = "cityCode")
    private String cityCode;

    @Element(name = "gatenumber")
    private String gateNumber;

    @Element
    private String airportCode;

    @Element(name = "terminalId")
    private String terminalId;


    /**
     * 필수 항목이 아님
     **/
    @Element(name = "remark", required = false)
    private String state;

    @Element(name = "elapsetime", required = false)
    private String elapseTime;

    @Element(required = false)
    private String firstopover;

    @Element(required = false)
    private String firstopovername;

    @Element(required = false)
    private String secstopover;

    @Element(required = false)
    private String secstopovername;

    @Element(required = false)
    private String thistopover;

    @Element(required = false)
    private String thistopovername;

    @Element(required = false)
    private String foustopover;

    @Element(required = false)
    private String foustopovername;

    @Element(required = false)
    private String fifstopover;

    @Element(required = false)
    private String fifstopovername;

    @Element(required = false)
    private String sixstopover;

    @Element(required = false)
    private String sixstopovername;

    @Element(required = false)
    private String sevstopover;

    @Element(required = false)
    private String sevstopovername;

    @Element(required = false)
    private String eigstopover;

    @Element(required = false)
    private String eigstopovername;

    @Element(required = false)
    private String ninstopover;

    @Element(required = false)
    private String ninstopovername;

    @Element(required = false)
    private String tenstopover;

    @Element(required = false)
    private String tenstopovername;


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

    public String getCheckInRange() {
        return checkInRange;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getGateNumber() {
        return gateNumber;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public String getState() {
        return state;
    }

    public String getElapseTime() {
        return elapseTime;
    }

    @Override
    public String toString() {
        return "PassengerDeparturesItem{" +
                "airline='" + airline + '\'' +
                ", flightId='" + flightId + '\'' +
                ", scheduleDateTime='" + scheduleDateTime + '\'' +
                ", estimatedDateTime='" + estimatedDateTime + '\'' +
                ", airport='" + airport + '\'' +
                ", checkInRange='" + checkInRange + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", gateNumber='" + gateNumber + '\'' +
                ", airportCode='" + airportCode + '\'' +
                ", terminalId='" + terminalId + '\'' +
                ", state='" + state + '\'' +
                ", elapseTime='" + elapseTime + '\'' +
                '}';
    }
}