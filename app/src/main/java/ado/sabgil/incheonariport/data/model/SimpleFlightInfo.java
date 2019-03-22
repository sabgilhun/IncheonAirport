package ado.sabgil.incheonariport.data.model;

import android.text.TextUtils;

import java.util.Objects;

import ado.sabgil.incheonariport.R;
import ado.sabgil.incheonariport.data.remote.openapi.response.FlightResponse;
import androidx.annotation.NonNull;

public class SimpleFlightInfo {

    @NonNull
    private final String airline;

    @NonNull
    private final String flightID;

    private final boolean delayed;

    @NonNull
    private final String time;

    private final int airlineIcon;

    @NonNull
    private final FlightResponse.Item remoteResponse;

    @NonNull
    public String getAirline() {
        return airline;
    }

    @NonNull
    public String getFlightID() {
        return flightID;
    }

    public boolean isDelayed() {
        return delayed;
    }

    @NonNull
    public String getTime() {
        return time;
    }

    public int getAirlineIcon() {
        return airlineIcon;
    }

    @NonNull
    public FlightResponse.Item getRemoteResponse() {
        return remoteResponse;
    }

    private SimpleFlightInfo(@NonNull String airline,
                             @NonNull String flightID,
                             boolean delayed,
                             @NonNull String time,
                             int airlineIcon,
                             @NonNull FlightResponse.Item remoteResponse) {
        this.airline = airline;
        this.flightID = flightID;
        this.delayed = delayed;
        this.time = time;
        this.airlineIcon = airlineIcon;
        this.remoteResponse = remoteResponse;
    }

    public static SimpleFlightInfo from(FlightResponse.Item item) {
        boolean delayed = !TextUtils.equals(item.getScheduleDateTime(), item.getEstimatedDateTime());

        return new SimpleFlightInfo(
                item.getAirline(),
                item.getFlightId(),
                delayed,
                delayed ? item.getEstimatedDateTime() : item.getScheduleDateTime(),
                R.drawable.ic_photo,
                item);
    }

    @NonNull
    @Override
    public String toString() {
        return "SimpleFlightInfo{" +
                "airline='" + airline + '\'' +
                ", flightID='" + flightID + '\'' +
                ", delayed=" + delayed +
                ", time='" + time + '\'' +
                ", airlineIcon=" + airlineIcon +
                ", remoteResponse=" + remoteResponse +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleFlightInfo that = (SimpleFlightInfo) o;
        return delayed == that.delayed &&
                airlineIcon == that.airlineIcon &&
                Objects.equals(airline, that.airline) &&
                Objects.equals(flightID, that.flightID) &&
                Objects.equals(time, that.time) &&
                Objects.equals(remoteResponse, that.remoteResponse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(airline, flightID, delayed,
                time, airlineIcon, remoteResponse);
    }
}
