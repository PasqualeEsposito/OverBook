package model;

import java.util.ArrayList;

public class Ordine {
    private int idOrdine;
    private ArrayList<Prodotto> prodotti;
    private double totale;
    private String dataOrdine;
    private String dataArrivo;

    public Ordine(){}

    public int getIdOrdine(){return idOrdine;}
    public ArrayList<Prodotto> getProdotti(){return prodotti;}

    public double getTotale() {
        return totale;
    }

    public String getDataArrivo() {
        return dataArrivo;
    }

    public String getDataOrdine() {
        return dataOrdine;
    }

    public void setDataArrivo(String dataArrivo) {
        this.dataArrivo = dataArrivo;
    }

    public void setDataOrdine(String dataOrdine) {
        this.dataOrdine = dataOrdine;
    }

    public void setIdOrdine(int idOrdine) {
        this.idOrdine = idOrdine;
    }

    public void setProdotti(ArrayList<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }

    public void setTotale(double totale) {
        this.totale = totale;
    }
}
