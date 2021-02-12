package controller;

import model.Cliente;
import model.ClienteDAO;
import model.Prodotto;
import model.ProdottoDAO;

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

@WebServlet(name = "Admin", urlPatterns = "/admin")
public class Admin extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address=null;
        var service=new ProdottoDAO();
        var service2=new ClienteDAO();

        ArrayList<Cliente> clienti=service2.doRetrieveAll();
        ArrayList<Prodotto> prodotti=service.doRetrieveAll();

        request.setAttribute("prodotti", prodotti);
        request.setAttribute("clienti", clienti);

        Cliente c=null;

        HttpSession ssn=request.getSession(false);
        if(ssn!=null){
            c=(Cliente)ssn.getAttribute("cliente");
        }

        if(c==null || !c.isAdmin()){
            address="index.jsp";
        }else address="WEB-INF/admin/pannello.jsp";

        RequestDispatcher dispatcher=request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}
