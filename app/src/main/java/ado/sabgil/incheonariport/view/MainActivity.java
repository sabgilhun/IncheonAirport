package ado.sabgil.incheonariport.view;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import ado.sabgil.incheonariport.R;
import ado.sabgil.incheonariport.databinding.ActivityMainBinding;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    private FragmentManager fragmentManager;
    private HomeFragment homeFragment;
    private CongestionFragment congestionFragment;
    private MyPlaneFragment myPlaneFragment;
    private SettingFragment settingFragment;

    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 뷰 바인딩
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // 프레그먼트 매니저 초기화
        fragmentManager = getSupportFragmentManager();

        // 상태바 색 설정
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);

        // 바텀 네비게이션 초기화
        mBinding.bottomNavigation.setOnNavigationItemSelectedListener(this::switchItem);
        mBinding.bottomNavigation.setSelectedItemId(R.id.action_home);

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
