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

    @NonNull
    private final List<GateNotice> notices;

    private Terminal1Notice(@NonNull String updateDate,
                            @NonNull List<GateNotice> notices) {
        this.updateDate = updateDate;
        this.notices = notices;
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
            List<String> timeList = new ArrayList<>();
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
                StringBuilder sb = new StringBuilder(item.getTime());
                sb.append("시").setCharAt(2, '-');

                timeList.add(sb.toString());
                gate2.add(item.getT1sum5());
                gate3.add(item.getT1sum6());
                gate4.add(item.getT1sum7());
                gate5.add(item.getT1sum8());
            }

            List<GateNotice> notices = new ArrayList<>();
            List<String> unmodifiableTimeList = Collections.unmodifiableList(timeList);
            notices.add(new GateNotice(2, unmodifiableTimeList,
                    Collections.unmodifiableList(gate2)));
            notices.add(new GateNotice(3, unmodifiableTimeList,
                    Collections.unmodifiableList(gate3)));
            notices.add(new GateNotice(4, unmodifiableTimeList,
                    Collections.unmodifiableList(gate4)));
            notices.add(new GateNotice(5, unmodifiableTimeList,
                    Collections.unmodifiableList(gate5)));

            return new Terminal1Notice(
                    today.get(0).getDate(),
                    Collections.unmodifiableList(notices));
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

    @NonNull
    public List<GateNotice> getNotices() {
        return notices;
    }

    @Override
    public String toString() {
        return "Terminal1Notice{" +
                "updateDate='" + updateDate + '\'' +
                ", notices=" + notices +
                '}';
    }
}
