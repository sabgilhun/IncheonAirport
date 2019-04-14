package ado.sabgil.incheonariport.view.departure;

import android.os.Bundle;
import android.view.View;

import ado.sabgil.incheonariport.R;
import ado.sabgil.incheonariport.adapter.ChartViewAdapter;
import ado.sabgil.incheonariport.databinding.FragmentDepartureBinding;
import ado.sabgil.incheonariport.view.base.BaseFragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class DepartureFragment extends BaseFragment<FragmentDepartureBinding> {

    private DepartureViewModel departureViewModel;

    private ViewPager chartViewPager;


    protected int getLayout() {
        return R.layout.fragment_departure;
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /* ViewModel 초기화 */
        setupViewModel();

        /* View 초기화 */
        setupRefreshButton();
        setupChartViewPager();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            departureViewModel.initDepartureData();
        }
    }

    private void setupViewModel() {
        departureViewModel = getViewModelInActivity(DepartureViewModel.class);
        getBinding().setVm(departureViewModel);
    }

    private void setupRefreshButton() {
        getBinding().ivRefresh.setOnClickListener(__ -> departureViewModel.loadDepartureData());
    }

    private void setupChartViewPager() {
        chartViewPager = getBinding().vpChart;

        // off screen view 최대 갯수 3
        chartViewPager.setOffscreenPageLimit(3);
        chartViewPager.setAdapter(new ChartViewAdapter());

        // 리스너 추가
        chartViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                ChartViewAdapter adapter = (ChartViewAdapter) chartViewPager.getAdapter();

                if (adapter != null && departureViewModel.getNotice().getValue() != null) {
                    adapter.updatePage(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }
}
