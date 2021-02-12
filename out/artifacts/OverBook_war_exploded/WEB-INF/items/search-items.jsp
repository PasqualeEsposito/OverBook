<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.Prodotto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%ArrayList<Prodotto> prodotti = (ArrayList<Prodotto>) request.getAttribute("prodotti");%>

    <jsp:include page="./../header.jsp">
        <jsp:param name="titolo" value="Ricerca Elemento"/>
    </jsp:include>
    <div class="corpo">
        <div>
            <%if(prodotti.isEmpty()) {%>
            <div id="contenitorenessunrisultato">
                <img id="imagenessunrisultato" alt="immagine nessun risultato" src="./imagePage/nessunrisultato.jpg">
                <div id="nessunrisultato">
                    <h2 class="nessunRisultato">"<%=request.getAttribute("ricerca")%>" non ha condotto a nessun risultato</h2>
                    <h5><a href="./index.jsp">Torna alla homepage</a></h5>
                </div>
            </div>
            <%} else {%>
                <div class="tabellaCategoria">
                <h2 class="titoloCategoria">Hai cercato: "<%=request.getAttribute("ricerca")%>"</h2>
                <table class="tabellaCategoria">
                    <tr>
                        <th></th>
                        <th></th>
                        <th></th>
                        <th></th>
                        <th></th>
                    </tr>
                    <%for(Prodotto p:prodotti) {%>
                    <tr>
                        <td align="center"><img src="<%=p.getImmagine()%>" alt="<%=p.getTitolo()%>" class="immagine" onclick="itemPage(<%=p.getId()%>);"></td>
                        <td align="center"><a href="prodotto?id=<%=p.getId()%>" class="nomeProdotto"><%=p.getTitolo()%></a></td>
                        <td align="center" class="autoreProdotto"><%=p.getAutore()%></td>
                        <td align="center" class="prezzo"><fmt:formatNumber value="<%=p.getPrezzo()%>" type = "currency" currencySymbol="&euro;"/></td>
                        <c:set var="disponibilita" value="<%=p.getDisponibilita()%>"></c:set>
                        <c:choose>
                            <c:when test="${disponibilita<=0}">
                                <td align="center"><img src="./imagePage/add.jpg" title="Prodotto non disponibile" class="cantAdd"></td>
                            </c:when>
                            <c:otherwise>
                        <td align="center">
                            <form action="addCart" method="post">
                                <input type="hidden" name="quantita" value="1">
                                <input type="hidden" name="provenienza" value="index">
                                <input type="hidden" name="idProdotto" value="<%=p.getId()%>">
                                <input type="image" src="./imagePage/add.jpg" title="Aggiungi al carrello" class="addCart">
                            </form>
                        </td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                    <%}%>
                </table>
                <div class="caricaAltri">
                    <span class="bottoneCategoria">Visualizza altri prodotti</span>
                    <span class="top" >Torna su <i class="fa fa-arrow-circle-up"></i></span>
                </div>
            <%}%>
        </div>
    </div>
  </div>
<%@include file="./../footer.html"%>
</div>
<script src="script/caricaElementi.js"></script>
</body>
</html>
