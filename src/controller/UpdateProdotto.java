package controller;

import model.ClienteDAO;
import model.Prodotto;
import model.ProdottoDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateProdotto", urlPatterns = "/update-prodotto")
public class UpdateProdotto extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean check=true;
        int id=Integer.parseInt(request.getParameter("id"));
        String titolo=request.getParameter("titolo");
        String descrizione=request.getParameter("descrizione");
        String autore=request.getParameter("autore");
        String data=request.getParameter("data_pubblicazione");
        String piattaforma=request.getParameter("piattaforma");
        String casa_editrice=request.getParameter("casa_editrice");
        double prezzo=0;
        int disponibilita=0, categoria=0;


        if(titolo.compareTo("")==0 || descrizione.compareTo("")==0 || autore.compareTo("")==0 || data.compareTo("")==0){
            check=false;
        }

        try{
            prezzo=Double.parseDouble(request.getParameter("prezzo"));
            disponibilita=Integer.parseInt(request.getParameter("disponibilita"));
            categoria=Integer.parseInt(request.getParameter("categoria"));

            if(prezzo<=0 || disponibilita<=0){
                check=false;
            }

            if((categoria==1 && piattaforma.compareTo("")==0) || (categoria==2 && casa_editrice.compareTo("")==0)){
                check=false;
            }
        }catch(NumberFormatException e){
            check=false;
        }

       if(check){
           //istanzio il javabean
           Prodotto p=new Prodotto();

           p.setId(id);
           p.setTitolo(titolo);
           p.setAutore(autore);
           p.setDescrizione(descrizione);
           p.setPrezzo(prezzo);
           p.setDisponibilita(disponibilita);
           p.setData_pubblicazione(data);
           if(piattaforma.compareTo("")==0){
               piattaforma=null;
           }
           if(casa_editrice.compareTo("")==0){
               casa_editrice=null;
           }
           p.setPiattaforma(piattaforma);
           p.setCasa_edit(casa_editrice);
           //memorizzo il nuovo prodotto nel database

           var service=new ProdottoDAO();

           service.doUpdateProdotto(p);
       }else{
           request.setAttribute("notifica", "erroreModifica");
       }

        request.setAttribute("clienti", new ClienteDAO().doRetrieveAll());
        request.setAttribute("prodotti", new ProdottoDAO().doRetrieveAll());
        //serve per aprire direttamente la scheda dei prodotti nel pannello
        request.setAttribute("modificheDaProdotto", true);

        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/admin/pannello.jsp");
        dispatcher.forward(request, response);
    }
}
