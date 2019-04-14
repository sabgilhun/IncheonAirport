package ado.sabgil.incheonariport.view.flightsearch;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

import ado.sabgil.incheonariport.R;
import ado.sabgil.incheonariport.adapter.FlightInfoAdapter;
import ado.sabgil.incheonariport.databinding.FragmentFlightSearchBinding;
import ado.sabgil.incheonariport.util.SoftKeyboardUtils;
import ado.sabgil.incheonariport.view.base.BaseDialogFragment;
import ado.sabgil.incheonariport.view.detail.DetailFragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

public class FlightSearchFragment extends BaseDialogFragment<FragmentFlightSearchBinding> {

    private FlightSearchViewModel flightSearchViewModel;

    private Timer debounceTimer;

    private AppCompatActivity rootActivity;


    @Override
    protected int getLayout() {
        return R.layout.fragment_flight_search;
    }

    /* 정적 팩토리 메서드이기 때문에 public 사용 */
    @SuppressWarnings("WeakerAccess")
    public static FlightSearchFragment newInstance() {
        return new FlightSearchFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenInputDialogStyle);

        if (getActivity() != null) {
            rootActivity = (AppCompatActivity) getActivity();
        } else {
            throw new IllegalStateException("Not attached fragment");
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /* ViewModel 초기화 */
        setupViewModel();

        /* status observe */
        flightSearchViewModel.getStatus().observe(this, this::switchView);

        /* View 초기화 */
        setupBackButton();
        setupTextClearButton();
        setupSearchEditText();
        setupFlightInfoRecyclerView();
    }

    private void setupViewModel() {
        flightSearchViewModel = getViewModelInFragment(FlightSearchViewModel.class);
        getBinding().setVm(flightSearchViewModel);
    }

    private void setupBackButton() {
        getBinding().ivBackButton.setOnClickListener(__ -> dismiss());
    }

    private void setupTextClearButton() {
        getBinding().ivClear.setOnClickListener(__ -> getBinding().etSearch.setText(null));
    }

    private void setupSearchEditText() {
        getBinding().etSearch.addTextChangedListener(textWatcher);
    }

    private void setupFlightInfoRecyclerView() {
        RecyclerView recyclerView = getBinding().rvSearchedItem;
        FlightInfoAdapter adapter = new FlightInfoAdapter();

        adapter.setOnItemClickListener((v, position) -> {
            /* 아이템 선택 시 키보드 숨김 */
            SoftKeyboardUtils.hideKeyboard(rootActivity, getBinding().etSearch);

            DetailFragment detailFragment = DetailFragment.newInstance(adapter.getItem(position));
            detailFragment.ifNotAddedShow(rootActivity.getSupportFragmentManager());
        });

        recyclerView.setAdapter(adapter);
    }

    private void switchView(FlightSearchViewModel.Status status) {

        switch (status) {
            case DEFAULT:
                getBinding().flListContainer.bringChildToFront(getBinding().lyDefaultScreen);
                return;

            case SUCCESS:
                getBinding().flListContainer.bringChildToFront(getBinding().lyListScreen);
                return;

            case NO_RESPONSE:
                getBinding().tvNoData.setText(String.format(
                        getText(R.string.hint_no_response).toString(),
                        flightSearchViewModel.getLastQuery()));
                getBinding().flListContainer.bringChildToFront(getBinding().lyNoDataScreen);
                return;

            case SHORT_QUERY:
                getBinding().flListContainer.bringChildToFront(getBinding().lyShortTextScreen);
                return;

            default:
                getBinding().flListContainer.bringChildToFront(getBinding().lyDefaultScreen);
        }
    }

    private TextWatcher textWatcher = new TextWatcher() {
        private int DEBOUNCE_TIMER_DURATION = 300;

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
            }

            debounceTimer = new Timer();
            debounceTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    flightSearchViewModel.loadFlightData(s.toString());
                }
            }, DEBOUNCE_TIMER_DURATION);
        }
    };
}
