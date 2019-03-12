package ado.sabgil.incheonariport;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import ado.sabgil.incheonariport.databinding.ActivityMainBinding;
import ado.sabgil.incheonariport.remote.openapi.IncheonAirportApiHandler;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    private IncheonAirportApiHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        handler = IncheonAirportApiHandler.getInstance();

        mBinding.btnWeather.setOnClickListener(__ ->
                handler.getDeparturesWeather(mBinding.etWeather.getText().toString().toUpperCase(),
                        response -> mBinding.tvWeather.setText(response),
                        error -> Log.e("Main", error.getMessage()))
        );
    }

    public void onClickUpdateCongestion(View v) {
        handler.getDepartureCongestion("1",
                response -> mBinding.setCg(response),
                error -> Log.e("Main", error.getMessage()));
    }
}
