package ado.sabgil.incheonariport.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import ado.sabgil.incheonariport.R;
import ado.sabgil.incheonariport.adapter.FlightInfoAdapter;
import ado.sabgil.incheonariport.data.DataManager;
import ado.sabgil.incheonariport.data.DataManagerImpl;
import ado.sabgil.incheonariport.databinding.FragmentHomeBinding;
import ado.sabgil.incheonariport.view.base.BaseFragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class HomeFragment extends BaseFragment<FragmentHomeBinding> {
    private DataManager dataManager;

    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // DataManager 초기화
        dataManager = DataManagerImpl.getInstance();

        // view 초기화
        getBinding().etSearch.setOnClickListener(this::onClickSearch);
        initRecyclerView();

        // 초기 data 로드
        updateFlightData();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = getBinding().rvQueriedWithTime;
        FlightInfoAdapter adapter = new FlightInfoAdapter();
        adapter.setOnItemClickListener((v, position) -> {
        });
        recyclerView.setAdapter(adapter);
    }

    private void onClickSearch(View v) {
        Intent intent = new Intent(getContext(), PlaneSearchActivity.class);
        startActivity(intent);
    }

    private void updateFlightData() {
        dataManager.getFlightInfo(
                response -> getBinding().setItems(response),
                error -> Log.e("networking", error.getMessage()));
    }
}
