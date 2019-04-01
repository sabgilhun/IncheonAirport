package ado.sabgil.incheonariport.data.model;

import androidx.annotation.NonNull;

public class GateCongestion {

    public enum State {

        LIGHT("원활"), NORMAL("보통"), BUSY("혼잡"), VERY_BUSY("매우 혼잡"), EMPTY2("오류"),
        EMPTY3("오류"), EMPTY4("오류"), EMPTY5("오류"), EMPTY6("오류"), CLOSE("운영 종료");

        final private String name;

        State(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private final int gateNo;

    @NonNull
    private final State congestion;

    private final int passengers;

    public int getGateNo() {
        return gateNo;
    }

    @NonNull
    public State getCongestion() {
        return congestion;
    }

    public int getPassengers() {
        return passengers;
    }

    GateCongestion(int gateNo, @NonNull State congestion, int passengers) {
        this.gateNo = gateNo;
        this.congestion = congestion;
        this.passengers = passengers;
    }
}
