package ado.sabgil.incheonariport.data.model;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ado.sabgil.incheonariport.data.remote.openapi.response.NoticeResponse;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Terminal1Notice {

    @NonNull
    private final String updateDate;

    private final int updateTime;

    @NonNull
    private final List<Integer> gate2;

    @NonNull
    private final List<Integer> gate3;

    @NonNull
    private final List<Integer> gate4;

    @NonNull
    private final List<Integer> gate5;

    private Terminal1Notice(@NonNull String updateDate,
                            int updateTime,
                            @NonNull List<Integer> gate2,
                            @NonNull List<Integer> gate3,
                            @NonNull List<Integer> gate4,
                            @NonNull List<Integer> gate5) {
        this.updateDate = updateDate;
        this.updateTime = updateTime;
        this.gate2 = gate2;
        this.gate3 = gate3;
        this.gate4 = gate4;
        this.gate5 = gate5;
    }

    @SuppressWarnings("ConstantConditions")
    public static Terminal1Notice of(int time,
                                     int range,
                                     @NonNull List<NoticeResponse.Item> today,
                                     @Nullable List<NoticeResponse.Item> tomorrow) {
        if (time > 23 || time < 0 || range < 0 ||
                (tomorrow != null && (time + range) < 24) ||
                (tomorrow == null && (time + range) > 23)) {
            throw new InvalidParameterException();
        } else {
            List<Integer> gate2 = new ArrayList<>();
            List<Integer> gate3 = new ArrayList<>();
            List<Integer> gate4 = new ArrayList<>();
            List<Integer> gate5 = new ArrayList<>();

            for (int i = time; i < range + time; i++) {
                NoticeResponse.Item item;
                if (i < 24) {
                    item = today.get(i);
                } else {
                    item = tomorrow.get(i - 24);
                }

                gate2.add(item.getT1sum5());
                gate3.add(item.getT1sum6());
                gate4.add(item.getT1sum7());
                gate5.add(item.getT1sum8());
            }

            return new Terminal1Notice(
                    today.get(0).getDate(),
                    time,
                    Collections.unmodifiableList(gate2),
                    Collections.unmodifiableList(gate3),
                    Collections.unmodifiableList(gate4),
                    Collections.unmodifiableList(gate5));
        }
    }

    public static Terminal1Notice of(int time,
                                     int range,
                                     List<NoticeResponse.Item> today) {
        return of(time, range, today, null);
    }

    @NonNull
    public String getUpdateDate() {
        return updateDate;
    }

    public int getUpdateTime() {
        return updateTime;
    }

    @NonNull
    public List<Integer> getGate2() {
        return gate2;
    }

    @NonNull
    public List<Integer> getGate3() {
        return gate3;
    }

    @NonNull
    public List<Integer> getGate4() {
        return gate4;
    }

    @NonNull
    public List<Integer> getGate5() {
        return gate5;
    }

    @Override
    public String toString() {
        return "Terminal1Notice{" +
                "updateDate='" + updateDate + '\'' +
                ", updateTime=" + updateTime +
                ", gate2=" + gate2 +
                ", gate3=" + gate3 +
                ", gate4=" + gate4 +
                ", gate5=" + gate5 +
                '}';
    }
}
