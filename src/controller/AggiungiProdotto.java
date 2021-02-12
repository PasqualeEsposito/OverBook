package controller;

import model.ClienteDAO;
import model.Prodotto;
import model.ProdottoDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

@WebServlet(name = "AggiungiProdotto", urlPatterns = "/aggiungi-prodotto")
@MultipartConfig
public class AggiungiProdotto extends HttpServlet {

    private String webTempPath;

    @Override
    public void init() throws ServletException {
        super.init();
        webTempPath = getServletContext().getRealPath("/") + "img";
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean check = true;
        String titolo = request.getParameter("titolo");
        String nomeFile = null;
        //recupero l'immagine dalla request
        Part part = request.getPart("immagine");
        if (part.getSize() == 0) //nel caso non si sia inserita nessuna immagine, se ne mette una di default
            nomeFile = "immagine-vuota.jpg";
        else {
            nomeFile = titolo.replace(" ", "");
            if (nomeFile.length() >= 10) {
                nomeFile = nomeFile.substring(0, 10) + ".jpg";
            } else {
                nomeFile = nomeFile + ".jpg";
            }
            InputStream is = part.getInputStream();
            BufferedInputStream bin = new BufferedInputStream(is);
            FileOutputStream fos = new FileOutputStream(new File(webTempPath + File.separator, nomeFile));
            int ch = 0;
            //memorizzo il file nella cartella img sul server
            while ((ch = bin.read()) != -1)
                fos.write(ch);
            fos.flush();
            fos.close();
            bin.close();
        }
        //recupero i dati dalla request
        String descrizione = request.getParameter("descrizione");
        String autore = request.getParameter("autore");
        String data = request.getParameter("data_pubblicazione");
        String piattaforma = request.getParameter("piattaforma");
        String casa_editrice = request.getParameter("casa_editrice");
        int categoria = 0, disponibilita = 0;
        double prezzo = 0;

        if (titolo.compareTo("") == 0 || descrizione.compareTo("") == 0 || autore.compareTo("") == 0 || data.compareTo("") == 0) {
            check = false;
        }

        try {
            prezzo = Double.parseDouble(request.getParameter("prezzo"));
            disponibilita = Integer.parseInt(request.getParameter("disponibilita"));
            categoria = Integer.parseInt(request.getParameter("categoria"));

            if ((categoria == 1 && piattaforma.compareTo("") == 0) || (categoria == 2 && casa_editrice.compareTo("") == 0)) {
                check = false;
            }

            if (prezzo <= 0 || disponibilita <= 0) {
                check = false;
            }
        } catch (NumberFormatException e) {
            check = false;
        }

        if (check) {
            //istanzio il javabean
            Prodotto p = new Prodotto();

            p.setTitolo(titolo);
            p.setAutore(autore);
            p.setDescrizione(descrizione);
            p.setPrezzo(prezzo);
            p.setDisponibilita(disponibilita);
            p.setData_pubblicazione(data);
            if (piattaforma.compareTo("") == 0)
                piattaforma = null;
            if (casa_editrice.compareTo("") == 0)
                casa_editrice = null;
            p.setPiattaforma(piattaforma);
            p.setCasa_edit(casa_editrice);
            p.setCategoria(categoria);
            p.setImmagine("./img/" + nomeFile);
            //memorizzo il nuovo prodotto nel database
            var service = new ProdottoDAO();
            service.doSave(p);
        } else {
            request.setAttribute("notifica", "erroreAggiungi");
        }

        request.setAttribute("clienti", new ClienteDAO().doRetrieveAll());
        request.setAttribute("prodotti", new ProdottoDAO().doRetrieveAll());
        //serve per aprire direttamente la scheda dei prodotti nel pannello
        request.setAttribute("modificheDaProdotto", true);

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/admin/pannello.jsp");
        dispatcher.forward(request, response);
    }

}
