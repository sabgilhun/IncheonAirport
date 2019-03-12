package ado.sabgil.incheonariport.remote.openapi;

import android.text.TextUtils;

import java.util.List;

import ado.sabgil.incheonariport.BuildConfig;
import ado.sabgil.incheonariport.model.DepartureCongestion;
import ado.sabgil.incheonariport.remote.OnFailureListener;
import ado.sabgil.incheonariport.remote.OnResponseListener;
import ado.sabgil.incheonariport.remote.openapi.response.DeparturesCongestionItem;
import ado.sabgil.incheonariport.remote.openapi.response.DeparturesCongestionResponse;
import ado.sabgil.incheonariport.remote.openapi.response.Header;
import ado.sabgil.incheonariport.remote.openapi.response.PassengerDeparturesItem;
import ado.sabgil.incheonariport.remote.openapi.response.PassengerDeparturesResponse;
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

                        DepartureCongestion data = DepartureCongestion.of(item);
                        onResponseListener.onResponse(data);

                    }

                    @Override
                    public void onFailure(@NonNull Call<DeparturesCongestionResponse> call,
                                          @NonNull Throwable t) {
                        onFailureListener.onFailure(new RuntimeException((t.getMessage())));
                    }
                });

    }

    //TODO: 응답 string으로 받고 있는거 model 객체 만들고 변경해야함
    public void getDeparturesWeather(@NonNull String flightID,
                                     @NonNull OnResponseListener<String> onResponseListener,
                                     @NonNull OnFailureListener onFailureListener) {

        if (TextUtils.isEmpty(flightID)) {
            onFailureListener.onFailure(new RuntimeException("No Query"));
            return;
        }

        retrofit.getPassengerDepartures(key, flightID)
                .enqueue(new Callback<PassengerDeparturesResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<PassengerDeparturesResponse> call,
                                           @NonNull Response<PassengerDeparturesResponse> response) {

                        PassengerDeparturesResponse departuresResponse = response.body();
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

                        List<PassengerDeparturesItem> items;
                        items = departuresResponse
                                .getBody()
                                .getItems()
                                .getItems();

                        if (items == null) {
                            onFailureListener.onFailure(new RuntimeException("no data"));
                            return;
                        }

                        StringBuilder stringBuilder = new StringBuilder();
                        for (PassengerDeparturesItem item : items) {
                            stringBuilder.append(item.toString());
                            stringBuilder.append("\n");
                        }

                        onResponseListener.onResponse(stringBuilder.toString());

                    }

                    @Override
                    public void onFailure(@NonNull Call<PassengerDeparturesResponse> call,
                                          @NonNull Throwable t) {
                        onFailureListener.onFailure(new RuntimeException((t.getMessage())));
                    }
                });
    }

}
