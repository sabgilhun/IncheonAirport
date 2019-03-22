package ado.sabgil.incheonariport;

import android.widget.TextView;

import com.txusballesteros.widgets.FitChart;
import com.txusballesteros.widgets.FitChartValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ado.sabgil.incheonariport.adapter.FlightInfoAdapter;
import ado.sabgil.incheonariport.data.model.GateCongestion;
import ado.sabgil.incheonariport.data.model.SimpleFlightInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class BindingAdapters {

    private static int LIGHT_STATE_COLOR = 0xFF42A5F5;
    private static int NORMAL_STATE_COLOR = 0xFF1E88E5;
    private static int BUSY_STATE_COLOR = 0xFF0D47A1;
    private static int VERY_BUSY_STATE_COLOR = 0xFFE53935;
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
                    color = VERY_BUSY_STATE_COLOR;
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
                    color = VERY_BUSY_STATE_COLOR;
                    break;

                case CLOSE:
                    color = CLOSE_STATE_COLOR;
                    break;
            }

            textView.setTextColor(color);
        }
    }
}
