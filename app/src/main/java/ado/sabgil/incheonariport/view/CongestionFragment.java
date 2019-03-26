package ado.sabgil.incheonariport.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ado.sabgil.incheonariport.R;
import ado.sabgil.incheonariport.adapter.ChartViewAdapter;
import ado.sabgil.incheonariport.data.DataManager;
import ado.sabgil.incheonariport.data.DataManagerImpl;
import ado.sabgil.incheonariport.data.model.Terminal1Congestion;
import ado.sabgil.incheonariport.data.model.Terminal1Notice;
import ado.sabgil.incheonariport.databinding.FragmentCongestionBinding;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class CongestionFragment extends Fragment {

    private FragmentCongestionBinding mBinding;
    private DataManager dataManager;
    private Terminal1Notice lineChartData;
    private Terminal1Congestion pieChartData;

    private ViewPager chartViewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_congestion, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // DataManager 객체 가져오기
        dataManager = DataManagerImpl.getInstance();

        // View 초기화
        mBinding.ivRefresh.setOnClickListener(this::onClickUpdateCongestion);
        initChartViewPager();

        // Data 로드 및 설정
        if (lineChartData == null || pieChartData == null) {
            loadNoticeData();
            loadCongestionData();
        } else {
            setLineChartData();
            setPieChartData();
        }
    }

    private void initChartViewPager() {
        chartViewPager = mBinding.vpChart;

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
                View page = chartViewPager.getChildAt(position);
                ChartViewAdapter adapter = (ChartViewAdapter) chartViewPager.getAdapter();

                if (adapter != null && page != null) {
                    adapter.updatePage(position, page);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    private void onClickUpdateCongestion(View v) {
        loadNoticeData();
        loadCongestionData();
    }

    private void loadNoticeData() {
        dataManager.getT1PassengerNotice(
                result -> {
                    this.lineChartData = result;
                    setLineChartData();
                },
                error -> Log.e("Main", error.getMessage())
        );
    }

    private void loadCongestionData() {
        dataManager.getT1Congestion(
                result -> {
                    this.pieChartData = result;
                    setPieChartData();
                },
                error -> Log.e("Main", error.getMessage()));
    }

    private void setLineChartData() {
        new Thread(() -> {
            if (getActivity() != null) {
                getActivity().runOnUiThread(() -> mBinding.setNt(lineChartData));
            }
        }).start();
    }

    private void setPieChartData() {
        new Thread(() -> {
            if (getActivity() != null) {
                getActivity().runOnUiThread(() -> mBinding.setCg(pieChartData));
            }
        }).start();
    }
}
