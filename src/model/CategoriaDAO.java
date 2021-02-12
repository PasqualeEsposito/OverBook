package model;

import java.sql.*;
import java.util.ArrayList;

public class CategoriaDAO {

    public ArrayList<Categoria> doRetrieveAll(){
        try (Connection con = ConPool.getConnection()){
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM categoria ORDER BY nome");
            ResultSet rs = ps.executeQuery();
            ArrayList<Categoria> categorie=new ArrayList<>();
            while(rs.next()){
               Categoria c=new Categoria();
               c.setIdCategoria(rs.getInt(1));
               c.setNome((rs.getString(2)));
               c.setDescrizione(rs.getString(3));
               categorie.add(c);
            }
            return categorie;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Categoria doRetrieveById(int id){
        try (Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM categoria WHERE ID_categoria=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Categoria c = new Categoria();
            if(rs.next()){
                c.setIdCategoria(rs.getInt(1));
                c.setNome((rs.getString(2)));
                c.setDescrizione(rs.getString(3));
            }
            return c;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
