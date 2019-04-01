package ado.sabgil.incheonariport.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import java.util.Objects;

import ado.sabgil.incheonariport.data.remote.openapi.response.FlightResponse;
import ado.sabgil.incheonariport.util.TimeUtils;
import androidx.annotation.NonNull;

public class FlightInformation implements Parcelable {

    @NonNull
    private final String airline;

    @NonNull
    private final String flightId;

    @NonNull
    private final String takeOffTime;

    @NonNull
    private final String takeOffDate;

    @NonNull
    private final String destinationAirport;

    @NonNull
    private final String destinationAirportCode;

    @NonNull
    private final String boardingGate;

    @NonNull
    private final String checkInDesk;

    @NonNull
    private final String state;

    @NonNull
    private final String terminal;

    protected FlightInformation(Parcel in) {
        airline = Objects.requireNonNull(in.readString());
        flightId = Objects.requireNonNull(in.readString());
        takeOffTime = Objects.requireNonNull(in.readString());
        takeOffDate = Objects.requireNonNull(in.readString());
        destinationAirport = Objects.requireNonNull(in.readString());
        destinationAirportCode = Objects.requireNonNull(in.readString());
        boardingGate = Objects.requireNonNull(in.readString());
        checkInDesk = Objects.requireNonNull(in.readString());
        state = Objects.requireNonNull(in.readString());
        terminal = Objects.requireNonNull(in.readString());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(airline);
        dest.writeString(flightId);
        dest.writeString(takeOffTime);
        dest.writeString(takeOffDate);
        dest.writeString(destinationAirport);
        dest.writeString(destinationAirportCode);
        dest.writeString(boardingGate);
        dest.writeString(checkInDesk);
        dest.writeString(state);
        dest.writeString(terminal);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FlightInformation> CREATOR = new Creator<FlightInformation>() {
        @Override
        public FlightInformation createFromParcel(Parcel in) {
            return new FlightInformation(in);
        }

        @Override
        public FlightInformation[] newArray(int size) {
            return new FlightInformation[size];
        }
    };

    @NonNull
    public String getAirline() {
        return airline;
    }

    @NonNull
    public String getFlightId() {
        return flightId;
    }

    @NonNull
    public String getTakeOffTime() {
        return takeOffTime;
    }

    @NonNull
    public String getTakeOffDate() {
        return takeOffDate;
    }

    @NonNull
    public String getDestinationAirport() {
        return destinationAirport;
    }

    @NonNull
    public String getDestinationAirportCode() {
        return destinationAirportCode;
    }

    @NonNull
    public String getBoardingGate() {
        return boardingGate;
    }

    @NonNull
    public String getCheckInDesk() {
        return checkInDesk;
    }

    @NonNull
    public String getState() {
        return state;
    }

    @NonNull
    public String getTerminal() {
        return terminal;
    }

    private FlightInformation(@NonNull String airline,
                              @NonNull String flightId,
                              @NonNull String takeOffTime,
                              @NonNull String takeOffDate,
                              @NonNull String destinationAirport,
                              @NonNull String destinationAirportCode,
                              @NonNull String boardingGate,
                              @NonNull String checkInDesk,
                              @NonNull String state,
                              @NonNull String terminal) {
        this.airline = airline;
        this.flightId = flightId;
        this.takeOffTime = takeOffTime;
        this.takeOffDate = takeOffDate;
        this.destinationAirport = destinationAirport;
        this.destinationAirportCode = destinationAirportCode;
        this.boardingGate = boardingGate;
        this.checkInDesk = checkInDesk;
        this.state = state;
        this.terminal = terminal;
    }

    public static FlightInformation from(FlightResponse.Item remote) {
        StringBuilder stringBuilder = new StringBuilder(remote.getScheduleDateTime());
        stringBuilder.insert(2, ":");

        return new FlightInformation(
                remote.getAirline(),
                remote.getFlightId(),
                stringBuilder.toString(),
                TimeUtils.getCurrentDate(),
                remote.getAirport(),
                remote.getAirportCode(),
                remote.getGateNumber(),
                remote.getCheckInRange(),
                remote.getRemark() != null ? remote.getRemark() : "",
                TextUtils.equals(remote.getTerminalId(), "P03") ? "제 2여객터미널" : "제 1여객터미널");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightInformation that = (FlightInformation) o;
        return Objects.equals(airline, that.airline) &&
                Objects.equals(flightId, that.flightId) &&
                Objects.equals(takeOffTime, that.takeOffTime) &&
                Objects.equals(takeOffDate, that.takeOffDate) &&
                Objects.equals(destinationAirport, that.destinationAirport) &&
                Objects.equals(destinationAirportCode, that.destinationAirportCode) &&
                Objects.equals(boardingGate, that.boardingGate) &&
                Objects.equals(checkInDesk, that.checkInDesk) &&
                Objects.equals(state, that.state) &&
                Objects.equals(terminal, that.terminal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(airline, flightId, takeOffTime, takeOffDate,
                destinationAirport, destinationAirportCode, boardingGate,
                checkInDesk, state, terminal);
    }
}
