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

@WebServlet("/addCart")
public class AddCart extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        var serviceInserimento = new InserimentoDAO();
        var serviceCarrello = new CarrelloDAO();
        var serviceProdotto = new ProdottoDAO();
        boolean trovato = false;
        boolean aggiunto= false;
        Double totale;

        HttpSession session = request.getSession();
        int idProdotto = Integer.parseInt(request.getParameter("idProdotto")); // id del prodotto da aggiungere dal carrello
        int quantita = Integer.parseInt(request.getParameter("quantita")); // quantità da aggiungere al carrello

        Cliente cliente = (Cliente) session.getAttribute("cliente"); // controllare se il cliente è loggato
        ArrayList<ProdottoInCarrello> cart = (ArrayList<ProdottoInCarrello>) session.getAttribute("cart"); // recupera il carrello dalla sessione

        if(cart==null){ //se il carrello non è presente nella sessione
            cart=new ArrayList<>(); // inizializza il carrello
            totale=(double) 0; // inizializza il totale
        }
        else{
            totale = (Double)session.getAttribute("totale"); // recupera il totale dalla sessione
        }


        Prodotto prodott = serviceProdotto.doRetrieveById(idProdotto); // recupera il prodotto da aggiungere

        if (cliente != null) {  // caso in cui il cliente ha effettuato l'accesso
            int idCarrello = serviceCarrello.doRetrieveCartByClient(cliente.getEmail()).getId(); // recupera l'id del carrello

            if(!cart.isEmpty()){ //controlla se il carrello in sessione c'è e non è vuoto
                for(ProdottoInCarrello p: cart){ // va a vedere se il prodotto è già presente nel carrello
                    if(p.getId() == idProdotto){ // se trova il prodotto nel carrello
                        p.setQuantita(quantita); // aggiunge alla quantità presente nel carrello la nuova quantità
                        totale+=p.getProdotto().getPrezzo(); // aggiorna il totale
                        serviceInserimento.doUpdateQuantita(idProdotto, idCarrello, (p.getQuantita())); // aggiorna la quantità nel database
                        trovato=true; // elemento trovato
                        aggiunto=true;
                        break; // termina il ciclo
                    }
                }
                if(!trovato){ // prodotto non presente nel carrello
                    Prodotto p = serviceProdotto.doRetrieveById(idProdotto); // interroga il db per prendere il prodotto
                    totale+=(p.getPrezzo()*quantita); // aggiorna il totale
                    ProdottoInCarrello prod = new ProdottoInCarrello(p,quantita); // crea un nuovo prodotto per il carrello
                    cart.add(prod); // aggiunge il prodotto al carrello
                    aggiunto=true;
                    serviceInserimento.doSave(idCarrello,p,quantita); // aggiorna il database
                }
            }
            if(cart.isEmpty()){ // caso in cui il carrello è vuoto
                Prodotto p = serviceProdotto.doRetrieveById(idProdotto); // interroga il db per prendere il prodotto
                totale+=(p.getPrezzo()*quantita); // aggiorna il totale
                ProdottoInCarrello prod = new ProdottoInCarrello(p,quantita); // crea un nuovo prodotto per il carrello
                cart.add(prod); // aggiunge il prodotto al carrello
                aggiunto=true;
                serviceInserimento.doSave(idCarrello,p,quantita); // aggiorna il database
            }
            session.setAttribute("cart", cart); //carica nella sessione il carrello aggiornato
            session.setAttribute("totale", totale); // carica nella sessione il totale aggiornato
            request.setAttribute("aggiunto",aggiunto); //carica nella request una variabile che indica che il prodotto è stato aggiunto nel carrello
            serviceCarrello.doUpdateTotal(totale, cliente.getEmail()); // modifica il totale nel carrello del database
        }

        if(cliente==null){
            if(!cart.isEmpty()) { //controlla se il carrello in sessione c'è e non è vuoto
                for (ProdottoInCarrello p : cart) { // va a vedere se il prodotto è già presente nel carrello
                    if (p.getId() == idProdotto) { // se trova il prodotto nel carrello
                        p.setQuantita(quantita); // aggiunge alla quantità presente nel carrello la nuova quantità
                        totale += p.getProdotto().getPrezzo(); // aggiorna il totale
                        trovato = true; // elemento trovato
                        aggiunto = true;
                        break; // termina il ciclo
                    }
                }
                if(!trovato){ // prodotto non presente nel carrello
                    Prodotto p = serviceProdotto.doRetrieveById(idProdotto); // interroga il db per prendere il prodotto
                    totale+=(p.getPrezzo()*quantita); // aggiorna il totale
                    ProdottoInCarrello prod = new ProdottoInCarrello(p,quantita); // crea un nuovo prodotto per il carrello
                    cart.add(prod); // aggiunge il prodotto al carrello
                    aggiunto=true;
                }
            }
            if(cart.isEmpty()) { // caso in cui il carrello è vuoto
                Prodotto p = serviceProdotto.doRetrieveById(idProdotto); // interroga il db per prendere il prodotto
                totale+=(p.getPrezzo()*quantita); // aggiorna il totale
                ProdottoInCarrello prod = new ProdottoInCarrello(p,quantita); // crea un nuovo prodotto per il carrello
                cart.add(prod); // aggiunge il prodotto al carrello
                aggiunto=true;
            }
            session.setAttribute("cart", cart); //carica nella sessione il carrello aggiornato
            session.setAttribute("totale", totale); // carica nella sessione il totale aggiornato
            request.setAttribute("aggiunto",aggiunto); //carica nella request una variabile che indica che il prodotto è stato aggiunto nel carrello
        }



        //controlla da quale pagina arriva la richiesta e si comporta di conseguenza
        String provenienza=request.getParameter("provenienza");
        String address="";

        if(provenienza.equalsIgnoreCase("cat")) {
            address = "/WEB-INF/items/categoria.jsp";
            String categoria=request.getParameter("categoria");
            int id = Integer.parseInt(categoria);
            var serviceCategoria= new CategoriaDAO();
            ArrayList<Prodotto> prodottiCategoria = serviceProdotto.doRetrieveByCategoria(id);
            Categoria c = serviceCategoria.doRetrieveById(id);
            request.setAttribute("prodotti",prodottiCategoria);
            request.setAttribute("categoria",c);
            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        }

        if(provenienza.equalsIgnoreCase("index")) {
            address = "/index.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        }
        else if(provenienza.equalsIgnoreCase("paginaProdotto")){
            request.setAttribute("prodotto",prodott);
            address="/WEB-INF/items/prodotto.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        }

    }
}
