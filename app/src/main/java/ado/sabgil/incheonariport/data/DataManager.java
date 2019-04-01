package ado.sabgil.incheonariport.data;

import java.util.List;

import ado.sabgil.incheonariport.data.model.FlightInformation;
import ado.sabgil.incheonariport.data.model.Terminal1Congestion;
import ado.sabgil.incheonariport.data.model.Terminal1Notice;
import ado.sabgil.incheonariport.data.remote.openapi.IcnAirportApiHelper;
import androidx.annotation.NonNull;

public interface DataManager extends IcnAirportApiHelper {

    void getT1Congestion(@NonNull OnResponseListener<Terminal1Congestion> responseListener,
                         @NonNull OnFailureListener onFailureListener);

    void getFlightInfo(@NonNull OnResponseListener<List<FlightInformation>> onResponseListener,
                       @NonNull OnFailureListener onFailureListener);

    void getFlightInfoWithId(@NonNull String flightId,
                             @NonNull OnResponseListener<List<FlightInformation>> responseListener,
                             @NonNull OnFailureListener onFailureListener);

    void getT1PassengerNotice(@NonNull OnResponseListener<Terminal1Notice> onResponseListener,
                              @NonNull OnFailureListener onFailureListener);

}
