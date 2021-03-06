package com.example.minijobmobile.main.favorite;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import com.example.minijobmobile.databinding.FragmentFavoriteBinding;
import com.example.minijobmobile.databinding.FragmentNearbyBinding;
import com.example.minijobmobile.main.ItemDataAdapter;
import com.example.minijobmobile.main.nearby.NearbyModel;
import com.example.minijobmobile.main.nearby.NearbyViewModel;
import com.example.minijobmobile.remote.RemoteRequestListener;
import com.example.minijobmobile.remote.response.FavoriteItemResponse;
import com.example.minijobmobile.remote.response.OnBoardingResponse;
import com.example.minijobmobile.util.Utils;

import java.util.ArrayList;


public class FavoriteFragment extends BaseFragment<FavoriteViewModel, FavoriteModel>
        implements ItemDataAdapter.OnNoteListener, RemoteRequestListener {
    private FragmentFavoriteBinding binding;
    private ItemDataAdapter adapter = new ItemDataAdapter();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Favorite");
        viewModel.setRemoteRequestListener(this);
        binding.rvMain.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvMain.setHasFixedSize(true);
        binding.rvMain.setAdapter(adapter);
        getAllItem();
    }

    @Override
    protected FavoriteViewModel getViewModel() {
        return new ViewModelProvider(requireActivity(), getFactory()).get(FavoriteViewModel.class);
    }

    @Override
    protected ViewModelProvider.Factory getFactory() {
        return new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new FavoriteViewModel(getModel());
            }
        };
    }

    @Override
    protected FavoriteModel getModel() {
        return new FavoriteModel();
    }

    private void getAllItem() {
        viewModel.getFavoriteList().observe(getViewLifecycleOwner(), list -> {
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
    public void onUpdate(LiveData<FavoriteItemResponse> itemResponse) {
        itemResponse.observe(getViewLifecycleOwner(), it -> {
            if (it != null) {
                Utils.showToast(getContext(), "Update Success").show();
            } else {
                Utils.showToast(getContext(), "Update failure").show();
            }
        });
    }

    @Override
    public void onFailure(String message) {

    }
}
