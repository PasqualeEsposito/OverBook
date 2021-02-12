<%@ page import="model.Prodotto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Carrello" %>
<%@ page import="model.ProdottoInCarrello" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% ArrayList<ProdottoInCarrello> cart =(ArrayList<ProdottoInCarrello>) session.getAttribute("cart"); %>
<% Double totale = (Double) session.getAttribute("totale"); %>
<% String notifica = (String) request.getAttribute("acquistoEffettuato"); %>

  <jsp:include page="../header.jsp">
    <jsp:param name="titolo" value="Carrello"/>
  </jsp:include>
  <div class="corpo">
    <div id="carrello">
      <c:if test="${acquistoNonEffettuato!=null}">
        <h4 id="alertCart">Attenzione! La disponibilità in magazzino di alcuni prodotti da te selezionati è variata
          nel tempo oppure non è abbastanza per soddisfare la tua richiesta. Abbiamo aggiornato il tuo carrello
          con la quantità massima di prodotti che puoi acquistare.</h4>
      </c:if>
      <% if(cart!=null && !cart.isEmpty()) { %>
      <h2 id="tuoiprodotti">I tuoi prodotti</h2>
      <table id="tabellaCarrello">
        <tr>
          <th></th>
          <th></th>
          <th id="prezzotable" align="right">Prezzo</th>
        </tr>
        <%for(ProdottoInCarrello p: cart) { %>
        <tr class="rigatab">
          <td><img src="<%=p.getProdotto().getImmagine()%>" alt="<%=p.getProdotto().getTitolo()%>"></td>
          <td>
            <a href="prodotto?id=<%=p.getId()%>"><%=p.getProdotto().getTitolo()%></a> <span id="autore">di <%=p.getProdotto().getAutore()%></span>
            <div id="quantitacarrello">Quantità: <%=p.getQuantita()%></div>
            <div>
              <form id="formCarrello" action="delete" method="post">
                <input type="hidden" name = "idProdotto"value ="<%=p.getProdotto().getId()%>">
                <input type="submit" value="Rimuovi">
              </form>
            </div>
          </td>
          <td id="prezzoCarrello" align="right"><fmt:formatNumber value = "<%=p.getProdotto().getPrezzo()%>" type = "currency" currencySymbol="&euro;"/></td>
        </tr>
        <%}%>
      </table><br>
      <h4 id="totalecarrello" align="right">Totale: <span id="prezzototale"><fmt:formatNumber value = "<%=totale%>" type = "currency" currencySymbol="&euro;"/></span></h4>
      <div id="acquista" align="right">
        <form action="buy" method="post">
          <input type="submit" value="Acquista">
        </form>
      </div>
      <%} else if(notifica!=null){ %>
      <h1 id="acquistoSuccesso">Acquisto effettuato con successo</h1>
      <a class="return" href="./index.jsp">Torna alla homepage</a>
      <div class="successo"><img src="./imagePage/acquistoEffettuato2.jpg"></div>
      <%} else { %>
      <div id="contenitorecarrellovuoto">
        <img id="imagecarrellovuoto" src="./imagePage/carrellovuoto2.jpg">
        <div id="carrellovuoto">
          <h2>Il tuo carrello Overbook è vuoto</h2>
          <h5><a href="search?categoria=0&ricerca=&provenienza=carrello">Dai un'occhiata al nostro catalogo</a></h5>
        </div>
      </div>
      <%}%>
    </div>
  </div>
  <%@include file="./../footer.html"%>
</div>
</body>
</html>
