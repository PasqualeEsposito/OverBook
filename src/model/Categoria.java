package model;

public class Categoria {
    private int idCategoria;
    private String nome, descrizione;

    public int getIdCategoria(){return idCategoria;}
    public String getNome(){return nome;}
    public String getDescrizione(){return descrizione;}

    public void setIdCategoria(int id){this.idCategoria=id;}
    public void setNome(String nome){this.nome=nome;}
    public void setDescrizione(String descrizione){this.descrizione=descrizione;}
}
