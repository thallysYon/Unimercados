package com.example.unimercados_beta.fragment.empresa;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.unimercados_beta.R;
import com.example.unimercados_beta.activity.empresa.EmpresaFormProdutoActivity;
import com.example.unimercados_beta.databinding.FragmentEmpresaProdutoBinding;


public class EmpresaProdutoFragment extends Fragment {

    private FragmentEmpresaProdutoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEmpresaProdutoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        configCliques();

    }

    private void configCliques() {

        binding.toolbar.btnAdd.setOnClickListener(v -> {
            startActivity(new Intent(requireContext(), EmpresaFormProdutoActivity.class));
        });

    }
}