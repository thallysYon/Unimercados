package com.example.unimercados_beta.activity.empresa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.unimercados_beta.R;
import com.example.unimercados_beta.databinding.ActivityEmpresaFormProdutoBinding;
import com.example.unimercados_beta.databinding.BottomSheetFormProdutoBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class EmpresaFormProdutoActivity extends AppCompatActivity {

    private ActivityEmpresaFormProdutoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEmpresaFormProdutoBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        configCliques();
    }

    private void configCliques(){

        binding.imageProduto0.setOnClickListener(view -> showBottomSheet() );
        binding.imageProduto1.setOnClickListener(view -> showBottomSheet());
        binding.imageProduto2.setOnClickListener(view -> showBottomSheet());

    }

    private void showBottomSheet(){
        BottomSheetFormProdutoBinding sheetBinding = BottomSheetFormProdutoBinding.inflate(LayoutInflater.from(this));

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this,R.style.BottomSheetDialog);

        bottomSheetDialog.setContentView(sheetBinding.getRoot());
    }

}