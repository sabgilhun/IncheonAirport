package ado.sabgil.incheonariport.view.myflight;

import android.os.Bundle;
import android.view.View;

import ado.sabgil.incheonariport.R;
import ado.sabgil.incheonariport.databinding.FragmentMyFlightBinding;
import ado.sabgil.incheonariport.view.base.BaseFragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyFlightFragment extends BaseFragment<FragmentMyFlightBinding> {
    private MyFlightViewModel myFlightViewModel;

    protected int getLayout() {
        return R.layout.fragment_my_flight;
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /* MyFlightViewModel 초기화 */
        myFlightViewModel = getViewModelInActivity(MyFlightViewModel.class);
        getBinding().setVm(myFlightViewModel);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            myFlightViewModel.clearBadge();
        }
    }
}
