package model;

public class Carrello {
    private int id;
    private String data_ordine;
    private double totale;
    private String data_arrivo;
    private String cliente;
    private boolean ordine;

    public Carrello(){

    }

    public boolean isOrdine(){
        return this.ordine;
    }

    public void setOrdine(boolean value){
        this.ordine=value;
    }

    public int getId(){
        return id;
    }

    public String getData_ordine(){
        return data_ordine;
    }

    public double getTotale(){
        return totale;
    }

    public String getData_arrivo(){
        return data_arrivo;
    }

    public String getCliente(){
        return cliente;
    }

    public void setId(int id){
        this.id=id;
    }

    public void setData_ordine(String data_ordine){
        this.data_ordine=data_ordine;
    }

    public void setData_arrivo(String data_arrivo){
        this.data_arrivo=data_arrivo;
    }

    public void setTotale(double totale){
        this.totale=totale;
    }

    public void setCliente(String cliente){
        this.cliente=cliente;
    }
}
