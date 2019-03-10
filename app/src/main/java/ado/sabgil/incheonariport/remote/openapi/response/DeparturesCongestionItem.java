package ado.sabgil.incheonariport.remote.openapi.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import androidx.annotation.NonNull;

@Root(name = "item")
public class DeparturesCongestionItem {

    @Element(name = "areadiv")
    private int area;

    @Element(name = "cgtdt")
    private String queryDate;

    @Element(name = "cgthm")
    private String queryTime;

    @Element
    private int gate1;

    @Element
    private int gate2;

    @Element(required = false)
    private int gate3;

    @Element(required = false)
    private int gate4;

    @Element(name = "gateinfo1")
    private int gateInfo1;

    @Element(name = "gateinfo2")
    private int gateInfo2;

    @Element(name = "gateinfo3", required = false)
    private int gateInfo3;

    @Element(name = "gateinfo4", required = false)
    private int gateInfo4;

    @Element(name = "terno")
    private int terminalNumber;

    public int getArea() {
        return area;
    }

    public String getQueryDate() {
        return queryDate;
    }

    public String getQueryTime() {
        return queryTime;
    }

    public int getGate1() {
        return gate1;
    }

    public int getGate2() {
        return gate2;
    }

    public int getGate3() {
        return gate3;
    }

    public int getGate4() {
        return gate4;
    }

    public int getGateInfo1() {
        return gateInfo1;
    }

    public int getGateInfo2() {
        return gateInfo2;
    }

    public int getGateInfo3() {
        return gateInfo3;
    }

    public int getGateInfo4() {
        return gateInfo4;
    }

    public int getTerminalNumber() {
        return terminalNumber;
    }

    @NonNull
    @Override
    public String toString() {
        return "DeparturesCongestionItem{" +
                "area=" + area +
                ", queryDate='" + queryDate + '\'' +
                ", queryTime='" + queryTime + '\'' +
                ", gate1=" + gate1 +
                ", gate2=" + gate2 +
                ", gate3=" + gate3 +
                ", gate4=" + gate4 +
                ", gateInfo1=" + gateInfo1 +
                ", gateInfo2=" + gateInfo2 +
                ", gateInfo3=" + gateInfo3 +
                ", gateInfo4=" + gateInfo4 +
                ", terminalNumber=" + terminalNumber +
                '}';
    }
}
