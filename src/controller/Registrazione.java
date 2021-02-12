package controller;

import model.Carrello;
import model.CarrelloDAO;
import model.Cliente;
import model.ClienteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;


@WebServlet(name = "Registrazione", urlPatterns = "/registrazione")
public class Registrazione extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address;
        boolean check = true;
        var serviceCarrello = new CarrelloDAO();
        //recupero dati dal form di registrazione
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String data = request.getParameter("data");
        String regione=request.getParameter("regione");
        String provincia=request.getParameter("provincia");
        String comune=request.getParameter("comune");
        String url=request.getHeader("Referer");
        boolean admin=false;
        //controllo se la registrazione è avvenuta dal pannello di amministrazione
        String lastPage=url.split("/")[url.split("/").length-1];

        if(lastPage.equalsIgnoreCase("admin")) {
            admin = true;
            request.setAttribute("amministratore", true);
        }

        //controllo che i dati prelevati dal form siano validi
        if (nome.compareTo("")==0 || cognome.compareTo("")==0 || data.compareTo("")==0 || regione.compareTo("")==0 || provincia.compareTo("")==0 || comune.compareTo("")==0)
            check = false;

        if (email.compareTo("")==0 || !Pattern.matches("[A-Za-z.]+[0-9]*@[A-Za-z.]+\\.[A-Za-z]+", email))
            check = false;

        if (password.compareTo("")==0 || !Pattern.matches("^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){8,16}$", password))
            check = false;
        request.setAttribute("check", check);
        //nel caso i dati non siano validi si inoltra la richiesta alla jsp registrazione_errore
        if (!check) {
            address = "WEB-INF/registrazione/registrazione_fallita.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        } else { //altrimenti si procede ad istanziare il javabean ad interrogare il model
            Cliente c = new Cliente(email, password, nome, cognome, data, admin, regione, provincia, comune);
            request.setAttribute("client", c);
            var service = new ClienteDAO();
            //se la query ha avuto successo si inoltra la richiesta alla resitrazione_riuscita.jsp in caso contrario a registrazione_fallita.jsp
            try {
                service.doSave(c);
                serviceCarrello.doSave(email);
                address = "WEB-INF/registrazione/registrazione_riuscita.jsp";
            } catch (RuntimeException e) {
                address = "WEB-INF/registrazione/registrazione_fallita.jsp";
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        }
    }
}
