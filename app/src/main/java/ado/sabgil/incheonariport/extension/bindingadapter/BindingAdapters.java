package ado.sabgil.incheonariport.extension.bindingadapter;

import java.util.List;

import ado.sabgil.incheonariport.adapter.FlightInfoAdapter;
import ado.sabgil.incheonariport.model.SimpleFlightInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class BindingAdapters {

    @SuppressWarnings("unchecked")
    @BindingAdapter("adapter")
    public static void setImageItems(@NonNull RecyclerView recyclerView,
                                     @Nullable List<SimpleFlightInfo> items) {
        FlightInfoAdapter adapter
                = (FlightInfoAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.submitList(items);
        }
    }
}
