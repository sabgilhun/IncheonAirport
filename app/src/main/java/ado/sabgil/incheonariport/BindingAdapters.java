package ado.sabgil.incheonariport;

import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.txusballesteros.widgets.FitChart;
import com.txusballesteros.widgets.FitChartValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ado.sabgil.incheonariport.adapter.ChartViewAdapter;
import ado.sabgil.incheonariport.adapter.FlightInfoAdapter;
import ado.sabgil.incheonariport.data.model.GateCongestion;
import ado.sabgil.incheonariport.data.model.GateNotice;
import ado.sabgil.incheonariport.data.model.SimpleFlightInfo;
import ado.sabgil.incheonariport.data.model.Terminal1Notice;
import ado.sabgil.incheonariport.util.LogoFinder;
import ado.sabgil.incheonariport.view.custom.LineChart;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

public class BindingAdapters {

    private static int LIGHT_STATE_COLOR = 0xFF42A5F5;
    private static int NORMAL_STATE_COLOR = 0xFF1E88E5;
    private static int BUSY_STATE_COLOR = 0xFF0D47A1;
    private static int VERY_BUSY_STATE_COLOR = 0xFFE53935;
    private static int CLOSE_STATE_COLOR = 0xFFD0D0D0;

    @BindingAdapter("adapter")
    public static void setFlightInfoItems(@NonNull RecyclerView recyclerView,
                                          @Nullable List<SimpleFlightInfo> items) {
        FlightInfoAdapter adapter
                = (FlightInfoAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.submitList(items);
        }
    }

    @BindingAdapter("adapter")
    public static void setNoticeViews(@NonNull ViewPager viewPager,
                                      @Nullable Terminal1Notice terminal1Notice) {
        ChartViewAdapter adapter
                = (ChartViewAdapter) viewPager.getAdapter();
        if (adapter != null && terminal1Notice != null) {
            adapter.setData(terminal1Notice);
        }
    }

    @BindingAdapter("chart_data")
    public static void setLineChartData(@NonNull LineChart lineChartView,
                                        @Nullable GateNotice data) {
        if (data != null) {
            lineChartView.setData(data.getNotice(), data.getTimeList());
        }
    }

    @BindingAdapter("chart_data")
    public static void setPieChartData(@NonNull FitChart fieChartView,
                                       @Nullable GateCongestion data) {
        if (data != null) {
            Collection<FitChartValue> chartValues = new ArrayList<>();
            float value = data.getPassengers() / 2f;
            int color = CLOSE_STATE_COLOR;

            switch (data.getCongestion()) {
                case LIGHT:
                    color = LIGHT_STATE_COLOR;
                    break;

                case NORMAL:
                    color = NORMAL_STATE_COLOR;
                    break;

                case BUSY:
                    color = BUSY_STATE_COLOR;
                    break;

                case VERY_BUSY:
                    color = VERY_BUSY_STATE_COLOR;
                    break;

                case CLOSE:
                    color = CLOSE_STATE_COLOR;
                    break;
            }
            chartValues.add(new FitChartValue(value, color));
            fieChartView.setValues(chartValues);
        }
    }

    @BindingAdapter("text_color")
    public static void setTextColor(@NonNull TextView textView,
                                    @Nullable GateCongestion data) {
        if (data != null) {
            int color = CLOSE_STATE_COLOR;

            switch (data.getCongestion()) {
                case LIGHT:
                    color = LIGHT_STATE_COLOR;
                    break;

                case NORMAL:
                    color = NORMAL_STATE_COLOR;
                    break;

                case BUSY:
                    color = BUSY_STATE_COLOR;
                    break;

                case VERY_BUSY:
                    color = VERY_BUSY_STATE_COLOR;
                    break;

                case CLOSE:
                    color = CLOSE_STATE_COLOR;
                    break;
            }

            textView.setTextColor(color);
        }
    }

    @BindingAdapter("src")
    public static void setLogoImage(@NonNull ImageView imageView,
                                    @Nullable String flightId) {

        if (!TextUtils.isEmpty(flightId)) {
            int id = LogoFinder.getLogoFromFlightId(imageView.getContext(), flightId);
            if (id == 0) {
                imageView.setImageResource(R.drawable.vector_default_logo);
            } else {
                imageView.setImageResource(id);
            }
        }
    }
}
