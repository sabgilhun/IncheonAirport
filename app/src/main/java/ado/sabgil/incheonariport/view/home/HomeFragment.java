package ado.sabgil.incheonariport.view.home;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import ado.sabgil.incheonariport.R;
import ado.sabgil.incheonariport.adapter.FlightInfoAdapter;
import ado.sabgil.incheonariport.databinding.FragmentHomeBinding;
import ado.sabgil.incheonariport.util.SoftKeyboardUtils;
import ado.sabgil.incheonariport.view.flightsearch.FlightSearchFragment;
import ado.sabgil.incheonariport.view.base.BaseFragment;
import ado.sabgil.incheonariport.view.detail.DetailFragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeFragment extends BaseFragment<FragmentHomeBinding> {
    private HomeViewModel homeViewModel;
    private FragmentManager fragmentManager;

    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /* ViewModel 초기화 */
        setupViewModel();

        /* FragmentManager 초기화 */
        fragmentManager = getFragmentManager();

        /* View 초기화 */
        setupSearchButton();
        setupRefreshButton();
        setupRecyclerView();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden && homeViewModel.getFlightInformations().getValue() == null) {
            /* 초기 data 로드 */
            homeViewModel.updateFlightData();
        }
    }

    private void setupViewModel() {
        homeViewModel = getViewModelInActivity(HomeViewModel.class);
        getBinding().setVm(homeViewModel);
    }

    private void setupSearchButton() {
        getBinding().etSearch.setOnClickListener(__ -> {
            FlightSearchFragment fragment = FlightSearchFragment.newInstance();
            fragment.ifNotAddedShow(fragmentManager);
        });
    }

    private void setupRefreshButton() {
        getBinding().ivRefresh.setOnClickListener(__ -> homeViewModel.updateFlightData());
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = getBinding().rvQueriedWithTime;
        FlightInfoAdapter adapter = new FlightInfoAdapter();

        adapter.setOnItemClickListener((v, position) -> {
            /* 상세 정보 다이얼로그 표시 및 버튼 리스너 설정 */
            DetailFragment fragment = DetailFragment.newInstance(adapter.getItem(position));
            fragment.ifNotAddedShow(fragmentManager);
        });

        recyclerView.setAdapter(adapter);
    }
}
