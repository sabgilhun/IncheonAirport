package ado.sabgil.incheonariport.data.remote.openapi.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

import androidx.annotation.NonNull;

@Root(name = "response")
public class CongestionResponse implements RemoteResponse {

    @Element(name = "header")
    private Header header;

    @Element(name = "body")
    private Body body;

    public Header getHeader() {
        return header;
    }

    public Body getBody() {
        return body;
    }

    public boolean isEmptyResponse() {
        return getBody().items.getItems() == null;
    }

    @Root(name = "body")
    public static class Body {

        @Element(name = "items")
        private Items items;

        @Element(name = "numOfRows")
        private int numOfRows;

        @Element(name = "pageNo")
        private int pageNo;

        @Element(name = "totalCount")
        private int totalCount;

        public Items getItems() {
            return items;
        }

        public int getNumOfRows() {
            return numOfRows;
        }

        public int getPageNo() {
            return pageNo;
        }

        public int getTotalCount() {
            return totalCount;
        }
    }

    @Root(name = "items")
    public static class Items {

        @ElementList(inline = true, required = false)
        private ArrayList<Item> items;

        public ArrayList<Item> getItems() {
            return items;
        }
    }

    @Root(name = "item")
    public static class Item {

        @Element(name = "areadiv")
        private int area;

        @Element(name = "cgtdt")
        private String queryDate;

        @Element(name = "cgthm")
        private String queryTime;

        @Element(name = "gate1")
        private int gate1;

        @Element(name = "gate2")
        private int gate2;

        @Element(name = "gate3")
        private int gate3;

        @Element(name = "gate4")
        private int gate4;

        @Element(name = "gateinfo1")
        private int gateInfo1;

        @Element(name = "gateinfo2")
        private int gateInfo2;

        @Element(name = "gateinfo3")
        private int gateInfo3;

        @Element(name = "gateinfo4")
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
}
