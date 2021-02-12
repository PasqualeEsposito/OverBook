package model;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Cliente {
    private String  nome, cognome, data, email, passwordhash, regione, provincia, comune;
    private boolean admin;


    public Cliente(){}
    public Cliente(String email, String password,String nome, String cognome, String data, boolean admin, String regione, String provincia, String comune){
        this.email=email;
        this.setPassword(password);
        this.nome=nome;
        this.cognome=cognome;
        this.data=data;
        this.admin=admin;
        this.regione=regione;
        this.provincia=provincia;
        this.comune=comune;
    }
    public String getEmail(){return email;}
    public String getPasswordhash(){return passwordhash;}
    public String getNome(){return nome;}
    public String getCognome(){return cognome;}
    public String getData(){return data;}
    public boolean isAdmin(){ return admin;}

    public String getComune() {
        return comune;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getRegione() {
        return regione;
    }

    public void setNome(String nome){this.nome=nome;}

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void setPassword(String password){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(password.getBytes(StandardCharsets.UTF_8));
            this.passwordhash = String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }
}
