package ado.sabgil.incheonariport.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ado.sabgil.incheonariport.R;
import ado.sabgil.incheonariport.databinding.FragmentCongestionBinding;
import ado.sabgil.incheonariport.remote.openapi.IncheonAirportApiHandler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

public class CongestionFragment extends Fragment {

    private FragmentCongestionBinding mBinding;
    private IncheonAirportApiHandler handler;

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
        handler = IncheonAirportApiHandler.getInstance();
        mBinding.ivRefresh.setOnClickListener(this::onClickUpdateCongestion);
    }

    private void onClickUpdateCongestion(View v) {
        handler.getDepartureCongestion("1",
                response -> mBinding.setT1(response),
                error -> Log.e("Main", error.getMessage()));
    }
}
