package com.example.minijobmobile.main.nearby;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.minijobmobile.base.BaseFragment;
import com.example.minijobmobile.databinding.FragmentNearbyBinding;
import com.example.minijobmobile.main.Item;
import com.example.minijobmobile.main.ItemDataAdapter;
import com.example.minijobmobile.remote.response.ListItemQueryResponse;

import java.util.ArrayList;


public class NearbyFragment extends BaseFragment<NearbyViewModel, NearbyModel> {

    private FragmentNearbyBinding binding;
    private ItemDataAdapter adapter = new ItemDataAdapter();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNearbyBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Nearby");
        binding.rvMain.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvMain.setHasFixedSize(true);
        binding.rvMain.setAdapter(adapter);
        getAllItem();
    }

    @Override
    protected NearbyViewModel getViewModel() {
        return new ViewModelProvider(requireActivity(), getFactory()).get(NearbyViewModel.class);
    }

    @Override
    protected ViewModelProvider.Factory getFactory() {
        return new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new NearbyViewModel(getModel());
            }
        };
    }

    @Override
    protected NearbyModel getModel() {
        return new NearbyModel();
    }

    private void getAllItem() {
        viewModel.getSearchResult().observe(getViewLifecycleOwner(), list -> {

            adapter.setItems(list);
        });
    }
}
