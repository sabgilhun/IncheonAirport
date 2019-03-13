package ado.sabgil.incheonariport.model;

import android.text.TextUtils;

import ado.sabgil.incheonariport.R;
import ado.sabgil.incheonariport.remote.openapi.response.PassengerDeparturesWItem;
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
    private final PassengerDeparturesWItem remoteResponse;

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
    public PassengerDeparturesWItem getRemoteResponse() {
        return remoteResponse;
    }

    private SimpleFlightInfo(@NonNull String airline,
                             @NonNull String flightID,
                             boolean delayed,
                             @NonNull String time,
                             int airlineIcon,
                             @NonNull PassengerDeparturesWItem remoteResponse) {
        this.airline = airline;
        this.flightID = flightID;
        this.delayed = delayed;
        this.time = time;
        this.airlineIcon = airlineIcon;
        this.remoteResponse = remoteResponse;
    }

    public static SimpleFlightInfo from(PassengerDeparturesWItem item) {
        boolean delayed = !TextUtils.equals(item.getScheduleDateTime(), item.getEstimatedDateTime());

        return new SimpleFlightInfo(
                item.getAirline(),
                item.getFlightId(),
                delayed,
                delayed ? item.getEstimatedDateTime() : item.getScheduleDateTime(),
                R.drawable.ic_photo,
                item);
    }

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
}
