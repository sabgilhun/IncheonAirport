package ado.sabgil.incheonariport.view;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import ado.sabgil.incheonariport.R;
import ado.sabgil.incheonariport.data.model.FlightInformation;
import ado.sabgil.incheonariport.databinding.FragmentDetailInfoBinding;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import static ado.sabgil.incheonariport.Constant.FLIGHT_INFO_ARGUMENT_KEY;

public class DetailFragment extends DialogFragment {
    private FragmentDetailInfoBinding binding;
    private FlightInformation information;
    private OnSelectFlightListener onSelectFlightListener;
    private static final String TAG = "DetailFragment";

    public interface OnSelectFlightListener {
        void onSelect(FlightInformation information);
    }

    /* 정적 팩토리 메서드이기 때문에 public 사용 */
    @SuppressWarnings("WeakerAccess")
    public static DetailFragment newInstance(@NonNull FlightInformation information) {
        DetailFragment fragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(FLIGHT_INFO_ARGUMENT_KEY, information);
        fragment.setArguments(bundle);
        return fragment;
    }

    /* 리스너 설정 */
    @SuppressWarnings("WeakerAccess")
    public void setOnSelectFlightListener(@NonNull OnSelectFlightListener onDismissListener) {
        this.onSelectFlightListener = onDismissListener;
    }

    void ifNotAddedShow(@NonNull FragmentManager fragmentManager) {
        Fragment fragment = fragmentManager.findFragmentByTag(TAG);

        /* 등록 된 TAG 없을 경우만 Fragment 추가 */
        if (fragment == null) {
            show(fragmentManager, TAG);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            Window window = dialog.getWindow();
            if (window != null) {
                /* 상태바 색 설정 */
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                window.setStatusBarColor(Color.WHITE);
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_detail_info, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /* 넘겨 받은 인자로 View 데이터바인딩 */
        if (getArguments() != null) {
            information = getArguments().getParcelable(FLIGHT_INFO_ARGUMENT_KEY);
            binding.setInfo(information);
        }

        /* 백버튼 설정 */
        binding.ivBackButton.setOnClickListener(__ -> dismiss());

        /* 내 비행기 등록 버튼 리스너 설정 */
        binding.tvFlightRegister.setOnClickListener(__ -> {
            if (onSelectFlightListener != null && information != null) {
                onSelectFlightListener.onSelect(information);
            }
            dismiss();
        });
    }
}
