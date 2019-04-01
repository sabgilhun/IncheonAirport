package ado.sabgil.incheonariport.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import ado.sabgil.incheonariport.R;
import ado.sabgil.incheonariport.data.model.FlightInformation;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class FlightInfoAdapter extends ListAdapter<FlightInformation, FlightInfoViewHolder> {

    private OnItemClickListener itemClickListener;

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
        holder.getBinding().lyItem.setOnClickListener(v ->
                itemClickListener.onItemClick(holder, position));
    }

    @Override
    public FlightInformation getItem(int position) {
        return super.getItem(position);
    }

    private static final DiffUtil.ItemCallback<FlightInformation> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<FlightInformation>() {
                @Override
                public boolean areItemsTheSame(@NonNull FlightInformation oldItem,
                                               @NonNull FlightInformation newItem) {
                    return oldItem.getFlightId().equals(newItem.getFlightId());
                }

                @Override
                public boolean areContentsTheSame(@NonNull FlightInformation oldItem,
                                                  @NonNull FlightInformation newItem) {
                    return oldItem.equals(newItem);
                }
            };

    public interface OnItemClickListener {
        void onItemClick(RecyclerView.ViewHolder viewHolder, int position);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
