package ado.sabgil.incheonariport.model;

import java.util.Objects;

import ado.sabgil.incheonariport.remote.openapi.response.DeparturesCongestionItem;
import androidx.annotation.NonNull;

public class DepartureCongestion {

    public enum State {
        LIGHT("원활"), NORMAL("보통"), BUSY("혼잡"), EMPTY1("오류"), EMPTY2("오류"),
        EMPTY3("오류"), EMPTY4("오류"), EMPTY5("오류"), EMPTY6("오류"), CLOSE("운영 종료");

        final private String name;

        State(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    @NonNull
    private final String updateDate;

    @NonNull
    private final String updateTime;

    @NonNull
    private final State congestion2;

    @NonNull
    private final State congestion3;

    @NonNull
    private final State congestion4;

    @NonNull
    private final State congestion5;

    private final int passengers2;

    private final int passengers3;

    private final int passengers4;

    private final int passengers5;

    @NonNull
    public String getUpdateDate() {
        return updateDate;
    }

    @NonNull
    public String getUpdateTime() {
        return updateTime;
    }

    @NonNull
    public State getCongestion2() {
        return congestion2;
    }

    @NonNull
    public State getCongestion3() {
        return congestion3;
    }

    @NonNull
    public State getCongestion4() {
        return congestion4;
    }

    @NonNull
    public State getCongestion5() {
        return congestion5;
    }

    public int getPassengers2() {
        return passengers2;
    }

    public int getPassengers3() {
        return passengers3;
    }

    public int getPassengers4() {
        return passengers4;
    }

    public int getPassengers5() {
        return passengers5;
    }

    private DepartureCongestion(@NonNull String updateDate,
                                @NonNull String updateTime,
                                int gate2Congestion,
                                int gate3Congestion,
                                int gate4Congestion,
                                int gate5Congestion,
                                int gate2NumberOfPassenger,
                                int gate3NumberOfPassenger,
                                int gate4NumberOfPassenger,
                                int gate5NumberOfPassenger) {
        this.updateDate = updateDate;
        this.updateTime = updateTime;
        this.congestion2 = State.values()[gate2Congestion];
        this.congestion3 = State.values()[gate3Congestion];
        this.congestion4 = State.values()[gate4Congestion];
        this.congestion5 = State.values()[gate5Congestion];
        this.passengers2 = gate2NumberOfPassenger;
        this.passengers3 = gate3NumberOfPassenger;
        this.passengers4 = gate4NumberOfPassenger;
        this.passengers5 = gate5NumberOfPassenger;
    }

    public static DepartureCongestion from(DeparturesCongestionItem item) {
        return new DepartureCongestion(
                item.getQueryDate(),
                item.getQueryTime(),
                item.getGate1(),
                item.getGate2(),
                item.getGate3(),
                item.getGate4(),
                item.getGateInfo1(),
                item.getGateInfo2(),
                item.getGateInfo3(),
                item.getGateInfo4());
    }

    @Override
    public String toString() {
        return "DepartureCongestion{" +
                "updateDate='" + updateDate + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", congestion2=" + congestion2 +
                ", congestion3=" + congestion3 +
                ", congestion4=" + congestion4 +
                ", congestion5=" + congestion5 +
                ", passengers2=" + passengers2 +
                ", passengers3=" + passengers3 +
                ", passengers4=" + passengers4 +
                ", passengers5=" + passengers5 +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartureCongestion that = (DepartureCongestion) o;
        return passengers2 == that.passengers2 &&
                passengers3 == that.passengers3 &&
                passengers4 == that.passengers4 &&
                passengers5 == that.passengers5 &&
                Objects.equals(updateDate, that.updateDate) &&
                Objects.equals(updateTime, that.updateTime) &&
                congestion2 == that.congestion2 &&
                congestion3 == that.congestion3 &&
                congestion4 == that.congestion4 &&
                congestion5 == that.congestion5;
    }

    @Override
    public int hashCode() {
        return Objects.hash(updateDate, updateTime, congestion2, congestion3,
                congestion4, congestion5, passengers2,
                passengers3, passengers4, passengers5);
    }
}
