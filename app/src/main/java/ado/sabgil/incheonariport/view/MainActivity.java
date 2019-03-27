package ado.sabgil.incheonariport.view;

import android.os.Bundle;
import android.view.MenuItem;

import ado.sabgil.incheonariport.R;
import ado.sabgil.incheonariport.databinding.ActivityMainBinding;
import ado.sabgil.incheonariport.view.base.BaseActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private FragmentManager fragmentManager;
    private HomeFragment homeFragment;
    private CongestionFragment congestionFragment;
    private MyPlaneFragment myPlaneFragment;
    private SettingFragment settingFragment;
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 프레그먼트 매니저 초기화
        fragmentManager = getSupportFragmentManager();

        // 바텀 네비게이션 초기화
        getBinding().bottomNavigation.setOnNavigationItemSelectedListener(this::switchItem);
        getBinding().bottomNavigation.setSelectedItemId(R.id.action_home);
    }

    protected int getLayout() {
        return R.layout.activity_main;
    }

    private boolean switchItem(@NonNull MenuItem item) {

        final int selectedAction = item.getItemId();

        switch (selectedAction) {
            case R.id.action_home:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
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
}
