package ado.sabgil.incheonariport.model;

import ado.sabgil.incheonariport.remote.openapi.response.DeparturesCongestionItem;
import androidx.annotation.NonNull;

public class DepartureCongestion {

    enum State {
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
    private final State gate2Congestion;

    @NonNull
    private final State gate3Congestion;

    @NonNull
    private final State gate4Congestion;

    @NonNull
    private final State gate5Congestion;

    private final int gate2NumberOfPassenger;

    private final int gate3NumberOfPassenger;

    private final int gate4NumberOfPassenger;

    private final int gate5NumberOfPassenger;

    @NonNull
    public String getUpdateDate() {
        return updateDate;
    }

    @NonNull
    public String getUpdateTime() {
        return updateTime;
    }

    @NonNull
    public State getGate2Congestion() {
        return gate2Congestion;
    }

    @NonNull
    public State getGate3Congestion() {
        return gate3Congestion;
    }

    @NonNull
    public State getGate4Congestion() {
        return gate4Congestion;
    }

    @NonNull
    public State getGate5Congestion() {
        return gate5Congestion;
    }

    public int getGate2NumberOfPassenger() {
        return gate2NumberOfPassenger;
    }

    public int getGate3NumberOfPassenger() {
        return gate3NumberOfPassenger;
    }

    public int getGate4NumberOfPassenger() {
        return gate4NumberOfPassenger;
    }

    public int getGate5NumberOfPassenger() {
        return gate5NumberOfPassenger;
    }

    public DepartureCongestion(@NonNull String updateDate,
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
        this.gate2Congestion = State.values()[gate2Congestion];
        this.gate3Congestion = State.values()[gate3Congestion];
        this.gate4Congestion = State.values()[gate4Congestion];
        this.gate5Congestion = State.values()[gate5Congestion];
        this.gate2NumberOfPassenger = gate2NumberOfPassenger;
        this.gate3NumberOfPassenger = gate3NumberOfPassenger;
        this.gate4NumberOfPassenger = gate4NumberOfPassenger;
        this.gate5NumberOfPassenger = gate5NumberOfPassenger;
    }

    public static DepartureCongestion of(DeparturesCongestionItem item) {
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
                ", gate2Congestion=" + gate2Congestion +
                ", gate3Congestion=" + gate3Congestion +
                ", gate4Congestion=" + gate4Congestion +
                ", gate5Congestion=" + gate5Congestion +
                ", gate2NumberOfPassenger=" + gate2NumberOfPassenger +
                ", gate3NumberOfPassenger=" + gate3NumberOfPassenger +
                ", gate4NumberOfPassenger=" + gate4NumberOfPassenger +
                ", gate5NumberOfPassenger=" + gate5NumberOfPassenger +
                '}';
    }
}
