package ado.sabgil.incheonariport;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import ado.sabgil.incheonariport.custumview.MySearchView;
import ado.sabgil.incheonariport.databinding.ActivityMainBinding;
import ado.sabgil.incheonariport.model.SimpleFlightInfo;
import ado.sabgil.incheonariport.remote.openapi.IncheonAirportApiHandler;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    private IncheonAirportApiHandler handler;

    private FragmentManager fragmentManager;
    private HomeFragment homeFragment;
    private MapFragment mapFragment;
    private AlarmFragment alarmFragment;
    private SettingFragment settingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.bottomNavigation.setOnNavigationItemSelectedListener(this::switchItem);
        setSupportActionBar(mBinding.tbMain);
        handler = IncheonAirportApiHandler.getInstance();

        // 프레그먼트 초기화
        fragmentManager = getSupportFragmentManager();
        homeFragment = new HomeFragment();
        mapFragment = new MapFragment();
        alarmFragment = new AlarmFragment();
        settingFragment = new SettingFragment();

        // 첫 화면 프레그먼트 추가
        fragmentManager.beginTransaction()
                .add(R.id.fl_container, homeFragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        MySearchView searchView = (MySearchView) searchItem.getActionView();

        searchView.addDebounceOnListener();
        searchView.setOnDebouncedQueryTextListener(query ->
                handler.getPassengerDeparturesW(query.toUpperCase(),
                        this::onResponseSimpleFlightInfo,
                        error -> Log.e("Main", error.getMessage()))
        );

        searchView.setOnSearchViewChangedListener(isExpand -> {
            if (isExpand) {
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.fl_container,
                                new SearchListFragment(),
                                SearchListFragment.class.getSimpleName())
                        .addToBackStack(null)
                        .commit();
            } else {
                onBackPressed();
            }
        });

        return true;
    }

    private boolean switchItem(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                fragmentManager.beginTransaction()
                        .replace(R.id.fl_container, homeFragment)
                        .commit();
                return true;

            case R.id.action_map:
                fragmentManager.beginTransaction()
                        .replace(R.id.fl_container, mapFragment)
                        .commit();
                return true;

            case R.id.action_alarm:
                fragmentManager.beginTransaction()
                        .replace(R.id.fl_container, alarmFragment)
                        .commit();
                return true;

            case R.id.action_settings:
                fragmentManager.beginTransaction()
                        .replace(R.id.fl_container, settingFragment)
                        .commit();
                return true;
        }
        return false;
    }

    private void onResponseSimpleFlightInfo(List<SimpleFlightInfo> response) {
        SearchListFragment fragment;
        fragment = (SearchListFragment) fragmentManager.
                findFragmentByTag(SearchListFragment.class.getSimpleName());

        if (fragment != null) {
            fragment.setListItem(response);
        }
    }
}
