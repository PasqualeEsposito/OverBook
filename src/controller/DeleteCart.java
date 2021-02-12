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

@WebServlet("/delete")
public class DeleteCart extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        doGet(request,response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

        var serviceInserimento = new InserimentoDAO();
        var serviceCarrello = new CarrelloDAO();

        HttpSession session  = request.getSession();
        int idProdotto = Integer.parseInt(request.getParameter("idProdotto")) ; // id del prodotto da rimuovere dal carrello

        Cliente cliente =(Cliente) session.getAttribute("cliente"); // controllare se il cliente è loggato
        ArrayList<ProdottoInCarrello> cart = (ArrayList<ProdottoInCarrello>) session.getAttribute("cart"); // recupera il carrello dalla sessione
        Double totale = (Double) session.getAttribute("totale"); // recupera il totale dalla sessione


        if (cliente != null) {  // caso in cui il cliente ha effettuato l'accesso
            int idCarrello = serviceCarrello.doRetrieveCartByClient(cliente.getEmail()).getId(); // recupera l'id del carrello
            for (ProdottoInCarrello p : cart) {
                if (p.getId() == idProdotto && p.getQuantita() > 1) {  // individua il prodotto da cancellare
                    p.subQuantita(); // rimuove il prodotto
                    totale -= p.getProdotto().getPrezzo(); // modifica il totale
                    serviceInserimento.doUpdateQuantita(idProdotto, idCarrello, (p.getQuantita())); // aggiorna il carrello nel database
                    break;
                }
                if (p.getId() == idProdotto && p.getQuantita() == 1) {
                    cart.remove(p);
                    totale -= p.getProdotto().getPrezzo(); // modifica il totale
                    serviceInserimento.doDelete(idProdotto, idCarrello); // aggiorna il carrello nel database
                    break;
                }
            }
            session.setAttribute("cart", cart); //carica nella sessione il carrello aggiornato
            session.setAttribute("totale", totale); // carica nella sessione il totale aggiornato
            serviceCarrello.doUpdateTotal(totale, cliente.getEmail()); // modifica il totale nel carrello del database
        }

        if (cliente == null) { //caso in cui il cliente non ha effettuato l'accesso
            for (ProdottoInCarrello p : cart) {
                if (p.getId() == idProdotto && p.getQuantita() > 1) {  // individua il prodotto da cancellare
                    p.subQuantita(); // rimuove il prodotto
                    totale -= p.getProdotto().getPrezzo(); // modifica il totale
                    break;
                }
                if (p.getId() == idProdotto && p.getQuantita() == 1) {
                    cart.remove(p);
                    totale -= p.getProdotto().getPrezzo(); // modifica il totale
                    break;
                }
            }
            session.setAttribute("cart", cart); //carica nella sessione il carrello aggiornato
            session.setAttribute("totale", totale); // carica nella sessione il totale aggiornato
        }
        String address = "/WEB-INF/results/carrello.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request,response);
    }
}
