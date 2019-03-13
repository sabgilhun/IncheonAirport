package ado.sabgil.incheonariport.model;

import ado.sabgil.incheonariport.remote.openapi.response.PassengerDeparturesWItem;
import androidx.annotation.NonNull;

public class FlightInfo {

    @NonNull
    private final String airline;

    @NonNull
    private final String flightID;

    @NonNull
    private final String scheduledTime;

    @NonNull
    private final String estimatedTime;

    @NonNull
    private final String airport;

    @NonNull
    private final String airportCode;

    @NonNull
    private final String gateNumber;

    @NonNull
    private final String checkInDesk;

    @NonNull
    private final String state;


    @NonNull
    public String getAirline() {
        return airline;
    }

    @NonNull
    public String getFlightID() {
        return flightID;
    }

    @NonNull
    public String getScheduledTime() {
        return scheduledTime;
    }

    @NonNull
    public String getEstimatedTime() {
        return estimatedTime;
    }

    @NonNull
    public String getAirport() {
        return airport;
    }

    @NonNull
    public String getAirportCode() {
        return airportCode;
    }

    @NonNull
    public String getGateNumber() {
        return gateNumber;
    }

    @NonNull
    public String getCheckInDesk() {
        return checkInDesk;
    }

    @NonNull
    public String getState() {
        return state;
    }

    private FlightInfo(@NonNull String airline,
                       @NonNull String flightID,
                       @NonNull String scheduledTime,
                       @NonNull String estimatedTime,
                       @NonNull String airport,
                       @NonNull String airportCode,
                       @NonNull String gateNumber,
                       @NonNull String checkInDesk,
                       @NonNull String state) {
        this.airline = airline;
        this.flightID = flightID;
        this.scheduledTime = scheduledTime;
        this.estimatedTime = estimatedTime;
        this.airport = airport;
        this.airportCode = airportCode;
        this.gateNumber = gateNumber;
        this.checkInDesk = checkInDesk;
        this.state = state;
    }

    public static FlightInfo from(PassengerDeparturesWItem item) {
        return new FlightInfo(
                item.getAirline(),
                item.getFlightId(),
                item.getScheduleDateTime(),
                item.getEstimatedDateTime(),
                item.getAirport(),
                item.getAirportCode(),
                item.getGateNumber(),
                item.getCheckInRange(),
                item.getRemark() != null ? item.getRemark() : "");
    }

    @Override
    public String toString() {
        return "FlightInfo{" +
                "airline='" + airline + '\'' +
                ", flightID='" + flightID + '\'' +
                ", scheduledTime='" + scheduledTime + '\'' +
                ", estimatedTime='" + estimatedTime + '\'' +
                ", airport='" + airport + '\'' +
                ", airportCode='" + airportCode + '\'' +
                ", gateNumber='" + gateNumber + '\'' +
                ", checkInDesk='" + checkInDesk + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
