package ado.sabgil.incheonariport.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ado.sabgil.incheonariport.R;
import ado.sabgil.incheonariport.data.model.GateNotice;
import ado.sabgil.incheonariport.data.model.Terminal1Notice;
import ado.sabgil.incheonariport.databinding.PagerChartViewBinding;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.PagerAdapter;

public class ChartViewAdapter extends PagerAdapter {

    private Terminal1Notice terminal1Notice;

    public void setData(@NonNull Terminal1Notice terminal1Notice) {
        this.terminal1Notice = terminal1Notice;
    }

    public void updatePage(int position, @NonNull View view) {
        PagerChartViewBinding binding = DataBindingUtil.findBinding(view);

        if (binding != null && position > -1) {
            GateNotice notice = terminal1Notice.getNotices().get(position);
            binding.lineChart.setData(notice.getNotice(), notice.getTimeList());
            binding.tvGateNo.getText();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());

        PagerChartViewBinding binding = DataBindingUtil.
                bind(inflater.inflate(R.layout.pager_chart_view, container, false));
        if (binding == null) {
            return container;
        }

        binding.tvGateNo.setText(position + "");
        container.addView(binding.getRoot());
        return binding.getRoot();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = (View) object;
        container.removeView(view);
        container.invalidate();
    }
}
