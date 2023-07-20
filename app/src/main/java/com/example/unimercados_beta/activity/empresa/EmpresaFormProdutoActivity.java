package com.example.unimercados_beta.activity.empresa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

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
        bottomSheetDialog.show();

        sheetBinding.btnCamera.setOnClickListener(view -> {
            Toast.makeText(this, "Camera", Toast.LENGTH_SHORT).show();
            bottomSheetDialog.dismiss();
        });

        sheetBinding.btnGaleria.setOnClickListener(view -> {
            Toast.makeText(this, "Galeria", Toast.LENGTH_SHORT).show();
            bottomSheetDialog.dismiss();
        });

        sheetBinding.btnCancelar.setOnClickListener(view -> {
            Toast.makeText(this, "Cancelar", Toast.LENGTH_SHORT).show();
            bottomSheetDialog.dismiss();
        });

    }

}