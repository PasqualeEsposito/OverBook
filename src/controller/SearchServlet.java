package controller;

import model.Categoria;
import model.CategoriaDAO;
import model.Prodotto;
import model.ProdottoDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "SearchServlet", urlPatterns = "/search")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address;
        int id = Integer.parseInt(request.getParameter("categoria"));
        if(id < 0 || id > 2)
            address = "index.jsp";
        else {
            String ricerca = request.getParameter("ricerca");
            String provenienza = request.getParameter("provenienza");

            if (id == 0 && ricerca.compareTo("") == 0) {
                if (provenienza != null && provenienza.equalsIgnoreCase("carrello")) {
                    request.setAttribute("provenienza", "carrello");
                }
                var service = new ProdottoDAO();
                //recupero dal db i libri e i videogames
                ArrayList<Prodotto> videogames = service.doRetrieveByCategoria(1);
                ArrayList<Prodotto> libri = service.doRetrieveByCategoria(2);
                //metto sia i libri che i videogames nella request
                request.setAttribute("videogames", videogames);
                request.setAttribute("libri", libri);
                address = "/WEB-INF/items/allCategories.jsp";
            } else {
                //Se il cliente filtra solo per categoria
                if (ricerca.compareTo("") == 0) {
                    var service = new ProdottoDAO();
                    ArrayList<Prodotto> prodotti = service.doRetrieveByCategoria(id);
                    var service2 = new CategoriaDAO();
                    Categoria c = service2.doRetrieveById(id);
                    request.setAttribute("categoria", c);
                    request.setAttribute("prodotti", prodotti);
                    address = "/WEB-INF/items/categoria.jsp";
                } else {
                    //Se il cliente ricerca senza categoria
                    if (id == 0) {
                        var service = new ProdottoDAO();
                        ArrayList<Prodotto> p = service.doRetrieveByWord(ricerca);
                        request.setAttribute("ricerca", ricerca);
                        request.setAttribute("prodotti", p);
                        address = "/WEB-INF/items/search-items.jsp";
                    } else {
                        //Se il cliente ricerca con categoria
                        var service = new ProdottoDAO();
                        ArrayList<Prodotto> p = service.doRetrieveByWordCategory(ricerca, id);
                        request.setAttribute("ricerca", ricerca);
                        request.setAttribute("prodotti", p);
                        address = "/WEB-INF/items/search-items.jsp";
                    }
                }
            }
        }


        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);

    }
}
