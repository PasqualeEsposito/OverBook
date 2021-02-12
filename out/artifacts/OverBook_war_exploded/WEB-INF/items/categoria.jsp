<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<%--
  Created by IntelliJ IDEA.
  User: Pasquale
  Date: 13/05/2020
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Prodotto" %>
<%@ page import="model.Categoria" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%ArrayList<Prodotto> prodotti = (ArrayList<Prodotto>) request.getAttribute("prodotti");%>
<%Categoria c = (Categoria) request.getAttribute("categoria");%>
    <jsp:include page="./../header.jsp">
        <jsp:param name="posizione" value="<%=c.getNome()%>"/>
        <jsp:param name="titolo" value="<%=c.getNome()%>" />
    </jsp:include>
    <div class="corpo">
        <div id="<%=c.getNome()%>" class="tabellaCategoria">
            <h2 class="titoloCategoria"><%=c.getNome()%></h2>
            <p class="descrizioneCategoria"><%=c.getDescrizione()%></p>

            <table class="tabellaCategoria" width="100%">
                <tr>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                </tr>
                <%for(Prodotto p: prodotti) {%>
                <tr>
                    <td align="center"><img src="<%=p.getImmagine()%>" alt="<%=p.getTitolo()%>" class="immagine" onclick="itemPage(<%=p.getId()%>)"></td>
                    <td align="center"><a href="prodotto?id=<%=p.getId()%>" class="nomeProdotto"><%=p.getTitolo()%></a></td>
                    <td align="center" class="autoreProdotto"><%=p.getAutore()%></td>
                    <td align="center" class="prezzo"><fmt:formatNumber value = "<%=p.getPrezzo()%>" type = "currency" currencySymbol="&euro;"/></td>
                    <c:set var="disponibilita" value="<%=p.getDisponibilita()%>"></c:set>
                    <c:choose>
                        <c:when test="${disponibilita<=0}">
                            <td align="center"><img src="./imagePage/add.jpg" title="Prodotto non disponibile" class="cantAdd"></td>
                        </c:when>
                        <c:otherwise>
                    <td align="center">
                        <form action="addCart">
                            <input type="hidden" name="quantita" value="1">
                            <input type="hidden" name="provenienza" value="cat">
                            <input type="hidden" name="categoria" value="<%=c.getIdCategoria()%>">
                            <input type="hidden" name="idProdotto" value="<%=p.getId()%>">
                            <input type="image" src="./imagePage/add.jpg" title="Aggiungi al carrello" class="addCart">
                        </form>
                    </td>
                        </c:otherwise>
                    </c:choose>
                </tr>
                <%}%>
            </table>
        </div>
        <div class="caricaAltri">
            <span class="bottoneCategoria">Visualizza altri prodotti</span>
            <span class="top" >Torna su <i class="fa fa-arrow-circle-up"></i></span>
        </div>
    </div>
    <script src="./script/homePageScript.js"></script>
    <%@include file="./../footer.html"%>
</div>
<script src="script/caricaElementi.js"></script>
</body>
</html>
