package com.example.unimercados.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.hardware.input.InputManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.unimercados.R;

import helper.FirebaseHelper;
import model.Login;
import model.Usuario;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UsuarioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UsuarioFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UsuarioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UsuarioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UsuarioFragment newInstance(String param1, String param2) {
        UsuarioFragment fragment = new UsuarioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private EditText edt_email;
    private EditText edt_senha;
    private ProgressBar progressBar;
    private Button btn_criar_conta;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_usuario, container, false);

        iniciaComponentes(view);

        validaDados();

        configCliques();

        return view;
    }

    private void configCliques(){

        btn_criar_conta.setOnClickListener(v -> validaDados());

    }

    private void validaDados(){

        String email = edt_email.getText().toString();
        String senha = edt_senha.getText().toString();

        //validação

        if (!email.isEmpty()){

            if (!senha.isEmpty()){

                ocultarTeclado();

                progressBar.setVisibility(View.VISIBLE);

                Usuario usuario = new Usuario();

                usuario.setEmail(email);
                usuario.setSenha(senha);

                criarConta(usuario);

                //getActivity().finish();

            }else{

                edt_senha.requestFocus();
                edt_senha.setError("Informe sua senha");

            }

        }else{

            edt_email.requestFocus();
            edt_email.setError("Informe seu email");

        }

    }

    private void criarConta(Usuario usuario){

        FirebaseHelper.getAuth().createUserWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(task -> {

            if (task.isSuccessful()){

                String id = task.getResult().getUser().getUid();

                usuario.setId(id);
                usuario.salvar();

                Login login = new Login(id,"U",false);

                login.salvar();

            }else{

                progressBar.setVisibility(View.GONE);

                erroAutenticacao(FirebaseHelper.validaErros(task.getException().getMessage()));

            }

        });



    }

    private void erroAutenticacao(String msg){

        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

        builder.setTitle("Atenção");

        builder.setMessage(msg);

        builder.setPositiveButton("OK",((dialog, which) -> {

            dialog.dismiss();

        }));

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void iniciaComponentes(View view){

        edt_email = view.findViewById(R.id.edt_email);
        edt_senha = view.findViewById(R.id.edt_senha);
        progressBar = view.findViewById(R.id.progressBar);
        btn_criar_conta = view.findViewById(R.id.btn_criar_conta);

    }

    private void ocultarTeclado(){

        ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                btn_criar_conta.getWindowToken(), 0
        );

    }
}