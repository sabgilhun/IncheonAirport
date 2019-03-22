package ado.sabgil.incheonariport.data.remote.openapi;

import android.text.TextUtils;

import java.util.Map;

import ado.sabgil.incheonariport.BuildConfig;
import ado.sabgil.incheonariport.data.OnFailureListener;
import ado.sabgil.incheonariport.data.OnResponseListener;
import ado.sabgil.incheonariport.data.remote.openapi.response.CongestionResponse;
import ado.sabgil.incheonariport.data.remote.openapi.response.FlightResponse;
import ado.sabgil.incheonariport.data.remote.openapi.response.Header;
import ado.sabgil.incheonariport.data.remote.openapi.response.NoticeResponse;
import ado.sabgil.incheonariport.data.remote.openapi.response.RemoteResponse;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

import static ado.sabgil.incheonariport.Constant.OpenApiResponseCode.NORMAL_SERVICE;

public class IcnAirportApiHelperImpl implements IcnAirportApiHelper {

    private RetrofitInterface retrofit;
    private final String URL = "http://openapi.airport.kr/openapi/service/";
    private final String key = BuildConfig.OPEN_API_SERVICE_KEY;

    private static class LazyHolder {
        private static final IcnAirportApiHelperImpl INSTANCE = new IcnAirportApiHelperImpl();
    }

    public static IcnAirportApiHelperImpl getInstance() {
        return LazyHolder.INSTANCE;
    }

    private IcnAirportApiHelperImpl() {
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build()
                .create(RetrofitInterface.class);
    }


    public void getCongestion(@NonNull Map<String, String> queries,
                              @NonNull OnResponseListener<CongestionResponse> onResponseListener,
                              @NonNull OnFailureListener onFailureListener) {

        retrofit.getCongestion(key, queries)
                .enqueue(new Callback<CongestionResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<CongestionResponse> call,
                                           @NonNull Response<CongestionResponse> response) {

                        CongestionResponse congestionResponse = response.body();
                        String errorMessage = checkResponseException(congestionResponse);
                        if (TextUtils.isEmpty(errorMessage)) {
                            onFailureListener.onFailure(new RuntimeException(errorMessage));
                        }
                        onResponseListener.onResponse(congestionResponse);

                    }

                    @Override
                    public void onFailure(@NonNull Call<CongestionResponse> call,
                                          @NonNull Throwable t) {
                        onFailureListener.onFailure(new RuntimeException((t.getMessage())));
                    }
                });

    }

    public void getFlight(@NonNull Map<String, String> queries,
                          @NonNull OnResponseListener<FlightResponse> onResponseListener,
                          @NonNull OnFailureListener onFailureListener) {

        retrofit.getFlight(key, queries)
                .enqueue(new Callback<FlightResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<FlightResponse> call,
                                           @NonNull Response<FlightResponse> response) {

                        FlightResponse flightResponse = response.body();
                        String errorMessage = checkResponseException(flightResponse);
                        if (TextUtils.isEmpty(errorMessage)) {
                            onFailureListener.onFailure(new RuntimeException(errorMessage));
                        }
                        onResponseListener.onResponse(flightResponse);
                    }

                    @Override
                    public void onFailure(@NonNull Call<FlightResponse> call,
                                          @NonNull Throwable t) {
                        onFailureListener.onFailure(new RuntimeException((t.getMessage())));
                    }
                });
    }

    public void getNotice(@NonNull Map<String, String> queries,
                          @NonNull OnResponseListener<NoticeResponse> onResponseListener,
                          @NonNull OnFailureListener onFailureListener) {

        retrofit.getNotice(key, queries)
                .enqueue(new Callback<NoticeResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<NoticeResponse> call,
                                           @NonNull Response<NoticeResponse> response) {

                        NoticeResponse noticeResponse = response.body();
                        String errorMessage = checkResponseException(noticeResponse);
                        if (TextUtils.isEmpty(errorMessage)) {
                            onFailureListener.onFailure(new RuntimeException(errorMessage));
                        }
                        onResponseListener.onResponse(noticeResponse);
                    }

                    @Override
                    public void onFailure(@NonNull Call<NoticeResponse> call,
                                          @NonNull Throwable t) {
                        onFailureListener.onFailure(new RuntimeException((t.getMessage())));
                    }
                });

    }

    private String checkResponseException(@Nullable RemoteResponse response) {

        if (response == null) {
            return "No Response";
        }

        Header header = response.getHeader();
        if (!TextUtils.equals(header.getResultCode(), NORMAL_SERVICE)) {
            String error = header.getResultMsg();
            return error;
        }

        return "";
    }

}
