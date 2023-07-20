package com.example.unimercados_beta.activity.empresa;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;
import com.example.unimercados_beta.R;
import com.example.unimercados_beta.databinding.ActivityEmpresaFormProdutoBinding;
import com.example.unimercados_beta.databinding.BottomSheetFormProdutoBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.util.List;

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
            verificaPermissaoCamera();
            bottomSheetDialog.dismiss();
        });

        sheetBinding.btnGaleria.setOnClickListener(view -> {
            verificaPermissaoGaleria();
            bottomSheetDialog.dismiss();
        });

        sheetBinding.btnCancelar.setOnClickListener(view -> {
            bottomSheetDialog.dismiss();
        });

    }

    private void verificaPermissaoCamera(){

        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                abrirCamera();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(getBaseContext(), "Permissão negada", Toast.LENGTH_SHORT).show();
            }


        };

        showDialogPermissao(
                permissionlistener,
                new String[]{android.Manifest.permission.CAMERA},
                "Se você não aceitar a permissão você não poderá acessar a câmera do dispositivo, deseja ativar a permissão agora?"
        );
    }

    private void verificaPermissaoGaleria(){

        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                abrirGaleria();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(getBaseContext(), "Permissão negada", Toast.LENGTH_SHORT).show();
            }


        };

        showDialogPermissao(
                permissionlistener,
                new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                "Se você não aceitar a permissão você não poderá acessar a galeria do dispositivo, deseja ativar a permissão agora?"
        );

    }

    private void showDialogPermissao(PermissionListener permissionListener, String[] permissoes, String msg){
        TedPermission.create()
                .setPermissionListener(permissionListener)
                .setDeniedTitle("Permissão negada")
                .setDeniedMessage(msg)
                .setDeniedCloseButtonText("Não")
                .setGotoSettingButtonText("Sim")
                .setPermissions(permissoes)
                .check();
    }

    private void abrirCamera(){

    }

    private void abrirGaleria(){

    }
}