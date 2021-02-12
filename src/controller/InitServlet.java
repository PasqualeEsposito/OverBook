package controller;

import model.Categoria;
import model.CategoriaDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "controller.InitServlet", urlPatterns = "/MyInit", loadOnStartup = 0)
public class InitServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        CategoriaDAO service=new CategoriaDAO();
        ArrayList<Categoria> categorie=service.doRetrieveAll();
        this.getServletContext().setAttribute("categorie", categorie);
        super.init();
    }
}
