package ado.sabgil.incheonariport.view;

import android.os.Bundle;
import android.view.View;

import ado.sabgil.incheonariport.R;
import ado.sabgil.incheonariport.data.model.FlightInfo;
import ado.sabgil.incheonariport.databinding.FragmentDetailInfoBinding;
import ado.sabgil.incheonariport.view.base.BaseFragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DetailInfoFragment extends BaseFragment<FragmentDetailInfoBinding> {

    protected int getLayout() {
        return R.layout.fragment_detail_info;
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            FlightInfo info = getArguments().getParcelable("flight_info");
            getBinding().setInfo(info);
        }
    }
}
