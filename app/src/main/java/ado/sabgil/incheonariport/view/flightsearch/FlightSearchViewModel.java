package ado.sabgil.incheonariport.view.flightsearch;

import android.text.TextUtils;
import android.util.Log;

import java.util.List;

import ado.sabgil.incheonariport.data.DataManager;
import ado.sabgil.incheonariport.data.DataManagerImpl;
import ado.sabgil.incheonariport.data.model.FlightInformation;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FlightSearchViewModel extends ViewModel {

    public enum Status {DEFAULT, NO_RESPONSE, SHORT_QUERY, SUCCESS}

    public static int SHORT_TEXT_CRITERION = 2;

    private final DataManager dataManager;

    private final MutableLiveData<List<FlightInformation>> flightInformations;

    private final MutableLiveData<Status> status;

    private String lastQuery;


    FlightSearchViewModel() {
        dataManager = DataManagerImpl.getInstance();
        flightInformations = new MutableLiveData<>();
        status = new MutableLiveData<>();
        status.setValue(Status.DEFAULT);
        lastQuery = "";
    }

    public String getLastQuery() {
        return lastQuery;
    }

    public LiveData<List<FlightInformation>> getFlightInformations() {
        return flightInformations;
    }

    public LiveData<Status> getStatus() {
        return status;
    }

    public void loadFlightData(@NonNull String query) {
        lastQuery = query;

        if (query.length() == 0) {
            status.postValue(Status.DEFAULT);
            return;
        } else if (query.length() < SHORT_TEXT_CRITERION) {
            status.postValue(Status.SHORT_QUERY);
            return;
        }

        dataManager.getFlightInfoWithId(query.toUpperCase(),
                result -> {
                    flightInformations.setValue(result);
                    status.postValue(Status.SUCCESS);
                },
                error -> {
                    if (TextUtils.equals(error.getMessage(), "No Response")) {
                        status.postValue(Status.NO_RESPONSE);
                    } else {
                        Log.e("networking", error.getMessage());
                    }
                }
        );
    }
}
