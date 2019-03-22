package ado.sabgil.incheonariport.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ado.sabgil.incheonariport.data.model.SimpleFlightInfo;
import ado.sabgil.incheonariport.data.model.Terminal1Congestion;
import ado.sabgil.incheonariport.data.model.Terminal1Notice;
import ado.sabgil.incheonariport.data.remote.openapi.IcnAirportApiHelper;
import ado.sabgil.incheonariport.data.remote.openapi.IcnAirportApiHelperImpl;
import ado.sabgil.incheonariport.data.remote.openapi.request.CongestionRequest;
import ado.sabgil.incheonariport.data.remote.openapi.request.FlightRequest;
import ado.sabgil.incheonariport.data.remote.openapi.response.CongestionResponse;
import ado.sabgil.incheonariport.data.remote.openapi.response.FlightResponse;
import ado.sabgil.incheonariport.data.remote.openapi.response.NoticeResponse;
import ado.sabgil.incheonariport.util.TimeUtils;
import androidx.annotation.NonNull;

public class DataManagerImpl implements DataManager {

    private IcnAirportApiHelper icnAirportApiHelper;

    private static class LazyHolder {
        private static final DataManagerImpl INSTANCE = new DataManagerImpl();
    }

    public static DataManagerImpl getInstance() {
        return DataManagerImpl.LazyHolder.INSTANCE;
    }

    private DataManagerImpl() {
        icnAirportApiHelper = IcnAirportApiHelperImpl.getInstance();
    }

    @Override
    public void getT1Congestion(@NonNull OnResponseListener<Terminal1Congestion> responseListener,
                                @NonNull OnFailureListener onFailureListener) {
        CongestionRequest request;
        request = new CongestionRequest.Builder()
                .terminalNo("1")
                .build();

        getCongestion(request.getRequestMap(),
                result -> {
                    CongestionResponse.Item item = result.getBody().getItems().getItems().get(0);
                    Terminal1Congestion terminal1Congestion = Terminal1Congestion.from(item);
                    responseListener.onResponse(terminal1Congestion);
                },
                onFailureListener);
    }

    @Override
    public void getSimpleFlightInfo(@NonNull OnResponseListener<List<SimpleFlightInfo>> onResponseListener,
                                    @NonNull OnFailureListener onFailureListener) {

        FlightRequest request;
        String fromTime = TimeUtils.getCurrentHour();
        String toTime = TimeUtils.getAfterHour(fromTime, 6);
        request = new FlightRequest.Builder()
                .fromTime(fromTime)
                .toTime(toTime)
                .build();

        getFlight(request.getRequestMap(),
                result -> {
                    List<SimpleFlightInfo> simpleFlightInfos = new ArrayList<>();
                    for (FlightResponse.Item item : result.getBody().getItems().getItems()) {
                        simpleFlightInfos.add(SimpleFlightInfo.from(item));
                    }
                    onResponseListener.onResponse(simpleFlightInfos);
                },
                onFailureListener);
    }

    @Override
    public void getSimpleFlightInfoWithId(@NonNull String flightId,
                                          @NonNull OnResponseListener<List<SimpleFlightInfo>> onResponseListener,
                                          @NonNull OnFailureListener onFailureListener) {
        FlightRequest request;
        request = new FlightRequest.Builder()
                .flightId(flightId)
                .build();

        getFlight(request.getRequestMap(),
                result -> {
                    List<SimpleFlightInfo> simpleFlightInfos = new ArrayList<>();
                    for (FlightResponse.Item item : result.getBody().getItems().getItems()) {
                        simpleFlightInfos.add(SimpleFlightInfo.from(item));
                    }
                    onResponseListener.onResponse(simpleFlightInfos);
                },
                onFailureListener);
    }

    @Override
    public void getT1PassengerNotice(@NonNull OnResponseListener<Terminal1Notice> onResponseListener,
                                     @NonNull OnFailureListener onFailureListener) {

    }

    @Override
    public void getCongestion(@NonNull Map<String, String> queries,
                              @NonNull OnResponseListener<CongestionResponse> onResponseListener,
                              @NonNull OnFailureListener onFailureListener) {
        icnAirportApiHelper.getCongestion(queries, onResponseListener, onFailureListener);
    }

    @Override
    public void getFlight(@NonNull Map<String, String> queries,
                          @NonNull OnResponseListener<FlightResponse> onResponseListener,
                          @NonNull OnFailureListener onFailureListener) {
        icnAirportApiHelper.getFlight(queries, onResponseListener, onFailureListener);
    }

    @Override
    public void getNotice(@NonNull Map<String, String> queries,
                          @NonNull OnResponseListener<NoticeResponse> onResponseListener,
                          @NonNull OnFailureListener onFailureListener) {
        icnAirportApiHelper.getNotice(queries, onResponseListener, onFailureListener);
    }
}
