package ado.sabgil.incheonariport.data.remote.openapi.request;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CongestionRequest {
    private final HashMap<String, String> requestMap;

    public static class Builder {
        private final HashMap<String, String> requestMap = new HashMap<>();

        public CongestionRequest.Builder terminalNo(String terminalNo) {
            requestMap.put("terno", terminalNo);
            return this;
        }

        public CongestionRequest build() {
            return new CongestionRequest(requestMap);
        }
    }

    private CongestionRequest(HashMap<String, String> hashMap) {
        requestMap = hashMap;
    }

    public Map<String, String> getRequestMap() {
        return Collections.unmodifiableMap(requestMap);
    }

    @Override
    public String toString() {
        return "CongestionRequest{" +
                "requestMap=" + requestMap +
                '}';
    }
}
