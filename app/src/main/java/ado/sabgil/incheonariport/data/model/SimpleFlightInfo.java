package ado.sabgil.incheonariport.data.model;

import java.util.Objects;

import ado.sabgil.incheonariport.data.remote.openapi.response.FlightResponse;
import androidx.annotation.NonNull;

public class SimpleFlightInfo {

    @NonNull
    private final String airline;

    @NonNull
    private final String flightID;

    @NonNull
    private final String time;

    @NonNull
    private final String airport;

    @NonNull
    private final FlightResponse.Item remoteResponse;

    private SimpleFlightInfo(@NonNull String airline,
                             @NonNull String flightID,
                             @NonNull String time,
                             @NonNull String airport,
                             @NonNull FlightResponse.Item remoteResponse) {
        this.airline = airline;
        this.flightID = flightID;
        this.airport = airport;
        this.time = time;
        this.remoteResponse = remoteResponse;
    }

    @NonNull
    public String getAirline() {
        return airline;
    }

    @NonNull
    public String getFlightID() {
        return flightID;
    }

    @NonNull
    public String getTime() {
        return time;
    }

    @NonNull
    public String getAirport() {
        return airport;
    }

    @NonNull
    public FlightResponse.Item getRemoteResponse() {
        return remoteResponse;
    }

    public static SimpleFlightInfo from(FlightResponse.Item item) {
        StringBuilder stringBuilder = new StringBuilder(item.getScheduleDateTime());
        stringBuilder.insert(2, ":");

        return new SimpleFlightInfo(
                item.getAirline(),
                item.getFlightId(),
                stringBuilder.toString(),
                item.getAirport(),
                item);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleFlightInfo info = (SimpleFlightInfo) o;
        return Objects.equals(airline, info.airline) &&
                Objects.equals(flightID, info.flightID) &&
                Objects.equals(time, info.time) &&
                Objects.equals(airport, info.airport) &&
                Objects.equals(remoteResponse, info.remoteResponse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(airline, flightID, time, airport, remoteResponse);
    }
}
