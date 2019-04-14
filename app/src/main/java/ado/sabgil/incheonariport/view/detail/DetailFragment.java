package ado.sabgil.incheonariport.view.detail;

import android.os.Bundle;
import android.view.View;

import ado.sabgil.incheonariport.R;
import ado.sabgil.incheonariport.data.model.FlightInformation;
import ado.sabgil.incheonariport.databinding.FragmentDetailInfoBinding;
import ado.sabgil.incheonariport.view.base.BaseDialogFragment;
import ado.sabgil.incheonariport.view.myflight.MyFlightViewModel;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import static ado.sabgil.incheonariport.Constant.FLIGHT_INFO_ARGUMENT_KEY;

public class DetailFragment extends BaseDialogFragment<FragmentDetailInfoBinding> {
    private DetailViewModel detailViewModel;
    private MyFlightViewModel myFlightViewModel;

    @Override
    protected int getLayout() {
        return R.layout.fragment_detail_info;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /* ViewModel 초기화 */
        setupViewModel();

        /* View 초기화 */
        setupBackButton();
        setupFlightRegisterButton();
    }

    private void setupViewModel() {
        detailViewModel = getViewModelInFragment(DetailViewModel.class);
        getBinding().setVm(detailViewModel);
        myFlightViewModel = getViewModelInActivity(MyFlightViewModel.class);

        if (getArguments() != null) {
            detailViewModel.setFlightInformation(
                    getArguments().getParcelable(FLIGHT_INFO_ARGUMENT_KEY));
        }
    }

    private void setupBackButton() {
        getBinding().ivBackButton.setOnClickListener(__ -> dismiss());
    }

    private void setupFlightRegisterButton() {
        getBinding().tvFlightRegister.setOnClickListener(__ -> {
            FlightInformation info = detailViewModel.getFlightInformation().getValue();
            myFlightViewModel.setFlightInformation(info);
            dismiss();
        });
    }
}
