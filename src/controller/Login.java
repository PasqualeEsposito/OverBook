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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Pattern;

@WebServlet(name = "Login", urlPatterns = "/login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        String address;
        boolean trovato=false;
        var serviceProdotti = new ProdottoDAO();
        var serviceInserimento = new InserimentoDAO();
        var serviceCarrello = new CarrelloDAO();
        PrintWriter out=response.getWriter();
        String risposta=null;

        if(email.compareTo("")==0 || !Pattern.matches("[A-Za-z.]+[0-9]*@[A-Za-z.]+", email) || password.compareTo("")==0)
            risposta="no";
        else{
            var service=new ClienteDAO();
            Cliente cliente=null;
            try{
                cliente=service.doRetrieveByEmailPassword(email, password);
                if(cliente==null)
                    risposta="no";
                else{
                    risposta="ok";
                    HttpSession ssn=request.getSession(/*false*/); // alla fine bisogna mettere false
                    if(ssn!=null)
                        ssn.setAttribute("cliente", cliente);
                    request.setAttribute("cliente", cliente);

                    ArrayList<ProdottoInCarrello> cartSessione =(ArrayList<ProdottoInCarrello>) ssn.getAttribute("cart"); // prende il carrello dalla sessione
                    ArrayList<ProdottoInCarrello> cartCliente = serviceProdotti.doRetrieveCart(cliente.getEmail());; // prende il carrello del cliente dal database
                    int idCarrello = serviceCarrello.doRetrieveCartByClient(cliente.getEmail()).getId(); // prende dal db l'id del carrello
                    double totale;

                    if(cartSessione==null || cartSessione.isEmpty()){ // controlla se nella sessione c'è un carrello vuoto oppure non c'è proprio il cart
                        ssn.setAttribute("cart", cartCliente); // inserisce il carrello nella sessione
                        totale=serviceCarrello.doRetrieveCartByClient(cliente.getEmail()).getTotale(); // ottiene il totale dal database
                        ssn.setAttribute("totale",totale); // inserisce il totale nella sessione

                    }
                    else { // caso in cui nella sessione è presente un carrello
                        totale=serviceCarrello.doRetrieveCartByClient(cliente.getEmail()).getTotale();
                        for (ProdottoInCarrello prodSession : cartSessione) { // fusione dei due carrelli
                            trovato=false;
                            for (ProdottoInCarrello prodClient : cartCliente) {
                                if (prodSession.getId() == prodClient.getId()){
                                    totale+=prodSession.getProdotto().getPrezzo()*prodSession.getQuantita();
                                    prodClient.setQuantita(prodSession.getQuantita());
                                    serviceInserimento.doUpdateQuantita(prodClient.getId(),idCarrello,prodClient.getQuantita()); // aggiorna la quantita nel db
                                    trovato=true;
                                    break;
                                }
                            }
                            if (!trovato){
                                totale+=prodSession.getProdotto().getPrezzo()*prodSession.getQuantita();
                                cartCliente.add(prodSession);
                                serviceInserimento.doSave(idCarrello,prodSession.getProdotto(),prodSession.getQuantita()); // aggiorna il db, inserendo il prodotto
                            }
                        }
                        ssn.setAttribute("cart",cartCliente); // inserimento del nuovo carrello nella sessione
                        System.out.println(totale);
                        ssn.setAttribute("totale",totale);
                        serviceCarrello.doUpdateTotal(totale,cliente.getEmail());
                    }

                }
            }catch (RuntimeException r){
                risposta="no";
            }
        }
        response.setContentType("text/plain");
        out.println(risposta);
    }
}
