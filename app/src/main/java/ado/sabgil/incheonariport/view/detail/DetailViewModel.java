package ado.sabgil.incheonariport.view.detail;

import ado.sabgil.incheonariport.data.model.FlightInformation;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DetailViewModel extends ViewModel {
    private final MutableLiveData<FlightInformation> flightInformation;

    DetailViewModel() {
        flightInformation = new MutableLiveData<>();
    }

    public LiveData<FlightInformation> getFlightInformation() {
        return flightInformation;
    }

    public void setFlightInformation(FlightInformation flightInformation) {
        this.flightInformation.setValue(flightInformation);
    }
}
