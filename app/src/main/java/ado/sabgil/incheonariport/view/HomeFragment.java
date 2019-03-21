package ado.sabgil.incheonariport.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ado.sabgil.incheonariport.R;
import ado.sabgil.incheonariport.adapter.FlightInfoAdapter;
import ado.sabgil.incheonariport.databinding.FragmentHomeBinding;
import ado.sabgil.incheonariport.remote.openapi.IncheonAirportApiHandler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding mBinding;
    private IncheonAirportApiHandler handler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,
                container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        handler = IncheonAirportApiHandler.getInstance();

        RecyclerView recyclerView = mBinding.rvQueriedWithTime;
        FlightInfoAdapter adapter = new FlightInfoAdapter();
        recyclerView.setAdapter(adapter);
    }
}
