package ado.sabgil.incheonariport.data.remote.openapi.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "response")
public class NoticeResponse implements RemoteResponse {

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

    public static class Body {
        @Element(name = "items")
        private Items items;

        public Items getItems() {
            return items;
        }
    }

    public static class Items {
        @ElementList(inline = true, required = false)
        private ArrayList<Item> items;

        public ArrayList<Item> getItems() {
            return items;
        }
    }

    public static class Item {
        @Element(name = "adate")
        private String date;

        @Element(name = "atime")
        private String time;

        @Element(name = "t1sum1")
        private int t1sum1;

        @Element(name = "t1sum2")
        private int t1sum2;

        @Element(name = "t1sum3")
        private int t1sum3;

        @Element(name = "t1sum4")
        private int t1sum4;

        @Element(name = "t1sum5")
        private int t1sum5;

        @Element(name = "t1sum6")
        private int t1sum6;

        @Element(name = "t1sum7")
        private int t1sum7;

        @Element(name = "t1sum8")
        private int t1sum8;

        @Element(name = "t1sumset1")
        private int t1sumset1;

        @Element(name = "t1sumset2")
        private int t1sumset2;

        @Element(name = "t2sum1")
        private int t2sum1;

        @Element(name = "t2sum2")
        private int t2sum2;

        @Element(name = "t2sum3")
        private int t2sum3;

        @Element(name = "t2sum4")
        private int t2sum4;

        @Element(name = "t2sumset1")
        private int t2sumset1;

        @Element(name = "t2sumset2")
        private int t2sumset2;

        public String getDate() {
            return date;
        }

        public String getTime() {
            return time;
        }

        public int getT1sum1() {
            return t1sum1;
        }

        public int getT1sum2() {
            return t1sum2;
        }

        public int getT1sum3() {
            return t1sum3;
        }

        public int getT1sum4() {
            return t1sum4;
        }

        public int getT1sum5() {
            return t1sum5;
        }

        public int getT1sum6() {
            return t1sum6;
        }

        public int getT1sum7() {
            return t1sum7;
        }

        public int getT1sum8() {
            return t1sum8;
        }

        public int getT1sumset1() {
            return t1sumset1;
        }

        public int getT1sumset2() {
            return t1sumset2;
        }

        public int getT2sum1() {
            return t2sum1;
        }

        public int getT2sum2() {
            return t2sum2;
        }

        public int getT2sum3() {
            return t2sum3;
        }

        public int getT2sum4() {
            return t2sum4;
        }

        public int getT2sumset1() {
            return t2sumset1;
        }

        public int getT2sumset2() {
            return t2sumset2;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "date='" + date + '\'' +
                    ", time='" + time + '\'' +
                    ", t1sum1=" + t1sum1 +
                    ", t1sum2=" + t1sum2 +
                    ", t1sum3=" + t1sum3 +
                    ", t1sum4=" + t1sum4 +
                    ", t1sum5=" + t1sum5 +
                    ", t1sum6=" + t1sum6 +
                    ", t1sum7=" + t1sum7 +
                    ", t1sum8=" + t1sum8 +
                    ", t1sumset1=" + t1sumset1 +
                    ", t1sumset2=" + t1sumset2 +
                    ", t2sum1=" + t2sum1 +
                    ", t2sum2=" + t2sum2 +
                    ", t2sum3=" + t2sum3 +
                    ", t2sum4=" + t2sum4 +
                    ", t2sumset1=" + t2sumset1 +
                    ", t2sumset2=" + t2sumset2 +
                    '}';
        }
    }
}
