package ado.sabgil.incheonariport.view;

import android.os.Bundle;
import android.view.View;

import ado.sabgil.incheonariport.R;
import ado.sabgil.incheonariport.data.model.FlightInformation;
import ado.sabgil.incheonariport.databinding.FragmentMyPlaneBinding;
import ado.sabgil.incheonariport.view.base.BaseFragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyPlaneFragment extends BaseFragment<FragmentMyPlaneBinding> {
    private FlightInformation myFlightInformation;

    protected int getLayout() {
        return R.layout.fragment_my_plane;
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            getBinding().setInfo(myFlightInformation);
        }
    }

    public FlightInformation getMyFlightInformation() {
        return myFlightInformation;
    }

    @SuppressWarnings("WeakerAccess")
    public void setMyFlightInformation(FlightInformation myFlightInformation) {
        this.myFlightInformation = myFlightInformation;
    }
}
