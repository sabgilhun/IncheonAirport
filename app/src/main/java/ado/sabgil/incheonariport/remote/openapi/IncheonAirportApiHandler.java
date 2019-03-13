package ado.sabgil.incheonariport.remote.openapi;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import ado.sabgil.incheonariport.BuildConfig;
import ado.sabgil.incheonariport.model.DepartureCongestion;
import ado.sabgil.incheonariport.model.SimpleFlightInfo;
import ado.sabgil.incheonariport.remote.OnFailureListener;
import ado.sabgil.incheonariport.remote.OnResponseListener;
import ado.sabgil.incheonariport.remote.openapi.response.DeparturesCongestionItem;
import ado.sabgil.incheonariport.remote.openapi.response.DeparturesCongestionResponse;
import ado.sabgil.incheonariport.remote.openapi.response.Header;
import ado.sabgil.incheonariport.remote.openapi.response.PassengerDeparturesWItem;
import ado.sabgil.incheonariport.remote.openapi.response.PassengerDeparturesWResponse;
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


    public void getDepartureCongestion(@NonNull String terminalNumber,
                                       @NonNull OnResponseListener<DepartureCongestion> onResponseListener,
                                       @NonNull OnFailureListener onFailureListener) {

        retrofit.getDeparturesCongestion(key, terminalNumber)
                .enqueue(new Callback<DeparturesCongestionResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<DeparturesCongestionResponse> call,
                                           @NonNull Response<DeparturesCongestionResponse> response) {

                        DeparturesCongestionResponse congestionResponse = response.body();
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

                        DeparturesCongestionItem item;
                        item = congestionResponse
                                .getBody()
                                .getItems()
                                .getItems()
                                .get(0);

                        DepartureCongestion data = DepartureCongestion.from(item);
                        onResponseListener.onResponse(data);

                    }

                    @Override
                    public void onFailure(@NonNull Call<DeparturesCongestionResponse> call,
                                          @NonNull Throwable t) {
                        onFailureListener.onFailure(new RuntimeException((t.getMessage())));
                    }
                });

    }

    public void getPassengerDeparturesW(@NonNull String flightID,
                                        @NonNull OnResponseListener<List<SimpleFlightInfo>> onResponseListener,
                                        @NonNull OnFailureListener onFailureListener) {

        if (TextUtils.isEmpty(flightID)) {
            onFailureListener.onFailure(new RuntimeException("No Query"));
            return;
        }

        retrofit.getPassengerDeparturesW(key, flightID)
                .enqueue(new Callback<PassengerDeparturesWResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<PassengerDeparturesWResponse> call,
                                           @NonNull Response<PassengerDeparturesWResponse> response) {

                        PassengerDeparturesWResponse departuresResponse = response.body();
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

                        List<PassengerDeparturesWItem> items;
                        items = departuresResponse
                                .getBody()
                                .getItems()
                                .getItems();

                        if (items == null) {
                            onFailureListener.onFailure(new RuntimeException("no data"));
                            return;
                        }

                        List<SimpleFlightInfo> result = new ArrayList<>();
                        for (PassengerDeparturesWItem item : items) {
                            result.add(SimpleFlightInfo.from(item));
                        }

                        onResponseListener.onResponse(result);
                    }

                    @Override
                    public void onFailure(@NonNull Call<PassengerDeparturesWResponse> call,
                                          @NonNull Throwable t) {
                        onFailureListener.onFailure(new RuntimeException((t.getMessage())));
                    }
                });
    }

}
