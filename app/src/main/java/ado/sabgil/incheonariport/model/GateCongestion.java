package ado.sabgil.incheonariport.model;

import ado.sabgil.incheonariport.model.enums.State;
import androidx.annotation.NonNull;

public class GateCongestion {

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

    public GateCongestion(int gateNo, @NonNull State congestion, int passengers) {
        this.gateNo = gateNo;
        this.congestion = congestion;
        this.passengers = passengers;
    }
}
