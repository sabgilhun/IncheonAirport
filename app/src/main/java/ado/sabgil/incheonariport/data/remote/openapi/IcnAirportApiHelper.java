package ado.sabgil.incheonariport.data.remote.openapi;

import java.util.Map;

import ado.sabgil.incheonariport.data.OnFailureListener;
import ado.sabgil.incheonariport.data.OnResponseListener;
import ado.sabgil.incheonariport.data.remote.openapi.response.CongestionResponse;
import ado.sabgil.incheonariport.data.remote.openapi.response.FlightResponse;
import ado.sabgil.incheonariport.data.remote.openapi.response.NoticeResponse;
import androidx.annotation.NonNull;

public interface IcnAirportApiHelper {

    void getCongestion(@NonNull Map<String, String> queries,
                       @NonNull OnResponseListener<CongestionResponse> onResponseListener,
                       @NonNull OnFailureListener onFailureListener);

    void getFlight(@NonNull Map<String, String> queries,
                   @NonNull OnResponseListener<FlightResponse> onResponseListener,
                   @NonNull OnFailureListener onFailureListener);

    void getNotice(@NonNull Map<String, String> queries,
                   @NonNull OnResponseListener<NoticeResponse> onResponseListener,
                   @NonNull OnFailureListener onFailureListener);


}
