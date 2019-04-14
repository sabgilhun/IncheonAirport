package ado.sabgil.incheonariport.view.base;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

public abstract class BaseActivity<B extends ViewDataBinding> extends AppCompatActivity {

    private B binding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, getLayout());
        binding.setLifecycleOwner(this);

        /* 상태바 색 설정 */
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);
    }

    protected <VM extends ViewModel> VM getViewModel(Class<VM> vmClass){
        return ViewModelProviders.of(this).get(vmClass);
    }

    protected B getBinding() {
        return binding;
    }

    protected abstract int getLayout();
}
