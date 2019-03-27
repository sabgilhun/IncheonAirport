package ado.sabgil.incheonariport.view;

import android.os.Bundle;
import android.view.View;

import ado.sabgil.incheonariport.R;
import ado.sabgil.incheonariport.databinding.FragmentEmptyBinding;
import ado.sabgil.incheonariport.view.base.BaseFragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SettingFragment extends BaseFragment<FragmentEmptyBinding> {

    protected int getLayout() {
        return R.layout.fragment_empty;
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
