package ado.sabgil.incheonariport.view.home;

import android.util.Log;

import java.util.List;

import ado.sabgil.incheonariport.data.DataManager;
import ado.sabgil.incheonariport.data.DataManagerImpl;
import ado.sabgil.incheonariport.data.model.FlightInformation;
import ado.sabgil.incheonariport.util.TimeUtils;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final DataManager dataManager;

    private final MutableLiveData<List<FlightInformation>> flightInformations;

    private final MutableLiveData<String> updateTime;


    HomeViewModel() {
        dataManager = DataManagerImpl.getInstance();
        flightInformations = new MutableLiveData<>();
        updateTime = new MutableLiveData<>();
    }

    public LiveData<List<FlightInformation>> getFlightInformations() {
        return flightInformations;
    }

    public LiveData<String> getUpdateTime() {
        return updateTime;
    }

    public void updateFlightData() {
        updateTime.setValue(TimeUtils.getCurrentDateFullFormat());

        dataManager.getFlightInfo(flightInformations::setValue,
                error -> Log.e("networking", error.getMessage()));
    }
}
