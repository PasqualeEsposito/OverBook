package controller;

import com.google.gson.Gson;
import model.Prodotto;
import model.ProdottoDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;

@WebServlet("/getTitoli")
public class getTitle extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        int i=0;
        var serviceProdotto = new ProdottoDAO();
        ArrayList<Prodotto> prodotti = serviceProdotto.doRetrieveAll();
        ArrayList<String> titoli = new ArrayList<>();
        for(Prodotto p: prodotti){
            titoli.add(p.getTitolo());

        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();

        response.getWriter().write(gson.toJson(titoli));
    }
}
