package ado.sabgil.incheonariport.view.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;

import java.util.ArrayList;
import java.util.List;

import ado.sabgil.incheonariport.R;
import ado.sabgil.incheonariport.databinding.ActivityMainBinding;
import ado.sabgil.incheonariport.view.base.BaseActivity;
import ado.sabgil.incheonariport.view.departure.DepartureFragment;
import ado.sabgil.incheonariport.view.home.HomeFragment;
import ado.sabgil.incheonariport.view.myflight.MyFlightFragment;
import ado.sabgil.incheonariport.view.myflight.MyFlightViewModel;
import ado.sabgil.incheonariport.view.setting.SettingFragment;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static ado.sabgil.incheonariport.Constant.BottomNavigationIndex.CONGESTION_FRAGMENT_INDEX;
import static ado.sabgil.incheonariport.Constant.BottomNavigationIndex.HOME_FRAGMENT_INDEX;
import static ado.sabgil.incheonariport.Constant.BottomNavigationIndex.MY_PLANE_FRAGMENT_INDEX;
import static ado.sabgil.incheonariport.Constant.BottomNavigationIndex.SETTINGS_FRAGMENT_INDEX;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private MainViewModel mainViewModel;

    private MyFlightViewModel myFlightViewModel;

    private FragmentManager fragmentManager;

    private List<Fragment> bottomNavFragmentList;

    private Fragment currentFragment;


    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainViewModel = getViewModel(MainViewModel.class);
        myFlightViewModel = getViewModel(MyFlightViewModel.class);

        /* View 초기화 */
        setupFragment();
        setupBottomNavigation();

        /* position 변수 observe */
        mainViewModel.getCurrentPagePosition().observe(this,
                pos -> replaceFragment(bottomNavFragmentList.get(pos))
        );

        /* badge flag 변수 observe */
        myFlightViewModel.getBadgeFlagMyPlane().observe(this,
                flag -> toggleBadge(MY_PLANE_FRAGMENT_INDEX, flag)
        );
    }

    private void setupBottomNavigation() {
        getBinding().bottomNavigation.setOnNavigationItemSelectedListener(this::switchItem);
        getBinding().bottomNavigation.setSelectedItemId(R.id.action_home);
    }

    private boolean switchItem(@NonNull MenuItem item) {
        final int selectedAction = item.getItemId();

        switch (selectedAction) {
            case R.id.action_home:
                mainViewModel.setCurrentPagePosition(HOME_FRAGMENT_INDEX);
                return true;

            case R.id.action_map:
                mainViewModel.setCurrentPagePosition(CONGESTION_FRAGMENT_INDEX);
                return true;

            case R.id.action_alarm:
                mainViewModel.setCurrentPagePosition(MY_PLANE_FRAGMENT_INDEX);
                return true;

            case R.id.action_settings:
                mainViewModel.setCurrentPagePosition(SETTINGS_FRAGMENT_INDEX);
                return true;
        }
        return false;
    }

    private void setupFragment() {
        /* FragmentManager 초기화 */
        bottomNavFragmentList = new ArrayList<>();
        fragmentManager = getSupportFragmentManager();

        /* HomeFragment 초기화 */
        bottomNavFragmentList.add(new HomeFragment());

        /* DepartureFragment 초기화 */
        bottomNavFragmentList.add(new DepartureFragment());

        /* MyFlightFragment 초기화 */
        bottomNavFragmentList.add(new MyFlightFragment());

        /* SettingFragment 초기화 */
        bottomNavFragmentList.add(new SettingFragment());

        /* 탭으로 사용될 프레그먼트 모두 등록 후 숨김*/
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .add(R.id.fl_page_container, bottomNavFragmentList.get(HOME_FRAGMENT_INDEX),
                        HomeFragment.class.getSimpleName())
                .add(R.id.fl_page_container, bottomNavFragmentList.get(CONGESTION_FRAGMENT_INDEX),
                        DepartureFragment.class.getSimpleName())
                .add(R.id.fl_page_container, bottomNavFragmentList.get(MY_PLANE_FRAGMENT_INDEX),
                        MyFlightFragment.class.getSimpleName())
                .add(R.id.fl_page_container, bottomNavFragmentList.get(SETTINGS_FRAGMENT_INDEX),
                        SettingFragment.class.getSimpleName());

        for (Fragment fg : bottomNavFragmentList) {
            fragmentTransaction.hide(fg);
        }

        fragmentTransaction.commit();
    }

    private void replaceFragment(@NonNull Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (currentFragment != null) {
            fragmentTransaction
                    .hide(currentFragment)
                    .show(fragment);
        } else {
            fragmentTransaction.show(fragment);
        }
        fragmentTransaction.commit();
        currentFragment = fragment;
    }

    private void toggleBadge(int index, boolean isSetting) {
        BottomNavigationMenuView menuView;
        menuView = (BottomNavigationMenuView) getBinding().bottomNavigation.getChildAt(0);

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
