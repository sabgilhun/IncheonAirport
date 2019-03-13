package ado.sabgil.incheonariport;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.SearchView;

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
        setSupportActionBar(mBinding.tbMain);
        handler = IncheonAirportApiHandler.getInstance();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.onActionViewCollapsed();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                handler.getDeparturesWeather(query.toUpperCase(),
//                        response -> mBinding.tvWeather.setText(response),
//                        error -> Log.e("Main", error.getMessage()));

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });

        return true;
    }

    public void onClickUpdateCongestion(View v) {
        handler.getDepartureCongestion("1",
                response -> mBinding.setCg(response),
                error -> Log.e("Main", error.getMessage()));
    }
}
