package ado.sabgil.incheonariport.adapter;

import android.view.View;

import ado.sabgil.incheonariport.databinding.ItemFlightInfoBinding;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class FlightInfoViewHolder extends RecyclerView.ViewHolder {

    private ItemFlightInfoBinding mBinding;

    public FlightInfoViewHolder(@NonNull View itemView) {
        super(itemView);
        mBinding = DataBindingUtil.bind(itemView);
        mBinding.tvFlightId.setText("임시");
    }

    public ItemFlightInfoBinding getBinding() {
        return mBinding;
    }
}
