package ado.sabgil.incheonariport;

import android.os.Bundle;
import android.util.Log;

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

        mBinding.btnCongestion.setOnClickListener(__ ->
                handler.getDepartureCongestion("1",
                        response -> mBinding.tvCongestion.setText(response),
                        error -> Log.e("Main", error.getMessage()))
        );

        mBinding.btnWeather.setOnClickListener(__ ->
                handler.getDeparturesWeather(mBinding.etWeather.getText().toString().toUpperCase(),
                        response -> mBinding.tvWeather.setText(response),
                        error -> Log.e("Main", error.getMessage()))
        );
    }
}
