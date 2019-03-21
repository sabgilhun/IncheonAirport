package ado.sabgil.incheonariport.remote.openapi.request;

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

        public Builder from_time(String from_time) {
            requestMap.put("from_time", from_time);
            return this;
        }

        public Builder to_time(String to_time) {
            requestMap.put("to_time", to_time);
            return this;
        }

        public Builder airport(String airport) {
            requestMap.put("airport", airport);
            return this;
        }

        public Builder flight_id(String flight_id) {
            requestMap.put("flight_id", flight_id);
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
