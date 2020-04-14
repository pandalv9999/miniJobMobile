package com.example.minijobmobile.onboarding.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.example.minijobmobile.R;

import com.example.minijobmobile.base.BaseFragment;
import com.example.minijobmobile.databinding.FragmentLoginBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment<LoginViewModel, LoginModel> {

    private FragmentLoginBinding binding;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.etUserId.setOnEditorActionListener(
                (v, actionId, event) -> {
                    if (actionId == EditorInfo.IME_ACTION_DONE
                            || actionId == EditorInfo.IME_ACTION_NEXT) { // require user to click
                        viewModel.setUserId(binding.etUserId.getText().toString());
                        // debug purpose
                        Toast.makeText(getActivity(), "done entering id", Toast.LENGTH_SHORT).show();
                        return true;
                    } else {
                        return false;
                    }
                }
        );
        binding.etPassword.setOnEditorActionListener(
                (v, actionId, event) -> {
                    if (actionId == EditorInfo.IME_ACTION_DONE
                            || actionId == EditorInfo.IME_ACTION_NEXT) { // require user to click
                        viewModel.setPassword(binding.etUserId.getText().toString());
                        // debug purpose
                        Toast.makeText(getActivity(), "done entering password", Toast.LENGTH_SHORT).show();
                        return true;
                    } else {
                        return false;
                    }
                }
        );
        viewModel.getToastObserver().observe(this, msg -> {
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        });
        binding.btnLogin.setOnClickListener( v -> {
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
}
