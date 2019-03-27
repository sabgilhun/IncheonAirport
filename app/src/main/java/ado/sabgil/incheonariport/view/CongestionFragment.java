package ado.sabgil.incheonariport.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import ado.sabgil.incheonariport.R;
import ado.sabgil.incheonariport.adapter.ChartViewAdapter;
import ado.sabgil.incheonariport.data.DataManager;
import ado.sabgil.incheonariport.data.DataManagerImpl;
import ado.sabgil.incheonariport.data.model.Terminal1Congestion;
import ado.sabgil.incheonariport.data.model.Terminal1Notice;
import ado.sabgil.incheonariport.databinding.FragmentCongestionBinding;
import ado.sabgil.incheonariport.view.base.BaseFragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class CongestionFragment extends BaseFragment<FragmentCongestionBinding> {

    private DataManager dataManager;
    private Terminal1Notice lineChartData;
    private Terminal1Congestion pieChartData;
    private ViewPager chartViewPager;

    protected int getLayout() {
        return R.layout.fragment_congestion;
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // DataManager 초기화
        dataManager = DataManagerImpl.getInstance();

        // view 초기화
        getBinding().ivRefresh.setOnClickListener(this::onClickRefresh);
        initChartViewPager();

        // 초기 data 로드
        loadNoticeData();
        loadCongestionData();

    }

    private void initChartViewPager() {
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

                if (adapter != null) {
                    adapter.updatePage(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    private void onClickRefresh(View v) {
        loadNoticeData();
        loadCongestionData();
    }

    private void loadNoticeData() {
        dataManager.getT1PassengerNotice(
                result -> {
                    this.lineChartData = result;
                    getBinding().setNt(lineChartData);
                },
                error -> Log.e("Main", error.getMessage())
        );
    }

    private void loadCongestionData() {
        dataManager.getT1Congestion(
                result -> {
                    this.pieChartData = result;
                    getBinding().setCg(pieChartData);
                },
                error -> Log.e("Main", error.getMessage()));
    }
}
