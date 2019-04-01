package ado.sabgil.incheonariport.data.model;

import java.util.Objects;

import ado.sabgil.incheonariport.data.remote.openapi.response.CongestionResponse;
import androidx.annotation.NonNull;

public class Terminal1Congestion {

    @NonNull
    private final String updateDate;

    @NonNull
    private final String updateTime;

    @NonNull
    private final GateCongestion gate2;

    @NonNull
    private final GateCongestion gate3;

    @NonNull
    private final GateCongestion gate4;

    @NonNull
    private final GateCongestion gate5;

    @NonNull
    public String getUpdateDate() {
        return updateDate;
    }

    @NonNull
    public String getUpdateTime() {
        return updateTime;
    }

    @NonNull
    public GateCongestion getGate2() {
        return gate2;
    }

    @NonNull
    public GateCongestion getGate3() {
        return gate3;
    }

    @NonNull
    public GateCongestion getGate4() {
        return gate4;
    }

    @NonNull
    public GateCongestion getGate5() {
        return gate5;
    }

    private Terminal1Congestion(@NonNull String updateDate,
                                @NonNull String updateTime,
                                @NonNull GateCongestion gate2,
                                @NonNull GateCongestion gate3,
                                @NonNull GateCongestion gate4,
                                @NonNull GateCongestion gate5) {
        this.updateDate = updateDate;
        this.updateTime = updateTime;
        this.gate2 = gate2;
        this.gate3 = gate3;
        this.gate4 = gate4;
        this.gate5 = gate5;
    }

    public static Terminal1Congestion from(CongestionResponse.Item item) {
        String updateDate = new StringBuilder(item.getQueryDate())
                .delete(0, 2)
                .insert(6, "일")
                .insert(4, "월 ")
                .insert(2, "년 ")
                .toString();

        String updateTime = new StringBuilder(item.getQueryTime())
                .insert(2, ":")
                .toString();

        return new Terminal1Congestion(
                updateDate,
                updateTime,
                new GateCongestion(2, GateCongestion.State.values()[item.getGate1()],
                        item.getGateInfo1()),
                new GateCongestion(3, GateCongestion.State.values()[item.getGate2()],
                        item.getGateInfo2()),
                new GateCongestion(4, GateCongestion.State.values()[item.getGate3()],
                        item.getGateInfo3()),
                new GateCongestion(5, GateCongestion.State.values()[item.getGate4()],
                        item.getGateInfo4())
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Terminal1Congestion that = (Terminal1Congestion) o;
        return Objects.equals(updateDate, that.updateDate) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(gate2, that.gate2) &&
                Objects.equals(gate3, that.gate3) &&
                Objects.equals(gate4, that.gate4) &&
                Objects.equals(gate5, that.gate5);
    }

    @Override
    public int hashCode() {
        return Objects.hash(updateDate, updateTime, gate2, gate3, gate4, gate5);
    }

    @NonNull
    @Override
    public String toString() {
        return "Terminal1Congestion{" +
                "updateDate='" + updateDate + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", gate2=" + gate2 +
                ", gate3=" + gate3 +
                ", gate4=" + gate4 +
                ", gate5=" + gate5 +
                '}';
    }
}
