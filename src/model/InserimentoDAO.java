package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InserimentoDAO {

    public boolean doDelete(int idProd, int idCar){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM inserimento WHERE prodotto=? AND carrello=?");
            ps.setInt(1, idProd);
            ps.setInt(2, idCar);
            if (ps.executeUpdate() != 1) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdateQuantita(int idProd, int idCar, int quantita){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE inserimento SET quantita=? WHERE prodotto = ? AND carrello = ?");
            ps.setInt(1, quantita );
            ps.setInt(2, idProd);
            ps.setInt(3,idCar);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("Update error");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdatePrezzo(int idProd, int idCar, double prezzo){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE inserimento SET prezzo=? WHERE prodotto = ? AND carrello = ?");
            ps.setDouble(1, prezzo );
            ps.setInt(2, idProd);
            ps.setInt(3,idCar);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("Update error");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave( int idCar, Prodotto p, int quantita){
        try (Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO inserimento ( carrello, prodotto, quantita, prezzo) " +
                            "VALUE(?,?,?,?)");
            ps.setInt(1, idCar);
            ps.setInt(2, p.getId());
            ps.setInt(3, quantita);
            ps.setDouble(4, p.getPrezzo());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("Insert ERROR");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
