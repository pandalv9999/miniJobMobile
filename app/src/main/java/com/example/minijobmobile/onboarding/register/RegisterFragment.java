package com.example.minijobmobile.onboarding.register;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.minijobmobile.R;
import com.example.minijobmobile.base.BaseFragment;
import com.example.minijobmobile.databinding.FragmentRegisterBinding;
import com.example.minijobmobile.onboarding.OnBoardingListener;
import com.example.minijobmobile.onboarding.login.LoginViewModel;
import com.example.minijobmobile.remote.response.OnBoardingResponse;
import com.example.minijobmobile.util.Utils;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends BaseFragment<RegisterViewModel, RegisterModel>
        implements OnBoardingListener {

    private FragmentRegisterBinding binding;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        viewModel.setOnBoardingListener(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
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
        binding.etFirstName.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                viewModel.setFirstName(binding.etFirstName.getText().toString());
            }
        });
        binding.etLastName.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                viewModel.setLastName(binding.etLastName.getText().toString());
            }
        });
        binding.btnRegister.setOnClickListener(v -> {
            binding.etLastName.clearFocus();
            viewModel.Register();
        });
    }

    @Override
    protected RegisterViewModel getViewModel() {
        return new ViewModelProvider(requireActivity(), getFactory()).get(RegisterViewModel.class);
    }

    @Override
    protected ViewModelProvider.Factory getFactory() {
        return new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new RegisterViewModel(getModel());
            }
        };
    }

    @Override
    protected RegisterModel getModel() {
        return new RegisterModel();
    }

    @Override
    public void onSuccess(LiveData<OnBoardingResponse> loginResponse) {
        loginResponse.observe(this, it -> {
            Utils.showToast(getContext(), it.getStatus()).show();
        });

    }

    @Override
    public void onFailure(String message) {
        Utils.showToast(getContext(), message).show();
    }
}
