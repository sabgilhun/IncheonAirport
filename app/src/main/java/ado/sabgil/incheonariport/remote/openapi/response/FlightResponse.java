package ado.sabgil.incheonariport.remote.openapi.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "response")
public class FlightResponse {

    @Element(name = "header")
    private Header header;

    @Element(name = "body", required = false)
    private Body body;

    public Header getHeader() {
        return header;
    }

    public Body getBody() {
        return body;
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

        @Element
        private String airline;

        @Element
        private String flightId;

        @Element
        private String scheduleDateTime;

        @Element
        private String estimatedDateTime;

        @Element
        private String airport;

        @Element(name = "gatenumber")
        private String gateNumber;

        @Element
        private String airportCode;

        @Element(required = false)
        private String remark;

        @Element(name = "yoil", required = false)
        private String dayOfWeek;

        @Element(name = "chkinrange", required = false)
        private String checkInRange;

        @Element(name = "himidity", required = false)
        private String humidity;

        @Element(name = "wimage", required = false)
        private String weatherImage;

        @Element(required = false)
        private String wind;

        @Element(name = "maxtem", required = false)
        private String maxTemperature;

        @Element(name = "mintem", required = false)
        private String minTemperature;

        @Element(name = "terminalid")
        private String terminalId;

        public String getAirline() {
            return airline;
        }

        public String getFlightId() {
            return flightId;
        }

        public String getScheduleDateTime() {
            return scheduleDateTime;
        }

        public String getEstimatedDateTime() {
            return estimatedDateTime;
        }

        public String getAirport() {
            return airport;
        }

        public String getGateNumber() {
            return gateNumber;
        }

        public String getAirportCode() {
            return airportCode;
        }

        public String getRemark() {
            return remark;
        }

        public String getDayOfWeek() {
            return dayOfWeek;
        }

        public String getCheckInRange() {
            return checkInRange;
        }

        public String getHumidity() {
            return humidity;
        }

        public String getWeatherImage() {
            return weatherImage;
        }

        public String getWind() {
            return wind;
        }

        public String getMaxTemperature() {
            return maxTemperature;
        }

        public String getMinTemperature() {
            return minTemperature;
        }

        public String getTerminalId() {
            return terminalId;
        }

        @Override
        public String toString() {
            return "PassengerDeparturesWItem{" +
                    "airline='" + airline + '\'' +
                    ", flightId='" + flightId + '\'' +
                    ", scheduleDateTime='" + scheduleDateTime + '\'' +
                    ", estimatedDateTime='" + estimatedDateTime + '\'' +
                    ", airport='" + airport + '\'' +
                    ", gateNumber='" + gateNumber + '\'' +
                    ", airportCode='" + airportCode + '\'' +
                    ", remark='" + remark + '\'' +
                    ", dayOfWeek='" + dayOfWeek + '\'' +
                    ", checkInRange='" + checkInRange + '\'' +
                    ", humidity='" + humidity + '\'' +
                    ", weatherImage='" + weatherImage + '\'' +
                    ", wind='" + wind + '\'' +
                    ", maxTemperature='" + maxTemperature + '\'' +
                    ", minTemperature='" + minTemperature + '\'' +
                    ", terminalId='" + terminalId + '\'' +
                    '}';
        }
    }
}
