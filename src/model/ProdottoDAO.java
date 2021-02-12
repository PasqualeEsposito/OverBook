package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class ProdottoDAO {

    public ArrayList<ProdottoInCarrello> doRetrieveCart(String client) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT prodotto.ID_prodotto, titolo, disponibilita, prodotto.prezzo, descrizione, piattaforma, " +
                            "data_pubblicazione, immagine, autore, casa_editrice, categoria, inserimento.quantita FROM prodotto,carrello,inserimento " +
                            "WHERE inserimento.prodotto=prodotto.ID_prodotto and inserimento.carrello=carrello.ID_carrello and carrello.ordine=false AND carrello.cliente=?");
            ps.setString(1, client);
            ResultSet rs = ps.executeQuery();
            ArrayList<ProdottoInCarrello> risultato = new ArrayList<>();
            int cont=0;
            while (rs.next()) {
                Prodotto p = new Prodotto();
                p.setId(rs.getInt(1));
                p.setTitolo(rs.getString(2));
                p.setDisponibilita(rs.getInt(3));
                p.setPrezzo(rs.getDouble(4));
                p.setDescrizione(rs.getString(5));
                p.setPiattaforma(rs.getString(6));
                p.setData_pubblicazione(rs.getDate(7).toString());
                p.setImmagine(rs.getString(8));
                p.setAutore(rs.getString(9));
                p.setCasa_edit(rs.getString(10));
                p.setCategoria(rs.getInt(11));

                risultato.add(new ProdottoInCarrello(p,rs.getInt(12)));
            }
            return risultato;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public Prodotto doRetrieveById(int idProd) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT prodotto.ID_prodotto, titolo, disponibilita, prezzo, descrizione, piattaforma, " +
                            "data_pubblicazione, immagine, autore, casa_editrice, categoria FROM prodotto " +
                            "WHERE ID_prodotto=?");
            ps.setInt(1, idProd);
            ResultSet rs = ps.executeQuery();
            Prodotto p = new Prodotto();
            if(rs.next()) {
                p.setId(rs.getInt(1));
                p.setTitolo(rs.getString(2));
                p.setDisponibilita(rs.getInt(3));
                p.setPrezzo(rs.getDouble(4));
                p.setDescrizione(rs.getString(5));
                p.setPiattaforma(rs.getString(6));
                p.setData_pubblicazione(rs.getDate(7).toString());
                p.setImmagine(rs.getString(8));
                p.setAutore(rs.getString(9));
                p.setCasa_edit(rs.getString(10));
                p.setCategoria(rs.getInt(11));
            }
            return p;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Prodotto> doRetrieveByCategoria(int categoria) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("select * from prodotto where prodotto.categoria=? order by ID_prodotto DESC;");
            ps.setInt(1, categoria);
            ResultSet rs = ps.executeQuery();
            ArrayList<Prodotto> prodotti=new ArrayList<>();
            while(rs.next()) {
                Prodotto p=new Prodotto();
                p.setId(rs.getInt(1));
                p.setTitolo(rs.getString(2));
                p.setDisponibilita(rs.getInt(3));
                p.setPrezzo(rs.getDouble(4));
                p.setDescrizione(rs.getString(5));
                p.setPiattaforma(rs.getString(6));
                p.setData_pubblicazione(rs.getDate(7).toString());
                p.setImmagine(rs.getString(8));
                p.setAutore(rs.getString(9));
                p.setCasa_edit(rs.getString(10));
                p.setCategoria(rs.getInt(11));
                prodotti.add(p);
            }
            return prodotti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public ArrayList<Prodotto> doRetrieveByWord(String ricerca) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("select * from prodotto;");
            ResultSet rs = ps.executeQuery();
            ArrayList<Prodotto> prodotti = new ArrayList<>();
            while(rs.next()) {
                Prodotto p = new Prodotto();
                String titolo = rs.getString(2).toLowerCase();
                if(titolo.contains(ricerca.toLowerCase()) || titolo.equalsIgnoreCase(ricerca)) {
                    p.setId(rs.getInt(1));
                    p.setTitolo(rs.getString(2));
                    p.setDisponibilita(rs.getInt(3));
                    p.setPrezzo(rs.getDouble(4));
                    p.setDescrizione(rs.getString(5));
                    p.setPiattaforma(rs.getString(6));
                    p.setData_pubblicazione(rs.getDate(7).toString());
                    p.setImmagine(rs.getString(8));
                    p.setAutore(rs.getString(9));
                    p.setCasa_edit(rs.getString(10));
                    p.setCategoria(rs.getInt(11));
                    prodotti.add(p);
                }
            }
            return prodotti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Prodotto> doRetrieveByWordCategory(String ricerca, int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("select * from prodotto WHERE categoria=?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            ArrayList<Prodotto> prodotti = new ArrayList<>();
            while(rs.next()) {
                Prodotto p = new Prodotto();
                String titolo = rs.getString(2).toLowerCase();
                if(titolo.contains(ricerca) || titolo.equalsIgnoreCase(ricerca)) {
                    p.setId(rs.getInt(1));
                    p.setTitolo(rs.getString(2));
                    p.setDisponibilita(rs.getInt(3));
                    p.setPrezzo(rs.getDouble(4));
                    p.setDescrizione(rs.getString(5));
                    p.setPiattaforma(rs.getString(6));
                    p.setData_pubblicazione(rs.getDate(7).toString());
                    p.setImmagine(rs.getString(8));
                    p.setAutore(rs.getString(9));
                    p.setCasa_edit(rs.getString(10));
                    p.setCategoria(rs.getInt(11));
                    prodotti.add(p);
                }
            }
            return prodotti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Prodotto> doRetrieveAll() {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("select * from prodotto order by ID_prodotto desc");
            ResultSet rs = ps.executeQuery();
            ArrayList<Prodotto> prodotti=new ArrayList<>();
            while(rs.next()) {
                Prodotto p=new Prodotto();
                p.setId(rs.getInt(1));
                p.setTitolo(rs.getString(2));
                p.setDisponibilita(rs.getInt(3));
                p.setPrezzo(rs.getDouble(4));
                p.setDescrizione(rs.getString(5));
                p.setPiattaforma(rs.getString(6));
                p.setData_pubblicazione(rs.getDate(7).toString());
                p.setImmagine(rs.getString(8));
                p.setAutore(rs.getString(9));
                p.setCasa_edit(rs.getString(10));
                p.setCategoria(rs.getInt(11));
                prodotti.add(p);
            }
            return prodotti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdatePrezzo(double sconto, int id){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE prodotto set prezzo=? where id_prodotto=?");
            ps.setDouble(1, sconto);
            ps.setInt(2, id);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdateProdotto(Prodotto p){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE prodotto set titolo=?, descrizione=?," +
                    " autore=?, prezzo=?, disponibilita=?, data_pubblicazione=?, piattaforma=?, casa_editrice=? where id_prodotto=?");
            ps.setString(1, p.getTitolo());
            ps.setString(2, p.getDescrizione());
            ps.setString(3, p.getAutore());
            ps.setDouble(4, p.getPrezzo());
            ps.setInt(5, p.getDisponibilita());
            ps.setString(6, p.getData_pubblicazione());
            ps.setString(7, p.getPiattaforma());
            ps.setString(8, p.getCasa_editrice());
            ps.setInt(9, p.getId());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(Prodotto p){
        try (Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO prodotto ( titolo, descrizione, autore, prezzo, disponibilita, data_pubblicazione," +
                            "piattaforma, casa_editrice, categoria, immagine) " +
                            "VALUE(?,?,?,?,?,?,?,?,?,?)");

            ps.setString(1, p.getTitolo());
            ps.setString(2, p.getDescrizione());
            ps.setString(3, p.getAutore());
            ps.setDouble(4, p.getPrezzo());
            ps.setInt(5, p.getDisponibilita());
            ps.setString(6, p.getData_pubblicazione());
            ps.setString(7, p.getPiattaforma());
            ps.setString(8, p.getCasa_editrice());
            ps.setInt(9, p.getCategoria());
            ps.setString(10, p.getImmagine());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("Insert ERROR");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDeleteById(int id){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE  from prodotto where ID_prodotto=?");
            ps.setInt(1, id);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("Delete error");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdateDisponibilita( int quantita, int id){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE prodotto set disponibilita = disponibilita-? where id_prodotto=?");
            ps.setInt(1, quantita);
            ps.setInt(2, id);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}