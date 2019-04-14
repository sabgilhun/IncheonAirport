package ado.sabgil.incheonariport.view.myflight;

import ado.sabgil.incheonariport.data.model.FlightInformation;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyFlightViewModel extends ViewModel {

    private final MutableLiveData<FlightInformation> flightInformation;

    private final MutableLiveData<Boolean> badgeFlagMyPlane;

    MyFlightViewModel() {
        flightInformation = new MutableLiveData<>();
        badgeFlagMyPlane = new MutableLiveData<>();
        badgeFlagMyPlane.setValue(false);
    }

    public LiveData<FlightInformation> getFlightInformation() {
        return flightInformation;
    }

    public LiveData<Boolean> getBadgeFlagMyPlane() {
        return badgeFlagMyPlane;
    }

    public void setFlightInformation(FlightInformation flightInformation) {
        FlightInformation oldData = this.flightInformation.getValue();

        if (oldData == null || !(oldData.equals(flightInformation))) {
            this.flightInformation.setValue(flightInformation);
            badgeFlagMyPlane.setValue(true);
        }
    }

    public void clearBadge() {
        badgeFlagMyPlane.setValue(false);
    }
}
