package controller;

import model.ClienteDAO;
import model.Prodotto;
import model.ProdottoDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminUpdate", urlPatterns = "/admin-sconta")
public class AdminSconta extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = 0;

        try {
            id = Integer.parseInt(request.getParameter("id"));
            var service = new ProdottoDAO();

            Prodotto p = service.doRetrieveById(id);
            double percentuale = Double.parseDouble(request.getParameter("percentuale"));
            if (percentuale < 0 || percentuale > 100) {
                request.setAttribute("notifica", "erroreSconto");
            } else {
                double sconto = p.getPrezzo() - ((p.getPrezzo() * percentuale) / 100);
                service.doUpdatePrezzo(sconto, id);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("notifica", "erroreSconto");
        }

        request.setAttribute("clienti", new ClienteDAO().doRetrieveAll());
        request.setAttribute("prodotti", new ProdottoDAO().doRetrieveAll());
        //serve per aprire direttamente la scheda dei prodotti nel pannello
        request.setAttribute("modificheDaProdotto", true);

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/admin/pannello.jsp");
        dispatcher.forward(request, response);

    }
}
