package ado.sabgil.incheonariport.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ado.sabgil.incheonariport.R;
import ado.sabgil.incheonariport.adapter.FlightInfoAdapter;
import ado.sabgil.incheonariport.databinding.FragmentHomeBinding;
import ado.sabgil.incheonariport.model.SimpleFlightInfo;
import ado.sabgil.incheonariport.remote.openapi.IncheonAirportApiHandler;
import ado.sabgil.incheonariport.remote.openapi.response.PassengerDeparturesWItem;
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

        RecyclerView recyclerView = mBinding.rvHome;
        FlightInfoAdapter adapter = new FlightInfoAdapter();
        recyclerView.setAdapter(adapter);

        List<SimpleFlightInfo> items = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            SimpleFlightInfo item = SimpleFlightInfo.from(new PassengerDeparturesWItem(
                    "1", "" + i, "3", "4", "5",
                    "6", "7", "8", "9", "10",
                    "11", "12", "13", "14", "15",
                    "16"));
            items.add(item);
        }

        mBinding.setItems(items);

        mBinding.lySearchContainer.setOnClickListener(__ -> {
            Intent intent = new Intent(getActivity(), PlaneSearchActivity.class);
            startActivity(intent);

        });
//        mBinding.btnCongestion.setOnClickListener(this::onClickUpdateCongestion);

    }

//    private void onClickUpdateCongestion(View v) {
//        handler.getDepartureCongestion("1",
//                response -> mBinding.setTerminal(response),
//                error -> Log.e("Main", error.getMessage()));
//    }

}
