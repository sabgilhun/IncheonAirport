package ado.sabgil.incheonariport.view.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

public abstract class BaseFragment<B extends ViewDataBinding> extends Fragment {

    private B binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    protected abstract int getLayout();

    public B getBinding() {
        return binding;
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
