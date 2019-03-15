package ado.sabgil.incheonariport.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import ado.sabgil.incheonariport.R;
import ado.sabgil.incheonariport.model.SimpleFlightInfo;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class FlightInfoAdapter extends ListAdapter<SimpleFlightInfo, FlightInfoViewHolder> {

    public FlightInfoAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public FlightInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FlightInfoViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_flight_info, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FlightInfoViewHolder holder, int position) {
        holder.getBinding().setInfo(getItem(position));
    }

    private static final DiffUtil.ItemCallback<SimpleFlightInfo> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<SimpleFlightInfo>() {
                @Override
                public boolean areItemsTheSame(@NonNull SimpleFlightInfo oldItem,
                                               @NonNull SimpleFlightInfo newItem) {
                    return oldItem.getFlightID().equals(newItem.getFlightID());
                }

                @Override
                public boolean areContentsTheSame(@NonNull SimpleFlightInfo oldItem,
                                                  @NonNull SimpleFlightInfo newItem) {
                    return oldItem.equals(newItem);
                }
            };
}
