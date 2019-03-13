package ado.sabgil.incheonariport;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

import ado.sabgil.incheonariport.databinding.ActivityMainBinding;
import ado.sabgil.incheonariport.remote.openapi.IncheonAirportApiHandler;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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
            Timer timer;

            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (timer != null) {
                    timer.cancel();
                }

                if (newText.length() > 1) {
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            handler.getPassengerDeparturesW(newText.toUpperCase(),
                                    response -> Log.d("timetest", response.get(0).toString()),
                                    error -> Log.e("Main", error.getMessage()));
                        }
                    }, 400);
                }
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
