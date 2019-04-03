package ado.sabgil.incheonariport.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;

import ado.sabgil.incheonariport.R;
import ado.sabgil.incheonariport.data.model.FlightInformation;
import ado.sabgil.incheonariport.databinding.ActivityMainBinding;
import ado.sabgil.incheonariport.view.base.BaseActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static ado.sabgil.incheonariport.Constant.BottomNavigationIndex.MY_PLANE_FRAGMENT_INDEX;
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

        /* 사용되는 프레그먼트들 초기화*/
        initFragment();

        /* 바텀 네비게이션 초기화 */
        getBinding().bottomNavigation.setOnNavigationItemSelectedListener(this::switchItem);
        getBinding().bottomNavigation.setSelectedItemId(R.id.action_home);
    }

    @Override
    public void onRegisterFlight(@NonNull FlightInformation information) {
        if (!information.equals(myPlaneFragment.getMyFlightInformation())) {
            toggleBadge(MY_PLANE_FRAGMENT_INDEX, true);
            myPlaneFragment.setMyFlightInformation(information);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SEARCH_REQUEST_CODE
                && resultCode == RESULT_OK
                && data != null) {
            FlightInformation information = data.getParcelableExtra(FLIGHT_INFO_ARGUMENT_KEY);
            if (!information.equals(myPlaneFragment.getMyFlightInformation())) {
                toggleBadge(MY_PLANE_FRAGMENT_INDEX, true);
                myPlaneFragment.setMyFlightInformation(information);
            }
        }
    }

    private boolean switchItem(@NonNull MenuItem item) {
        final int selectedAction = item.getItemId();

        switch (selectedAction) {
            case R.id.action_home:
                replaceFragment(homeFragment);
                return true;

            case R.id.action_map:
                replaceFragment(congestionFragment);
                return true;

            case R.id.action_alarm:
                toggleBadge(MY_PLANE_FRAGMENT_INDEX, false);
                replaceFragment(myPlaneFragment);
                return true;

            case R.id.action_settings:
                replaceFragment(settingFragment);
                return true;
        }
        return false;
    }

    private void initFragment() {
        /* FragmentManager 초기화 */
        fragmentManager = getSupportFragmentManager();

        /* HomeFragment 초기화 */
        homeFragment = new HomeFragment();
        homeFragment.setRegisterListener(this);

        /* CongestionFragment 초기화 */
        congestionFragment = new CongestionFragment();

        /* MyPlaneFragment 초기화 */
        myPlaneFragment = new MyPlaneFragment();

        /* SettingFragment 초기화 */
        settingFragment = new SettingFragment();

        /* 탭으로 사용될 프레그먼트 모두 등록 후 숨김*/
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .add(R.id.fl_page_container, homeFragment,
                        homeFragment.getClass().getSimpleName())
                .add(R.id.fl_page_container, congestionFragment,
                        congestionFragment.getClass().getSimpleName())
                .add(R.id.fl_page_container, myPlaneFragment,
                        myPlaneFragment.getClass().getSimpleName())
                .add(R.id.fl_page_container, settingFragment,
                        settingFragment.getClass().getSimpleName())
                .hide(homeFragment)
                .hide(congestionFragment)
                .hide(myPlaneFragment)
                .hide(settingFragment)
                .commit();
    }

    private void replaceFragment(@NonNull Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (currentFragment != null) {
            fragmentTransaction
                    .hide(currentFragment).show(fragment);
        } else {
            fragmentTransaction.show(fragment);
        }

        fragmentTransaction.commit();
        currentFragment = fragment;
    }

    private void toggleBadge(int index, boolean isSetting) {
        BottomNavigationMenuView menuView =
                (BottomNavigationMenuView) getBinding().bottomNavigation.getChildAt(0);

        View v = menuView.getChildAt(index);
        BottomNavigationItemView itemView = (BottomNavigationItemView) v;

        View badge = itemView.findViewById(R.id.notification_badge);
        if (isSetting && badge == null) {
            LayoutInflater.from(this)
                    .inflate(R.layout.layout_notification_badge, itemView, true);
        } else if (!isSetting && badge != null) {
            itemView.removeView(badge);
        }
    }
}
