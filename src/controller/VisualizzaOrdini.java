package controller;

import model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "VisualizzaOrdini", urlPatterns = "/visualizza-ordini")
public class VisualizzaOrdini extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email=request.getParameter("emailCliente");

        var service=new CarrelloDAO();
        var service2=new ClienteDAO();

        Cliente c=service2.doRetrieveByEmail(email);
        ArrayList<Ordine> ordini=service.doRetrieveOrder(email);

        request.setAttribute("ordini", ordini);
        request.setAttribute("c", c);
        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/results/ordini.jsp");
        dispatcher.forward(request, response);
    }
}
