package controller;

import model.Prodotto;
import model.ProdottoDAO;
import model.ProdottoInCarrello;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "controller.HomeServlet", urlPatterns = "/index.jsp")
public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //inizializzo la sessione
        HttpSession ssn=request.getSession(true);

        var service=new ProdottoDAO();
        //recupero dal db i libri e i videogames
        ArrayList<Prodotto> videogames=service.doRetrieveByCategoria(1);
        ArrayList<Prodotto> libri=service.doRetrieveByCategoria(2);
        //metto sia i libri che i videogames nella request
        request.setAttribute("videogames", videogames);
        request.setAttribute("libri", libri);

        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/index.jsp");
        dispatcher.forward(request, response);
    }
}
