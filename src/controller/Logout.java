package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Logout", urlPatterns = "/logout")
public class Logout extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession ssn=request.getSession(false);
        if(ssn!=null){
            ssn.removeAttribute("cliente");
            ssn.removeAttribute("cart");
            ssn.removeAttribute("totale");
        }

        RequestDispatcher dispatcher=request.getRequestDispatcher("./index.jsp");
        dispatcher.forward(request, response);
    }
}
