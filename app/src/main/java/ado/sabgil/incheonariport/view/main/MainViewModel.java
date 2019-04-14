package ado.sabgil.incheonariport.view.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<Integer> currentPagePosition;

    MainViewModel() {
        currentPagePosition = new MutableLiveData<>();
    }

    public LiveData<Integer> getCurrentPagePosition() {
        return currentPagePosition;
    }

    public void setCurrentPagePosition(int currentPagePosition) {
        this.currentPagePosition.setValue(currentPagePosition);
    }
}
