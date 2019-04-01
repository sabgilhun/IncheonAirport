package ado.sabgil.incheonariport.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

import ado.sabgil.incheonariport.data.remote.openapi.response.FlightResponse;
import androidx.annotation.NonNull;

public class FlightInfo implements Parcelable {

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

    public static FlightInfo from(FlightResponse.Item item) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightInfo that = (FlightInfo) o;
        return Objects.equals(airline, that.airline) &&
                Objects.equals(flightID, that.flightID) &&
                Objects.equals(scheduledTime, that.scheduledTime) &&
                Objects.equals(estimatedTime, that.estimatedTime) &&
                Objects.equals(airport, that.airport) &&
                Objects.equals(airportCode, that.airportCode) &&
                Objects.equals(gateNumber, that.gateNumber) &&
                Objects.equals(checkInDesk, that.checkInDesk) &&
                Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(airline, flightID, scheduledTime,
                estimatedTime, airport, airportCode,
                gateNumber, checkInDesk, state);
    }

    protected FlightInfo(Parcel in) {
        airline = Objects.requireNonNull(in.readString());
        flightID = Objects.requireNonNull(in.readString());
        scheduledTime = Objects.requireNonNull(in.readString());
        estimatedTime = Objects.requireNonNull(in.readString());
        airport = Objects.requireNonNull(in.readString());
        airportCode = Objects.requireNonNull(in.readString());
        gateNumber = Objects.requireNonNull(in.readString());
        checkInDesk = Objects.requireNonNull(in.readString());
        state = Objects.requireNonNull(in.readString());
    }

    public static final Creator<FlightInfo> CREATOR = new Creator<FlightInfo>() {
        @Override
        public FlightInfo createFromParcel(Parcel in) {
            return new FlightInfo(in);
        }

        @Override
        public FlightInfo[] newArray(int size) {
            return new FlightInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(airline);
        dest.writeString(flightID);
        dest.writeString(scheduledTime);
        dest.writeString(estimatedTime);
        dest.writeString(airport);
        dest.writeString(airportCode);
        dest.writeString(gateNumber);
        dest.writeString(checkInDesk);
        dest.writeString(state);
    }
}
