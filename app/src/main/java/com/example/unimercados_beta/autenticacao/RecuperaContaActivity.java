package com.example.unimercados_beta.autenticacao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.unimercados_beta.R;
import com.example.unimercados_beta.databinding.ActivityRecuperaContaBinding;
import com.example.unimercados_beta.helper.FirebaseHelper;

public class RecuperaContaActivity extends AppCompatActivity {

    private ActivityRecuperaContaBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRecuperaContaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        configCliques();
    }

    public void validaDados(View view){

        String email = binding.edtEmail.getText().toString().trim();

        if (!email.isEmpty()){

            binding.progressBar.setVisibility(View.VISIBLE);

            recuperaSenha(email);

        }else{
            binding.edtEmail.requestFocus();
            binding.edtEmail.setError("Informe seu email!");

        }

    }

    private void recuperaSenha(String email){

        FirebaseHelper.getAuth().sendPasswordResetEmail(
                email
        ).addOnCompleteListener(task -> {

            if (task.isSuccessful()){

                Toast.makeText(this, "Acabamos de enviar um link para o email informado.", Toast.LENGTH_SHORT).show();

            }else{

                Toast.makeText(this, FirebaseHelper.validaErros(task.getException().getMessage()), Toast.LENGTH_SHORT).show();
            }
            binding.progressBar.setVisibility(View.GONE);
        });

    }
    private void configCliques(){
        binding.include.ibVoltar.setOnClickListener(view -> finish());

    }
}