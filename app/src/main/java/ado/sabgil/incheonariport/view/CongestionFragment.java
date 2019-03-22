package ado.sabgil.incheonariport.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ado.sabgil.incheonariport.R;
import ado.sabgil.incheonariport.data.DataManager;
import ado.sabgil.incheonariport.data.DataManagerImpl;
import ado.sabgil.incheonariport.data.remote.openapi.IcnAirportApiHelperImpl;
import ado.sabgil.incheonariport.databinding.FragmentCongestionBinding;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

public class CongestionFragment extends Fragment {

    private FragmentCongestionBinding mBinding;
    private IcnAirportApiHelperImpl handler;
    private DataManager dataManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_congestion, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataManager = DataManagerImpl.getInstance();
        mBinding.ivRefresh.setOnClickListener(this::onClickUpdateCongestion);
    }

    private void onClickUpdateCongestion(View v) {
        dataManager.getT1Congestion(
                response -> mBinding.setT1(response),
                error -> Log.e("Main", error.getMessage()));
    }
}