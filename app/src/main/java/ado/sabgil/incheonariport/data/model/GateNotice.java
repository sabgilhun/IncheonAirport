package ado.sabgil.incheonariport.data.model;

import java.util.List;

import androidx.annotation.NonNull;

public class GateNotice {

    private final int gateNo;

    @NonNull
    private final List<String> timeList;

    @NonNull
    private final List<Integer> notice;

    public GateNotice(int gateNo,
                      @NonNull List<String> timeList,
                      @NonNull List<Integer> notice) {
        this.gateNo = gateNo;
        this.timeList = timeList;
        this.notice = notice;
    }

    public int getGateNo() {
        return gateNo;
    }

    @NonNull
    public List<String> getTimeList() {
        return timeList;
    }

    @NonNull
    public List<Integer> getNotice() {
        return notice;
    }
}
