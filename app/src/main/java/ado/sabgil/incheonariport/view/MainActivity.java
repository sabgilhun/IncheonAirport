package ado.sabgil.incheonariport.view;

import android.os.Bundle;
import android.view.MenuItem;

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

    private MenuItem searchItem;
    private int currentAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.bottomNavigation.setOnNavigationItemSelectedListener(this::switchItem);
//        setSupportActionBar(mBinding.tbMain);

        // 프레그먼트 초기화
        fragmentManager = getSupportFragmentManager();
        homeFragment = new HomeFragment();
        congestionFragment = new CongestionFragment();
        myPlaneFragment = new MyPlaneFragment();
        settingFragment = new SettingFragment();

        // 첫 화면 프레그먼트 추가
        fragmentManager.beginTransaction()
                .add(R.id.fl_page_container, homeFragment)
                .commit();
        currentAction = R.id.action_home;
    }

    private boolean switchItem(@NonNull MenuItem item) {

        final int selectedAction = item.getItemId();
        if (currentAction == selectedAction) {
            return false;
        }

        currentAction = selectedAction;
        switch (selectedAction) {
            case R.id.action_home:
                replaceFragment(homeFragment, false);
                return true;

            case R.id.action_map:
                replaceFragment(congestionFragment, false);

                return true;

            case R.id.action_alarm:
                replaceFragment(myPlaneFragment, false);

                return true;

            case R.id.action_settings:
                replaceFragment(settingFragment, false);

                return true;
        }
        return false;
    }

    private void passQueryToFragment(String query) {
        SearchListFragment fragment;
        fragment = (SearchListFragment) fragmentManager.
                findFragmentByTag(SearchListFragment.class.getSimpleName());

        if (fragment != null) {
            fragment.requestQuery(query);
        }

    }

    private void expandOrCollapseFragment(boolean isExpanded) {
        if (isExpanded) {
            replaceFragment(new SearchListFragment(), true);
        } else {
            onBackPressed();
        }
    }

    private void replaceFragment(Fragment fragment, boolean isAddToBackStack) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .replace(R.id.fl_page_container, fragment, fragment.getClass().getSimpleName());

        if (isAddToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.commit();
    }
}
