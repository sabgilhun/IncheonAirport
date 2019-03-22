package ado.sabgil.incheonariport.data.remote.openapi.request;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class NoticeRequest {
    private final HashMap<String, String> requestMap;

    public static class Builder {
        private final HashMap<String, String> requestMap = new HashMap<>();

        public Builder selectDate(String selectDate) {
            requestMap.put("selectdate", selectDate);
            return this;
        }

        public NoticeRequest build() {
            return new NoticeRequest(requestMap);
        }
    }

    private NoticeRequest(HashMap<String, String> hashMap) {
        requestMap = hashMap;
    }

    public Map<String, String> getRequestMap() {
        return Collections.unmodifiableMap(requestMap);
    }
}
