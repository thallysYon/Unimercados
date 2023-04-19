package com.example.unimercados.activity.autenticacao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TextView;

import com.example.unimercados.R;
import com.example.unimercados.adapter.ViewPagerAdapter;
import com.example.unimercados.fragment.EmpresaFragment;
import com.example.unimercados.fragment.UsuarioFragment;
import com.google.android.material.tabs.TabLayout;

public class CriarContaActivity extends AppCompatActivity {

    private TabLayout tab_layout;
    private ViewPager view_pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);

        //inicializar

        iniciaComponentes();

        configTabsLayout();

        configCliques();

    }


    private void ConfigTabsLayout(){


    }


    private void configCliques(){
        findViewById(R.id.ib_voltar).setOnClickListener(v -> finish());
    }

    private void configTabsLayout(){

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(new UsuarioFragment(), "Usuário");
        viewPagerAdapter.addFragment(new EmpresaFragment(), "Empresa");

        view_pager.setAdapter(viewPagerAdapter);

        tab_layout.setElevation(0);
        tab_layout.setupWithViewPager(view_pager);

    }

    private void iniciaComponentes(){

        TextView text_toolbar = findViewById(R.id.text_toolbar);
        text_toolbar.setText("Cadastre-se");

        view_pager = findViewById(R.id.view_pager);
        tab_layout = findViewById(R.id.tab_layout);

    }

}