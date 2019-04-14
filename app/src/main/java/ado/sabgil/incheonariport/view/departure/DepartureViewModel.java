package ado.sabgil.incheonariport.view.departure;

import android.util.Log;

import ado.sabgil.incheonariport.data.DataManager;
import ado.sabgil.incheonariport.data.DataManagerImpl;
import ado.sabgil.incheonariport.data.model.Terminal1Congestion;
import ado.sabgil.incheonariport.data.model.Terminal1Notice;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DepartureViewModel extends ViewModel {
    private DataManager dataManager;
    private final MutableLiveData<Terminal1Notice> notice;
    private final MutableLiveData<Terminal1Congestion> congestion;

    DepartureViewModel() {
        dataManager = DataManagerImpl.getInstance();
        notice = new MutableLiveData<>();
        congestion = new MutableLiveData<>();
    }

    public LiveData<Terminal1Notice> getNotice() {
        return notice;
    }

    public LiveData<Terminal1Congestion> getCongestion() {
        return congestion;
    }

    public void loadNoticeData() {
        dataManager.getT1PassengerNotice(notice::setValue,
                error -> Log.e("Main", error.getMessage())
        );
    }

    public void loadCongestionData() {
        dataManager.getT1Congestion(congestion::setValue,
                error -> Log.e("Main", error.getMessage()));
    }

    public void loadDepartureData() {
        loadNoticeData();
        loadCongestionData();
    }

    public void initDepartureData() {
        if((notice.getValue() == null || congestion.getValue() == null)) {
            loadDepartureData();
        }
    }
}
