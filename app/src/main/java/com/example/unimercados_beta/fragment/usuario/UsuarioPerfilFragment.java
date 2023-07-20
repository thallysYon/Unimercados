package com.example.unimercados_beta.fragment.usuario;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.unimercados_beta.R;
import com.example.unimercados_beta.activity.app.SplashActivity;
import com.example.unimercados_beta.autenticacao.LoginActivity;
import com.example.unimercados_beta.databinding.FragmentUsuarioPerfilBinding;
import com.example.unimercados_beta.helper.FirebaseHelper;


public class UsuarioPerfilFragment extends Fragment {

    private FragmentUsuarioPerfilBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentUsuarioPerfilBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnLogin.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        });

        binding.btnSair.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), SplashActivity.class));
        });

    }

    public void sair(View view){
        FirebaseHelper.getAuth().signOut();
    }
}