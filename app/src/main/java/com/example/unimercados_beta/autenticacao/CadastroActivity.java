package com.example.unimercados_beta.autenticacao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.unimercados_beta.R;
import com.example.unimercados_beta.databinding.ActivityCadastroBinding;
import com.example.unimercados_beta.databinding.ActivityLoginBinding;
import com.example.unimercados_beta.helper.FirebaseHelper;
import com.example.unimercados_beta.model.Loja;
import com.example.unimercados_beta.model.Usuario;

public class CadastroActivity extends AppCompatActivity {

    private ActivityCadastroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCadastroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        configCliques();

    }

    public void validaDados(View view){
        String nome = binding.edtNome.getText().toString().trim();

        String email = binding.edtEmail.getText().toString().trim();

        String senha = binding.edtSenha.getText().toString().trim();

        String confirmaSenha = binding.edtConfirmaSenha.getText().toString().trim();

        if (!nome.isEmpty()){

            if (!email.isEmpty()){

                if (!senha.isEmpty()){

                    if (!confirmaSenha.isEmpty()){

                        if (senha.equals(confirmaSenha)){

                            binding.progressBar.setVisibility(View.VISIBLE);

                            //cadastro

                            Usuario usuario = new Usuario();
                            usuario.setNome(nome);
                            usuario.setEmail(email);
                            usuario.setSenha(senha);
                            criarConta(usuario);




                        }else{
                            binding.edtConfirmaSenha.requestFocus();
                            binding.edtConfirmaSenha.setError("As senhas não sao iguais!");
                        }


                    }else{
                        binding.edtConfirmaSenha.requestFocus();
                        binding.edtConfirmaSenha.setError("Confirme a senha!");
                    }

                }else{
                    binding.edtSenha.requestFocus();
                    binding.edtSenha.setError("Informe sua senha!");
                }

            }else{
                binding.edtEmail.requestFocus();
                binding.edtEmail.setError("Informe seu email!");
            }

        }else{
            binding.edtNome.requestFocus();
            binding.edtNome.setError("Informe seu nome!");
        }
    }

    private void criarConta(Usuario usuario){

        FirebaseHelper.getAuth().createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(task -> {

            if (task.isSuccessful()){

                String id = task.getResult().getUser().getUid();

                usuario.setId(id);
                usuario.salvar();

                Intent intent = new Intent();
                intent.putExtra("email",usuario.getEmail());
                setResult(RESULT_OK, intent);
                finish();

            }else {
                Toast.makeText(this,FirebaseHelper.validaErros(task.getException().getMessage()),Toast.LENGTH_SHORT).show();
            }

            binding.progressBar.setVisibility(View.GONE);

        });

    }

    private void configCliques(){

        binding.include.ibVoltar.setOnClickListener(view -> finish());
        binding.btnLogin.setOnClickListener(view -> finish());

    }
}