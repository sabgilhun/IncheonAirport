package ado.sabgil.incheonariport;

import android.widget.TextView;

import com.txusballesteros.widgets.FitChart;
import com.txusballesteros.widgets.FitChartValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ado.sabgil.incheonariport.adapter.FlightInfoAdapter;
import ado.sabgil.incheonariport.model.GateCongestion;
import ado.sabgil.incheonariport.model.SimpleFlightInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class BindingAdapters {

    private static int LIGHT_STATE_COLOR = 0xFF96EAC6;
    private static int NORMAL_STATE_COLOR = 0xFF25B97A;
    private static int BUSY_STATE_COLOR = 0xFFC5300D;
    private static int CLOSE_STATE_COLOR = 0xFFD0D0D0;

    @BindingAdapter("adapter")
    public static void setImageItems(@NonNull RecyclerView recyclerView,
                                     @Nullable List<SimpleFlightInfo> items) {
        FlightInfoAdapter adapter
                = (FlightInfoAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.submitList(items);
        }
    }

    @BindingAdapter("chart_data")
    public static void setChartData(@NonNull FitChart chartView,
                                    @Nullable GateCongestion info) {
        if (info != null) {
            Collection<FitChartValue> chartValues = new ArrayList<>();
            float value = info.getPassengers() / 2f;
            int color = CLOSE_STATE_COLOR;

            switch (info.getCongestion()) {
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
                    color = BUSY_STATE_COLOR;
                    break;

                case CLOSE:
                    color = CLOSE_STATE_COLOR;
                    break;
            }
            chartValues.add(new FitChartValue(value, color));
            chartView.setValues(chartValues);
        }
    }

    @BindingAdapter("text_color")
    public static void setTextColor(@NonNull TextView textView,
                                    @Nullable GateCongestion info) {
        if (info != null) {
            int color = CLOSE_STATE_COLOR;

            switch (info.getCongestion()) {
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
                    color = BUSY_STATE_COLOR;
                    break;

                case CLOSE:
                    color = CLOSE_STATE_COLOR;
                    break;
            }

            textView.setTextColor(color);
        }
    }
}