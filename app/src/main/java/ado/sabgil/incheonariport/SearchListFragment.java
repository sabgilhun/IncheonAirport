package ado.sabgil.incheonariport;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ado.sabgil.incheonariport.adapter.FlightInfoAdapter;
import ado.sabgil.incheonariport.databinding.FragmentSearchListBinding;
import ado.sabgil.incheonariport.remote.openapi.IncheonAirportApiHandler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class SearchListFragment extends Fragment {
    private static final int QUERY_MINIMUM_LENGTH = 2;

    private FragmentSearchListBinding mBinding;
    private IncheonAirportApiHandler handler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.
                inflate(inflater, R.layout.fragment_search_list, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        handler = IncheonAirportApiHandler.getInstance();
        initRecyclerView();
        setVisibleView(mBinding.lyDefaultScreen);
    }

    public void requestQuery(@NonNull String query) {

        if (TextUtils.isEmpty(query)) {
            setVisibleView(mBinding.lyDefaultScreen);
            mBinding.setItems(null);
        } else if (query.length() < QUERY_MINIMUM_LENGTH) {
            setVisibleView(mBinding.lyShortTextScreen);
            mBinding.setItems(null);
        } else {
            if (getActivity() != null) {
                getActivity().runOnUiThread(() -> setVisibleView(mBinding.lyListScreen));
            }
            handler.getPassengerDeparturesW(query.toUpperCase(),
                    response -> {
                        if (response == null) {
                            setVisibleView(mBinding.lyNoDataScreen);
                        }
                        mBinding.setItems(response);

                    },
                    error -> Log.e("Main", error.getMessage())
            );
        }
    }


    private void initRecyclerView() {
        RecyclerView recyclerView = mBinding.rvSearchedItem;
        FlightInfoAdapter adapter = new FlightInfoAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void setVisibleView(View view) {
        mBinding.flListContainer.bringChildToFront(view);
    }

}
