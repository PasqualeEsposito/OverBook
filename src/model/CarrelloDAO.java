package model;


import java.sql.*;
import java.util.ArrayList;

public class CarrelloDAO {

    public Carrello doRetrieveCartByClient(String client) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT ID_carrello, totale, cliente FROM carrello where ordine=false AND cliente=? ");
            ps.setString(1, client);
            ResultSet rs = ps.executeQuery();
            Carrello c = new Carrello();
            if (rs.next()) {
                c.setId(rs.getInt(1));
                c.setTotale(rs.getDouble(2));
                c.setCliente(rs.getString(3));
                return c;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error");
        }
    }

    public void doUpdateTotal(double total, String client) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE carrello SET totale=? WHERE ordine = false AND cliente = ?");
            ps.setDouble(1, total);
            ps.setString(2, client);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdateOrdine(String emailClient) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE carrello SET ordine=true, data_ordine=CURRENT_DATE(), data_arrivo=DATE_ADD(CURRENT_DATE, INTERVAL 2 DAY)  WHERE cliente = ? AND ordine=false");
            ps.setString(1, emailClient);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void doSave(String emailCliente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO carrello (totale, data_ordine, data_arrivo, cliente, ordine) VALUES(?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            ps.setDouble(1, 0);
            ps.setDate(2, null);
            ps.setDate(3, null);
            ps.setString(4, emailCliente);
            ps.setBoolean(5, false);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
          /*  ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            customer.setId(id);*/
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Ordine> doRetrieveOrder(String client) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("select c.ID_carrello, p.immagine, p.titolo, i.prezzo, c.cliente, c.data_ordine, c.data_arrivo, i.quantita, c.totale from (carrello c join inserimento i on c.ID_carrello = i.carrello) join prodotto p on prodotto=ID_prodotto\n" +
                            "where ordine=true and cliente=?;");
            ps.setString(1, client);
            ResultSet rs = ps.executeQuery();
            ArrayList<Prodotto> prodotti = new ArrayList<>();
            ArrayList<Ordine> ordini = new ArrayList<>();
            if (rs.next()) {
                double totale = 0;
                String dataArrivo = null, dataOrdine = null;
                int idPrec = rs.getInt(1);
                int id;
                rs.first();
                do {
                    if ((id = rs.getInt(1)) != idPrec) {
                        Ordine o = new Ordine();
                        o.setIdOrdine(idPrec);
                        o.setProdotti(prodotti);
                        o.setTotale(totale);
                        o.setDataArrivo(dataArrivo);
                        o.setDataOrdine(dataOrdine);
                        ordini.add(o);
                        prodotti = new ArrayList<>();
                    }
                    idPrec = id;
                    Prodotto p = new Prodotto();
                    p.setImmagine(rs.getString(2));
                    p.setTitolo(rs.getString(3));
                    p.setPrezzo(rs.getDouble(4));
                    p.setDisponibilita(rs.getInt(8));
                    prodotti.add(p);
                    totale = rs.getDouble(9);
                    dataOrdine = rs.getString(6);
                    dataArrivo = rs.getString(7);
                } while (rs.next());
                Ordine o = new Ordine();
                o.setIdOrdine(idPrec);
                o.setProdotti(prodotti);
                o.setTotale(totale);
                o.setDataArrivo(dataArrivo);
                o.setDataOrdine(dataOrdine);
                ordini.add(o);
                return ordini;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error");
        }
    }


}