package com.example.minijobmobile.main.nearby;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.minijobmobile.R;
import com.example.minijobmobile.base.BaseFragment;
import com.example.minijobmobile.databinding.FragmentNearbyBinding;
import com.example.minijobmobile.main.Item;
import com.example.minijobmobile.main.ItemDataAdapter;
import com.example.minijobmobile.remote.RemoteRequestListener;
import com.example.minijobmobile.remote.response.OnBoardingResponse;
import com.example.minijobmobile.util.Utils;

import java.util.ArrayList;


public class NearbyFragment extends BaseFragment<NearbyViewModel, NearbyModel> implements
        ItemDataAdapter.OnNoteListener, RemoteRequestListener {

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
        viewModel.setRemoteRequestListener(this);
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
            adapter.setItems(new ArrayList<>(list));
            adapter.setOnNoteListener(this);
            adapter.notifyDataSetChanged();
        });
    }

    @Override
    public void onNoteClick(int position, ItemDataAdapter adapter) {
        Intent browserIntend = new Intent(Intent.ACTION_VIEW,
                Uri.parse(adapter.getItem(position).getUrl()));
        startActivity(browserIntend);
    }

    @Override
    public void onHeartClick(int position, ItemDataAdapter adapter, Button heart) {
        if (adapter.getItem(position).isFavorite()) {
            heart.setBackground(getContext().getResources()
                    .getDrawable(R.drawable.ic_fav_black));
            adapter.getItem(position).setFavorite(false);
            viewModel.processFavoriteItem(adapter.getItem(position), false);
        } else {
            heart.setBackground(getContext().getResources()
                    .getDrawable(R.drawable.ic_fav_red));
            adapter.getItem(position).setFavorite(true);
            viewModel.processFavoriteItem(adapter.getItem(position), true);
        }

    }

    @Override
    public void onSuccess(LiveData<OnBoardingResponse> loginResponse) {

    }

    @Override
    public void onUpdate(LiveData<Item> itemResponse) {

    }

    @Override
    public void onFailure(String message) {

    }
}
