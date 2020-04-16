package com.example.minijobmobile.onboarding.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.minijobmobile.MainActivity;
import com.example.minijobmobile.base.BaseFragment;
import com.example.minijobmobile.databinding.FragmentLoginBinding;
import com.example.minijobmobile.main.Item;
import com.example.minijobmobile.remote.RemoteRequestListener;
import com.example.minijobmobile.remote.response.FavoriteItemResponse;
import com.example.minijobmobile.remote.response.OnBoardingResponse;
import com.example.minijobmobile.util.Utils;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment<LoginViewModel, LoginModel>
        implements RemoteRequestListener {

    private FragmentLoginBinding binding;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        viewModel.setRemoteRequestListener(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.etUserId.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                viewModel.setUserId(binding.etUserId.getText().toString());
            }
        });

        binding.etPassword.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                viewModel.setPassword(binding.etPassword.getText().toString());
            }
        });
        binding.btnLogin.setOnClickListener( v -> {
            binding.etPassword.clearFocus();
            viewModel.Login();
        });
    }

    @Override
    protected LoginViewModel getViewModel() {
        return new ViewModelProvider(requireActivity(), getFactory()).get(LoginViewModel.class);
    }

    @Override
    protected ViewModelProvider.Factory getFactory() {
        return new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new LoginViewModel(getModel());
            }
        };
    }

    @Override
    protected LoginModel getModel() {
        return new LoginModel();
    }

    @Override
    public void onSuccess(LiveData<OnBoardingResponse> loginResponse) {
        loginResponse.observe(this, it -> {
            Utils.showToast(getContext(), it.getStatus()).show();
            if (it.getStatus().equals("OK")) {
                Utils.showToast(getContext(), "Loading job list...").show();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("userId", it.getUser_id());
                intent.putExtra("firstName", it.getFirst_name());
                intent.putExtra("lastName", it.getLast_name());
                startActivity(intent);
                getActivity().finish();
            }
        });

    }

    @Override
    public void onUpdate(LiveData<FavoriteItemResponse> itemResponse) {

    }

    @Override
    public void onFailure(String message) {
        Utils.showToast(getContext(), message).show();
    }
}
