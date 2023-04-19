package model;

import com.google.firebase.database.DatabaseReference;

import helper.FirebaseHelper;

public class Login {

    private String id;

    private String tipo; //esse é o tipo U: de usuário - e tipo E: de empresa

    private Boolean acesso; //utilizado para a empresa ser redirecionada para a segunda tela de login //

    public Login(String id, String tipo, Boolean acesso) {
        this.id = id;
        this.tipo = tipo;
        this.acesso = acesso;
    }

    public void salvar(){

        DatabaseReference loginRef = FirebaseHelper.getDatabaseReference().child("login").child(getId());

        loginRef.setValue(this);

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getAcesso() {
        return acesso;
    }

    public void setAcesso(Boolean acesso) {
        this.acesso = acesso;
    }
}
