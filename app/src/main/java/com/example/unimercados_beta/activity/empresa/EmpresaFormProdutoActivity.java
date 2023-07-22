package com.example.unimercados_beta.activity.empresa;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import com.example.unimercados_beta.R;
import com.example.unimercados_beta.databinding.ActivityEmpresaFormProdutoBinding;
import com.example.unimercados_beta.databinding.BottomSheetFormProdutoBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EmpresaFormProdutoActivity extends AppCompatActivity {
    private ActivityEmpresaFormProdutoBinding binding;

    private int resultCode = 0;

    private String currentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEmpresaFormProdutoBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        configCliques();
    }

    private void configCliques(){

        binding.imageProduto0.setOnClickListener(view -> showBottomSheet(0));
        binding.imageProduto1.setOnClickListener(view -> showBottomSheet(1));
        binding.imageProduto2.setOnClickListener(view -> showBottomSheet(2));

    }

    private void showBottomSheet(int code){

        resultCode = code;

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


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void abrirCamera(){

        switch (resultCode){
            case 0:
                resultCode = 3;
                break;
            case 1:
                resultCode = 4;
                break;
            case 2:
                resultCode = 5;
                break;
        }

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.unimercados_beta.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                resultLauncher.launch(takePictureIntent);

            }
        }
    }

    private void abrirGaleria(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        resultLauncher.launch(intent);
    }

    private final ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode() == RESULT_OK){

                    String caminhoImagem;

                    if(resultCode <= 2){//Galeria

                        Uri imagemSelecionada = result.getData().getData();


                        try {

                            caminhoImagem = imagemSelecionada.toString();

                            switch (resultCode){
                                case 0:

                                    binding.imgFake0.setVisibility(View.GONE);
                                    binding.imageProduto0.setImageBitmap(getBitmap(imagemSelecionada));
                                    break;

                                case 1:

                                    binding.imgFake1.setVisibility(View.GONE);
                                    binding.imageProduto1.setImageBitmap(getBitmap(imagemSelecionada));
                                    break;

                                case 2:

                                    binding.imgFake2.setVisibility(View.GONE);
                                    binding.imageProduto2.setImageBitmap(getBitmap(imagemSelecionada));
                                    break;

                                default:
                                    Toast.makeText(this, "Erro", Toast.LENGTH_SHORT).show();
                            }


                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }else { //Câmera

                        File file = new File(currentPhotoPath);
                        caminhoImagem = String.valueOf(file.toURI());

                        switch (resultCode){
                            case 3:
                                binding.imgFake0.setVisibility(View.GONE);
                                binding.imageProduto0.setImageURI(Uri.fromFile(file));
                                break;
                            case 4:
                                binding.imgFake1.setVisibility(View.GONE);
                                binding.imageProduto1.setImageURI(Uri.fromFile(file));
                                break;
                            case 5:
                                binding.imgFake2.setVisibility(View.GONE);
                                binding.imageProduto2.setImageURI(Uri.fromFile(file));
                                break;
                        }

                    }

                }
            }
    );

    private Bitmap getBitmap(Uri caminhoUri){

        Bitmap bitmap = null;

        try {
            if (Build.VERSION.SDK_INT < 28){

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), caminhoUri);

            }else{

                ImageDecoder.Source source = ImageDecoder.createSource(getContentResolver(), caminhoUri);
                bitmap = ImageDecoder.decodeBitmap(source);

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return  bitmap;
    }
}