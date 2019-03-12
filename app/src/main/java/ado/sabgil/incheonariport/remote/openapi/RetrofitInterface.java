package ado.sabgil.incheonariport.remote.openapi;

import ado.sabgil.incheonariport.remote.openapi.response.DeparturesCongestionResponse;
import ado.sabgil.incheonariport.remote.openapi.response.PassengerDeparturesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @GET(value = "StatusOfDepartures/getDeparturesCongestion")
    Call<DeparturesCongestionResponse> getDeparturesCongestion(
            @Query(value = "ServiceKey", encoded = true) String key,
            @Query(value = "terno") String terminalNumber);

    @GET(value = "StatusOfPassengerFlights/getPassengerDepartures")
    Call<PassengerDeparturesResponse> getPassengerDepartures(
            @Query(value = "ServiceKey", encoded = true) String key,
            @Query(value = "flight_id") String flightID);
}
