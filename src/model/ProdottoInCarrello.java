package model;

public class ProdottoInCarrello {
    private Prodotto p;
    private int quantita;


    public ProdottoInCarrello(Prodotto prod, int quantita){
        this.p=prod;
        this.quantita=quantita;
    }

    public Prodotto getProdotto(){
        return this.p;
    }

    public int getQuantita(){
        return this.quantita;
    }

    public void setProdotto(Prodotto pro){
        this.p = pro;
    }

    public void aggiornaQuantita(int x){this.quantita=x;}
    public void setQuantita(int x) {
        this.quantita+=x;
    }

    public void subQuantita(){
        this.quantita--;
    }

    public void addQuantita() {
        this.quantita++;
    }

    public int getId(){
        return p.getId();
    }
}
