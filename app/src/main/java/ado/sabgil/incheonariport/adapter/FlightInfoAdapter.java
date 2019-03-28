package ado.sabgil.incheonariport.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import ado.sabgil.incheonariport.R;
import ado.sabgil.incheonariport.data.model.SimpleFlightInfo;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class FlightInfoAdapter extends ListAdapter<SimpleFlightInfo, FlightInfoViewHolder> {

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
    public SimpleFlightInfo getItem(int position) {
        return super.getItem(position);
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

    public interface OnItemClickListener {
        void onItemClick(RecyclerView.ViewHolder viewHolder, int position);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
