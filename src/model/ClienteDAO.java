package model;

import java.sql.*;
import java.util.ArrayList;

public class ClienteDAO {

    //salva i dati del cliente nella teballe Cliente del database
    public void doSave(Cliente c) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO cliente ( email, password, nome, cognome, data_nascita, regione, provincia, comune, admin) " +
                            "VALUE(?,?,?,?,?,?,?,?,?)");
            ps.setString(1, c.getEmail());
            ps.setString(2, c.getPasswordhash());
            ps.setString(3, c.getNome());
            ps.setString(4, c.getCognome());
            ps.setString(5, c.getData());
            ps.setString(6, c.getRegione());
            ps.setString(7, c.getProvincia());
            ps.setString(8, c.getComune());
            ps.setBoolean(9, c.isAdmin());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("Insert ERROR");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //controlla se esiste nella tabelle Cliente un cliente con email e password corrispondenti ai parametri passati
    public Cliente doRetrieveByEmailPassword(String email, String password) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM cliente WHERE email=? AND password=SHA1(?)");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Cliente c = new Cliente();
                c.setEmail(rs.getString(1));
                c.setNome(rs.getString(3));
                c.setCognome(rs.getString(4));
                c.setData(rs.getString(5));
                c.setRegione(rs.getString(6));
                c.setProvincia(rs.getString(7));
                c.setComune(rs.getString(8));
                c.setAdmin(rs.getBoolean(9));
                return c;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Cliente> doRetrieveAll() {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("select * from cliente");
            ResultSet rs = ps.executeQuery();
            ArrayList<Cliente> clienti = new ArrayList<>();
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setEmail(rs.getString(1));
                c.setNome(rs.getString(3));
                c.setCognome(rs.getString(4));
                c.setData(rs.getString(5));
                c.setRegione(rs.getString(6));
                c.setProvincia(rs.getString(7));
                c.setComune(rs.getString(8));
                c.setAdmin(rs.getBoolean(9));
                clienti.add(c);
            }
            return clienti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Cliente doRetrieveByEmail(String email) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM cliente WHERE email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Cliente c = new Cliente();
                c.setEmail(rs.getString(1));
                c.setNome(rs.getString(3));
                c.setCognome(rs.getString(4));
                c.setData(rs.getString(5));
                c.setRegione(rs.getString(6));
                c.setProvincia(rs.getString(7));
                c.setComune(rs.getString(8));
                c.setAdmin(rs.getBoolean(9));
                return c;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String doRetrievePasswordByEmail(String email) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM cliente WHERE email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Cliente c = new Cliente();
                return rs.getString(2);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdatePasswordByEmail(String email, String password) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE cliente SET password = SHA1(?) WHERE email=?");
            ps.setString(1, password);
            ps.setString(2, email);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("Update error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
