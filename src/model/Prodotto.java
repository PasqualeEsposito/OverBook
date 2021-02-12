package model;

public class Prodotto {


    private int id;
    private String titolo;
    private int disponibilita;
    private double prezzo;
    private String descrizione;
    private String piattaforma;
    private String data_pubblicazione;
    private String immagine;
    private String autore;
    private String casa_editrice;
    private int categoria;

    public Prodotto(){

    }
    public int getId(){ return this.id; }

    public String getTitolo(){
        return this.titolo;
    }

    public int getDisponibilita(){
        return this.disponibilita;
    }

    public double getPrezzo(){
        return this.prezzo;
    }

    public String getDescrizione(){ return this.descrizione; }

    public String getPiattaforma(){
        return this.piattaforma;
    }

    public String getData_pubblicazione(){
        return this.data_pubblicazione;
    }

    public String getImmagine(){
        return this.immagine;
    }

    public String getAutore(){ return this.autore; }

    public String getCasa_editrice(){
        return this.casa_editrice;
    }

    public int getCategoria(){ return this.categoria;}


    public void setId(int id){ this.id=id; }

    public void setTitolo(String titolo){
        this.titolo=titolo;
    }

    public void setDisponibilita(int disponibilita){
        this.disponibilita=disponibilita;
    }

    public void setPrezzo(double prezzo){
        this.prezzo=prezzo;
    }

    public void setDescrizione(String descrizione){
        this.descrizione=descrizione;
    }

    public void setPiattaforma(String piattaforma){
        this.piattaforma=piattaforma;
    }

    public void setData_pubblicazione(String data_pubblicazione){
        this.data_pubblicazione=data_pubblicazione;
    }

    public void setImmagine(String immagine){
        this.immagine=immagine;
    }

    public void setAutore(String autore){
        this.autore=autore;
    }

    public void setCasa_edit(String casa_editrice){
        this.casa_editrice=casa_editrice;
    }

    public void setCategoria(int categoria){ this.categoria=categoria;}
}

