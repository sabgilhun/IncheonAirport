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
import ado.sabgil.incheonariport.util.TimeUtils;
import ado.sabgil.incheonariport.view.base.BaseFragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeFragment extends BaseFragment<FragmentHomeBinding> {
    private DataManager dataManager;
    private FragmentManager fragmentManager;

    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /* FragmentManager 초기화 */
        fragmentManager = getFragmentManager();

        /* DataManager 초기화 */
        dataManager = DataManagerImpl.getInstance();

        /* view 초기화 */
        getBinding().etSearch.setOnClickListener(this::onClickSearch);
        getBinding().ivRefresh.setOnClickListener(__ -> updateFlightData());
        initRecyclerView();

        /* 초기 data 로드 */
        updateFlightData();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = getBinding().rvQueriedWithTime;
        FlightInfoAdapter adapter = new FlightInfoAdapter();

        adapter.setOnItemClickListener((v, position) -> {
            DetailFragment fragment = DetailFragment.newInstance(adapter.getItem(position));
            fragment.ifNotAddedShow(fragmentManager);
        });

        recyclerView.setAdapter(adapter);
    }

    private void onClickSearch(View v) {
        Intent intent = new Intent(getContext(), PlaneSearchActivity.class);
        startActivity(intent);
    }

    private void updateFlightData() {
        String updateTime = getString(R.string.update_time, TimeUtils.getCurrentDateFullFormat());
        getBinding().tvUpdateTime.setText(updateTime);

        dataManager.getFlightInfo(
                response -> getBinding().setItems(response),
                error -> Log.e("networking", error.getMessage()));
    }
}
