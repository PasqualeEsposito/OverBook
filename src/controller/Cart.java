package controller;

import model.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@WebServlet("/cart")
public class Cart extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        doGet(request,response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        var serviceProdotti = new ProdottoDAO();
        var serviceCarrello = new CarrelloDAO();
        var serviceInserimento = new InserimentoDAO();
        double totale = 0;

        HttpSession session = request.getSession();
        Cliente cliente =(Cliente) session.getAttribute("cliente");
        ArrayList<ProdottoInCarrello> cart = (ArrayList<ProdottoInCarrello>) session.getAttribute("cart"); //vede se nel carrello è presente qualcosa


        if(cliente!=null) { // il cliente è loggato
            int idCarrello = serviceCarrello.doRetrieveCartByClient(cliente.getEmail()).getId();//prende id del carrello del cliente
            if (cart != null && !cart.isEmpty()) {
                for (ProdottoInCarrello p : cart) { // per ogni prodotto nel carrello somma il totale
                    Prodotto prod = serviceProdotti.doRetrieveById(p.getId()); //prende il prodotto dal DB
                    p.getProdotto().setPrezzo(prod.getPrezzo()); //aggiorna il prezzo del prodotto che sta in sessione con il prezzo del prodotto preso dal DB
                    serviceInserimento.doUpdatePrezzo(p.getId(),idCarrello,prod.getPrezzo()); // aggiorna il prezzo del prodotto nella tabella inserimento
                    if (p.getQuantita() > 1) {
                        totale += (prod.getPrezzo() * p.getQuantita());
                    }
                    else
                        totale += prod.getPrezzo();
                }
                session.setAttribute("cart",cart); // rimette il carrello nella sessione
                session.setAttribute("totale", totale); //carica nella sessione il totale dei prodotto presenti nel carrello
                serviceCarrello.doUpdateTotal(totale, cliente.getEmail());
            }
        }
        if(cliente == null) { // il cliente non è loggato
            if (cart != null  && !cart.isEmpty()) { // per ogni prodotto nel carrello somma il totale
                for (ProdottoInCarrello p : cart) { // per ogni prodotto nel carrello somma il totale
                    Prodotto prod = serviceProdotti.doRetrieveById(p.getId()); //prende il prodotto dal DB
                    p.getProdotto().setPrezzo(prod.getPrezzo()); //aggiorna il prezzo del prodotto che sta in sessione con il prezzo del prodotto preso dal DB
                    if (p.getQuantita() > 1)
                        totale += (prod.getPrezzo() * p.getQuantita());
                    else
                        totale += prod.getPrezzo();
                }
                session.setAttribute("cart",cart); // rimette il carrello nella sessione
                session.setAttribute("totale", totale); //carica nella sessione il totale dei prodotto presenti nel carrello
            }
        }
        String address = "/WEB-INF/results/carrello.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request,response);
    }
}
