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
import java.lang.reflect.Array;
import java.util.ArrayList;

@WebServlet("/buy")
public class Buy extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request,response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

        HttpSession session = request.getSession();
        Cliente cliente =(Cliente) session.getAttribute("cliente");
        var serviceCarrello = new CarrelloDAO();
        var serviceProdotto = new ProdottoDAO();
        var serviceInserimento = new InserimentoDAO();
        ArrayList<ProdottoInCarrello> cart = (ArrayList<ProdottoInCarrello>) session.getAttribute("cart");
        double totale=(double) session.getAttribute("totale");
        boolean trovato=false;
        String address="";

        if(cliente==null) { //il cliente non ha fatto il login, quindi lo manda nella pagina di login/registrazione
            address = "./registrazione.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request,response);
        }

        if(cliente!=null){ //il cliente ha fatto il login
            int idCarrello = serviceCarrello.doRetrieveCartByClient(cliente.getEmail()).getId();//recupera id del cart cliente
            for(ProdottoInCarrello p: cart){ //esamina il carrello che sta in sessione ...
                int id = p.getId();          //...per vedere se c'è incongruenza con le quantità disponibili nel DB
                int quantita = p.getQuantita();
                Prodotto prod = serviceProdotto.doRetrieveById(id);
                if(quantita>prod.getDisponibilita() ){
                    trovato=true;  // appena trova un'incongruenza si blocca
                    break;
                }
               // serviceProdotto.doUpdateDisponibilita(quantita,id);
            }
            if(trovato){ // caso in cui ha trovato un'incongruenza
                session.removeAttribute("cart");  // elimina il carrello dalla sessione
                session.removeAttribute("totale"); //elimina il totale dalla sessione
                ArrayList<ProdottoInCarrello> newCart = new ArrayList<>(); // crea un nuovo carrello
                double newTotale=0; //crea un nuovo totale
                for(ProdottoInCarrello pro: cart){ //prende ogni prodotto che stava nel carrello vecchio
                    int id = pro.getId();
                    int quantita = pro.getQuantita();
                    Prodotto prod = serviceProdotto.doRetrieveById(id);
                    if(quantita>prod.getDisponibilita() ){ //verifica se c'è incongruenza
                        if(prod.getDisponibilita()==0) { // verifica se il prodotto è finito
                            serviceInserimento.doDelete(id,idCarrello); //se è finito lo elimina dal carrello
                            continue; //continua il ciclo
                        }//se non è finito..
                       pro.aggiornaQuantita(prod.getDisponibilita()); //  ..aggiorna la quantita
                       newTotale+=(prod.getDisponibilita()*prod.getPrezzo()); //aggiorna il totola
                       newCart.add(pro); //aggiunge il prodotto al nuovo carrello
                       serviceInserimento.doUpdateQuantita(id,idCarrello,pro.getQuantita()); //aggiorna la quantità nel carrello
                    }
                    else{ //se non c'è un'incongruenza aggiunge direttamente il prodotto al nuovo carrello
                        newCart.add(pro);
                        newTotale+=(prod.getPrezzo()*quantita);
                    }
                }
                serviceCarrello.doUpdateTotal(newTotale,cliente.getEmail()); //aggiorna il DB con il nuovo totale
                session.setAttribute("cart",newCart); // carica in sessione il nuovo carrello
                session.setAttribute("totale",newTotale); //carica in sessione il nuovo totale

                //con l'attributo sottostante comunica che non è stato effettuato l'acquisto
                //in seguito ad una incongruenza. L'attributo serve per far comparire il messaggio di errore
                request.setAttribute("acquistoNonEffettuato","acquistoNonEffettuato");
                address = "WEB-INF/results/carrello.jsp";
                RequestDispatcher dispatcher = request.getRequestDispatcher(address);
                dispatcher.forward(request,response);
            }
            else{ // caso in cui non ha trovato incongruenza quindi si può fare l'acquisto
                for(ProdottoInCarrello product: cart) { // aggiorna il DB in seguito all'acquisto
                    int id = product.getId();
                    int quantita = product.getQuantita();
                    Prodotto prod = serviceProdotto.doRetrieveById(id);
                    serviceProdotto.doUpdateDisponibilita(quantita, id);
                }
                session.removeAttribute("cart"); //rimuove il carrello dalla sessione
                session.removeAttribute("totale"); // rimuove il totale dalla sessione
                ArrayList<ProdottoInCarrello> newCart = new ArrayList<>(); // crea un carrello vuoto
                double newTotale=0; // crea il totale vuoto
                session.setAttribute("cart",newCart); //aggiunge il nuovo carrello vuoto
                session.setAttribute("totale",newTotale); // aggiunge il nuovo totale vuoto
                String emailClient = cliente.getEmail();
                serviceCarrello.doUpdateOrdine(emailClient);
                serviceCarrello.doSave(emailClient);

                //con l'attributo sottostante comunica che è stato effettuato l'acquisto
                request.setAttribute("acquistoEffettuato","acquistoEffettuato");
                address = "WEB-INF/results/carrello.jsp";
                RequestDispatcher dispatcher = request.getRequestDispatcher(address);
                dispatcher.forward(request,response);
            }

        }

    }

}
