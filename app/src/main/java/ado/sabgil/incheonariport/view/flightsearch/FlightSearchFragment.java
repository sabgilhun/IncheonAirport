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

    public static int DEBOUNCE_TIMER_DURATION = 300;

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
        flightSearchViewModel = getViewModelInFragment(FlightSearchViewModel.class);
        getBinding().setVm(flightSearchViewModel);

        flightSearchViewModel.getStatus().observe(this, this::switchView);
        getBinding().ivBackButton.setOnClickListener(__ -> dismiss());
        getBinding().ivClear.setOnClickListener(__ -> getBinding().etSearch.setText(null));
        getBinding().etSearch.addTextChangedListener(textWatcher);
        initRecyclerView();
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

    private void initRecyclerView() {
        RecyclerView recyclerView = getBinding().rvSearchedItem;
        FlightInfoAdapter adapter = new FlightInfoAdapter();

        adapter.setOnItemClickListener((v, position) -> {
            /* 아이템 선택 시 키보드 숨김 */
            SoftKeyboardUtils.hideKeyboard(rootActivity, getBinding().etSearch);

            /* 상세 정보 다이얼로그 표시 및 버튼 리스너 설정 */
            DetailFragment detailFragment = DetailFragment.newInstance(adapter.getItem(position));
            detailFragment.ifNotAddedShow(rootActivity.getSupportFragmentManager());
        });

        recyclerView.setAdapter(adapter);
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
