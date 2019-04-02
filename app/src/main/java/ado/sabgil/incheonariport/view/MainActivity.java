package ado.sabgil.incheonariport.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import ado.sabgil.incheonariport.R;
import ado.sabgil.incheonariport.data.model.FlightInformation;
import ado.sabgil.incheonariport.databinding.ActivityMainBinding;
import ado.sabgil.incheonariport.view.base.BaseActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static ado.sabgil.incheonariport.Constant.FLIGHT_INFO_ARGUMENT_KEY;
import static ado.sabgil.incheonariport.Constant.SEARCH_REQUEST_CODE;

public class MainActivity extends BaseActivity<ActivityMainBinding>
        implements OnRegisterFlightListener {

    private FragmentManager fragmentManager;
    private HomeFragment homeFragment;
    private CongestionFragment congestionFragment;
    private MyPlaneFragment myPlaneFragment;
    private SettingFragment settingFragment;
    private Fragment currentFragment;

    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* 프레그먼트 매니저 초기화 */
        fragmentManager = getSupportFragmentManager();

        /* 바텀 네비게이션 초기화 */
        getBinding().bottomNavigation.setOnNavigationItemSelectedListener(this::switchItem);
        getBinding().bottomNavigation.setSelectedItemId(R.id.action_home);
    }

    private boolean switchItem(@NonNull MenuItem item) {
        final int selectedAction = item.getItemId();

        switch (selectedAction) {
            case R.id.action_home:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    homeFragment.setRegisterListener(this);
                    replaceFragment(homeFragment, true);
                } else {
                    replaceFragment(homeFragment, false);
                }
                return true;

            case R.id.action_map:
                if (congestionFragment == null) {
                    congestionFragment = new CongestionFragment();
                    replaceFragment(congestionFragment, true);
                } else {
                    replaceFragment(congestionFragment, false);
                }
                return true;

            case R.id.action_alarm:
                if (myPlaneFragment == null) {
                    myPlaneFragment = new MyPlaneFragment();
                    replaceFragment(myPlaneFragment, true);
                } else {
                    replaceFragment(myPlaneFragment, false);
                }
                return true;

            case R.id.action_settings:
                if (settingFragment == null) {
                    settingFragment = new SettingFragment();
                    replaceFragment(settingFragment, true);
                } else {
                    replaceFragment(settingFragment, false);
                }
                return true;
        }
        return false;
    }

    private void replaceFragment(Fragment fragment, boolean isAddFragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (isAddFragment) {
            fragmentTransaction
                    .add(R.id.fl_page_container, fragment, fragment.getClass().getSimpleName());
        }

        if (currentFragment != null) {
            fragmentTransaction
                    .hide(currentFragment).show(fragment);
        }

        fragmentTransaction.commit();
        currentFragment = fragment;
    }

    @Override
    public void onRegisterFlight(FlightInformation information) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SEARCH_REQUEST_CODE
                && resultCode == RESULT_OK
                && data != null) {
            FlightInformation information = data.getParcelableExtra(FLIGHT_INFO_ARGUMENT_KEY);
        }
    }
}
