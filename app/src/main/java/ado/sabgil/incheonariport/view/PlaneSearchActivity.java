package ado.sabgil.incheonariport.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

import ado.sabgil.incheonariport.R;
import ado.sabgil.incheonariport.adapter.FlightInfoAdapter;
import ado.sabgil.incheonariport.data.DataManager;
import ado.sabgil.incheonariport.data.DataManagerImpl;
import ado.sabgil.incheonariport.databinding.ActivityPlaneSearchBinding;
import ado.sabgil.incheonariport.util.SoftKeyboardUtils;
import ado.sabgil.incheonariport.view.base.BaseActivity;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class PlaneSearchActivity extends BaseActivity<ActivityPlaneSearchBinding> {

    private DataManager dataManager;
    private Timer debounceTimer;
    public static int DEBOUNCE_TIMER_DURATION = 500;
    public static int SHORT_TEXT_CRITERION = 2;

    protected int getLayout() {
        return R.layout.activity_plane_search;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // DataManager 초기화
        dataManager = DataManagerImpl.getInstance();

        // view 초기화
        getBinding().ivBackButton.setOnClickListener(__ -> finish());
        getBinding().ivClear.setOnClickListener(__ -> getBinding().etSearch.setText(null));
        getBinding().etSearch.addTextChangedListener(textWatcher);
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = getBinding().rvSearchedItem;
        FlightInfoAdapter adapter = new FlightInfoAdapter();
        adapter.setOnItemClickListener((v, position) -> {
            SoftKeyboardUtils.hideKeyboard(this, getBinding().etSearch);

            Bundle bundle = new Bundle();
            bundle.putParcelable("flight_info", adapter.getItem(position));
            Fragment fragment = new DetailInfoFragment();
            fragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_screen_container, fragment)
                    .addToBackStack("")
                    .commit();
        });
        recyclerView.setAdapter(adapter);
    }

    private void loadFlightData(String query) {
        if (query.length() < SHORT_TEXT_CRITERION) {
            runOnUiThread(() ->
                    getBinding().flListContainer.bringChildToFront(getBinding().lyShortTextScreen));
            return;
        }

        dataManager.getFlightInfoWithId(query.toUpperCase(),
                result -> {
                    getBinding().setItems(result);
                    getBinding().flListContainer.bringChildToFront(getBinding().lyListScreen);
                },
                error -> {
                    if (TextUtils.equals(error.getMessage(), "No Response")) {
                        getBinding().tvNoData.setText(
                                String.format(getText(R.string.hint_no_response).toString(), query));
                        getBinding().flListContainer.bringChildToFront(getBinding().lyNoDataScreen);
                    } else {
                        Log.e("networking", error.getMessage());
                    }
                }
        );
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (debounceTimer != null) {
                debounceTimer.cancel();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() > 0) {
                getBinding().ivClear.setVisibility(View.VISIBLE);
            } else {
                getBinding().ivClear.setVisibility(View.INVISIBLE);
                getBinding().flListContainer.bringChildToFront(getBinding().lyDefaultScreen);
                return;
            }

            debounceTimer = new Timer();
            debounceTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    loadFlightData(s.toString());
                }
            }, DEBOUNCE_TIMER_DURATION);
        }
    };

}
