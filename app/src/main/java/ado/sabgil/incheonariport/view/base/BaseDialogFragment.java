package ado.sabgil.incheonariport.view.base;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

public abstract class BaseDialogFragment<B extends ViewDataBinding> extends DialogFragment {
    private final String TAG = getClass().getSimpleName();
    private B binding;

    protected abstract int getLayout();

    public B getBinding() {
        return binding;
    }

    public void ifNotAddedShow(@NonNull FragmentManager fragmentManager) {
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    protected <VM extends ViewModel> VM getViewModelInFragment(Class<VM> vmClass) {
        return ViewModelProviders.of(this).get(vmClass);
    }

    protected <VM extends ViewModel> VM getViewModelInActivity(Class<VM> vmClass) {
        if (getActivity() != null) {
            return ViewModelProviders.of(getActivity()).get(vmClass);
        } else {
            throw new IllegalStateException("Fragment not attached");
        }
    }
}
