package ado.sabgil.incheonariport.view;

import android.os.Bundle;

import ado.sabgil.incheonariport.R;
import ado.sabgil.incheonariport.databinding.ActivityPlaneSearchBinding;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class PlaneSearchActivity extends AppCompatActivity {

    private ActivityPlaneSearchBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_plane_search);
    }
}
