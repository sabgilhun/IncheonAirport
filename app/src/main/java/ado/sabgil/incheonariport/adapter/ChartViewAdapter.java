package ado.sabgil.incheonariport.adapter;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ado.sabgil.incheonariport.R;
import ado.sabgil.incheonariport.data.model.Terminal1Notice;
import ado.sabgil.incheonariport.databinding.PagerChartViewBinding;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.PagerAdapter;

public class ChartViewAdapter extends PagerAdapter {
    private SparseArray<PagerChartViewBinding> bindingList;

    public ChartViewAdapter() {
        bindingList = new SparseArray<>();
    }

    public void setData(@NonNull Terminal1Notice terminal1Notice) {
        for (int i = 0; i < getCount(); i++) {
            PagerChartViewBinding binding = bindingList.get(i);
            if (binding != null) {
                binding.setNt(terminal1Notice.getNotices().get(i));
            }
        }
    }

    public void updatePage(int position) {
        if (position > -1) {
            bindingList.get(position).lineChart.animationPlay();
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

        bindingList.append(position, binding);
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
