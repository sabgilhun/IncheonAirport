package ado.sabgil.incheonariport.view;

import android.os.Bundle;

import ado.sabgil.incheonariport.R;
import ado.sabgil.incheonariport.databinding.ActivityPlaneSearchBinding;
import ado.sabgil.incheonariport.view.base.BaseActivity;
import androidx.annotation.Nullable;

public class PlaneSearchActivity extends BaseActivity<ActivityPlaneSearchBinding> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected int getLayout() {
        return R.layout.activity_plane_search;
    }
}
