package controller;

import model.CarrelloDAO;
import model.Cliente;
import model.ClienteDAO;
import model.Ordine;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

@WebServlet(name = "ChangePassword", urlPatterns = "/change-password")
public class ChangePassword extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Prendo il cliente dal database
        String email = request.getParameter("email");
        String address;
        if(email != null) {
            var service = new ClienteDAO();
            Cliente cliente = service.doRetrieveByEmail(email);

            //Inserisco in oldPass la password criptata dell'utente della sessione
            String oldPass = service.doRetrievePasswordByEmail(email);

            String oldPassword = request.getParameter("oldPassword");
            String newPassword = request.getParameter("newPassword");

            if (oldPassword == null || !Pattern.matches("^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){8,16}$", oldPassword) ||
                    newPassword == null || !Pattern.matches("^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){8,16}$", newPassword)) {
                request.setAttribute("verificato", false);
            } else {
                //Creo un nuovo cliente e gli imposto la vecchia password, così criptata posso confrontarla con la password del cliente loggato
                Cliente x = new Cliente();
                x.setPassword(oldPassword);
                String check = x.getPasswordhash();

                //Se le due vecchie password sono uguali, aggiorno la password dell'utente e inserisco la nuova nel database
                if (oldPass.compareTo(check) == 0) {
                    cliente.setPassword(newPassword);
                    service.doUpdatePasswordByEmail(email, newPassword);
                    request.setAttribute("verificato", true);
                    //Se le due vecchie password sono diverse, non aggiorno la password dell'utente
                } else {
                    request.setAttribute("verificato", false);
                }

                request.setAttribute("cliente", cliente);

                //Reinserisco nuovamente tutti gli ordini fatti dal cliente all'interno della request
                var carrello = new CarrelloDAO();
                ArrayList<Ordine> ordini = carrello.doRetrieveOrder(cliente.getEmail());
                request.setAttribute("ordini", ordini);
            }

            address = "/WEB-INF/user/profilo.jsp";
        }
        else {
            address = "index.jsp";
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
