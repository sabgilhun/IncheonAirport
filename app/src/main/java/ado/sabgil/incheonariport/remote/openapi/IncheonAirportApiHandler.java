package ado.sabgil.incheonariport.remote.openapi;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ado.sabgil.incheonariport.BuildConfig;
import ado.sabgil.incheonariport.model.SimpleFlightInfo;
import ado.sabgil.incheonariport.model.Terminal1Congestion;
import ado.sabgil.incheonariport.remote.OnFailureListener;
import ado.sabgil.incheonariport.remote.OnResponseListener;
import ado.sabgil.incheonariport.remote.openapi.response.CongestionResponse;
import ado.sabgil.incheonariport.remote.openapi.response.FlightResponse;
import ado.sabgil.incheonariport.remote.openapi.response.Header;
import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

import static ado.sabgil.incheonariport.Constant.OpenApiResponseCode.NORMAL_SERVICE;

public class IncheonAirportApiHandler {

    private RetrofitInterface retrofit;
    private final String URL = "http://openapi.airport.kr/openapi/service/";
    private final String key = BuildConfig.OPEN_API_SERVICE_KEY;

    private static class LazyHolder {
        private static final IncheonAirportApiHandler INSTANCE = new IncheonAirportApiHandler();
    }

    public static IncheonAirportApiHandler getInstance() {
        return LazyHolder.INSTANCE;
    }

    private IncheonAirportApiHandler() {
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build()
                .create(RetrofitInterface.class);
    }


    public void getCongestion(@NonNull Map<String, String> queries,
                              @NonNull OnResponseListener<Terminal1Congestion> onResponseListener,
                              @NonNull OnFailureListener onFailureListener) {

        retrofit.getCongestion(key, queries)
                .enqueue(new Callback<CongestionResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<CongestionResponse> call,
                                           @NonNull Response<CongestionResponse> response) {

                        CongestionResponse congestionResponse = response.body();
                        if (congestionResponse == null) {
                            onFailureListener.onFailure(new RuntimeException("No Response"));
                            return;
                        }

                        Header header = congestionResponse.getHeader();
                        if (!TextUtils.equals(header.getResultCode(), NORMAL_SERVICE)) {
                            String error = header.getResultMsg();
                            onFailureListener.onFailure(new RuntimeException(error));
                            return;
                        }

                        CongestionResponse.Item item;
                        item = congestionResponse
                                .getBody()
                                .getItems()
                                .getItems()
                                .get(0);

                        Terminal1Congestion data = Terminal1Congestion.from(item);
                        onResponseListener.onResponse(data);

                    }

                    @Override
                    public void onFailure(@NonNull Call<CongestionResponse> call,
                                          @NonNull Throwable t) {
                        onFailureListener.onFailure(new RuntimeException((t.getMessage())));
                    }
                });

    }

    public void getFlight(@NonNull Map<String, String> queries,
                          @NonNull OnResponseListener<List<SimpleFlightInfo>> onResponseListener,
                          @NonNull OnFailureListener onFailureListener) {

        if (queries.size() == 0) {
            onFailureListener.onFailure(new RuntimeException("No Query"));
            return;
        }

        retrofit.getFlight(key, queries)
                .enqueue(new Callback<FlightResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<FlightResponse> call,
                                           @NonNull Response<FlightResponse> response) {

                        FlightResponse departuresResponse = response.body();
                        if (departuresResponse == null) {
                            onFailureListener.onFailure(new RuntimeException("No Response"));
                            return;
                        }

                        Header header = departuresResponse.getHeader();
                        if (!TextUtils.equals(header.getResultCode(), NORMAL_SERVICE)) {
                            String error = header.getResultMsg();
                            onFailureListener.onFailure(new RuntimeException(error));
                            return;
                        }

                        List<FlightResponse.Item> items;
                        items = departuresResponse
                                .getBody()
                                .getItems()
                                .getItems();

                        if (items == null) {
                            onResponseListener.onResponse(null);
                            return;
                        }

                        List<SimpleFlightInfo> result = new ArrayList<>();
                        for (FlightResponse.Item item : items) {
                            result.add(SimpleFlightInfo.from(item));
                        }

                        onResponseListener.onResponse(result);
                    }

                    @Override
                    public void onFailure(@NonNull Call<FlightResponse> call,
                                          @NonNull Throwable t) {
                        onFailureListener.onFailure(new RuntimeException((t.getMessage())));
                    }
                });
    }

}
