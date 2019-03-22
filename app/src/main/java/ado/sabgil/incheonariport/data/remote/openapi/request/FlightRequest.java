package ado.sabgil.incheonariport.data.remote.openapi.request;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FlightRequest {
    private final HashMap<String, String> requestMap;

    public static class Builder {
        private final HashMap<String, String> requestMap = new HashMap<>();

        public Builder pageNo(String pageNo) {
            requestMap.put("pageNo", pageNo);
            return this;
        }

        public Builder numOfRows(String numOfRows) {
            requestMap.put("numOfRows", numOfRows);
            return this;
        }

        public Builder fromTime(String fromTime) {
            requestMap.put("from_time", fromTime);
            return this;
        }

        public Builder toTime(String toTime) {
            requestMap.put("to_time", toTime);
            return this;
        }

        public Builder airport(String airport) {
            requestMap.put("airport", airport);
            return this;
        }

        public Builder flightId(String flightId) {
            requestMap.put("flight_id", flightId);
            return this;
        }

        public Builder airline(String airline) {
            requestMap.put("airline", airline);
            return this;
        }

        public FlightRequest build() {
            return new FlightRequest(requestMap);
        }
    }

    private FlightRequest(HashMap<String, String> hashMap) {
        requestMap = hashMap;
    }

    public Map<String, String> getRequestMap() {
        return Collections.unmodifiableMap(requestMap);
    }

    @Override
    public String toString() {
        return "FlightRequest{" +
                "requestMap=" + requestMap +
                '}';
    }
}
