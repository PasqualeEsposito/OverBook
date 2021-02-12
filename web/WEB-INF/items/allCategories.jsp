<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jps" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="./../header.jsp">
    <jsp:param name="titolo" value="Tutte le categorie"/>
</jsp:include>
<div class="corpo">
    <c:if test="${provenienza==null}">
        <h4 id="alertCategoria">Non hai inserito nessuna parola chiave! Ti Proponiamo alcuni prodotti del nostro catalogo</h4>
    </c:if>
    <div class="tabellaCategoria">
        <div id="libri">
        <h2 class="titoloCategoria">Libri</h2>
        <table width="100%">
            <tr>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${libri}" begin="0" end="3" var="libro">
                <tr>
                    <td align="center"><img src="${libro.immagine}" alt="${libro.titolo}" class="immagine" onclick="itemPage(${libro.id});"></td>
                    <td align="center"><a href="prodotto?id=${libro.id}" class="nomeProdotto">${libro.titolo}</a></td>
                    <td align="center" class="autoreProdotto">${libro.autore}</td>
                    <td align="center" class="prezzo"><fmt:formatNumber value = "${libro.prezzo}" type = "currency" currencySymbol="&euro;"/></td>
                    <c:set var="disponibilita" value="${libro.disponibilita}"></c:set>
                    <c:choose>
                        <c:when test="${disponibilita<=0}">
                            <td align="center"><img src="./imagePage/add.jpg" title="Prodotto non disponibile" class="cantAdd"></td>
                        </c:when>
                        <c:otherwise>
                            <td align="center">
                                <form action="addCart" method="post">
                                    <input type="hidden" name="quantita" value="1">
                                    <input type="hidden" name="provenienza" value="index">
                                    <input type="hidden" name="idProdotto" value="${libro.id}">
                                    <input type="image" src="./imagePage/add.jpg" title="Aggiungi al carrello" class="addCart">
                                </form>
                            </td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
        </table>
        <div class="scopri" > <a href="search?categoria=2&ricerca=" >Scopri di più <i class="fa fa-arrow-circle-right"></i> </a> </div>
    </div>
        <div id="videogames">
        <h2 class="titoloCategoria">Videogiochi</h2>
        <table width="100%">
            <tr>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${videogames}" begin="0" end="3" var="videogame">
                <tr>
                    <td align="center"><img src="${videogame.immagine}" alt="${videogame.titolo}" class="immagine" onclick="itemPage(${videogame.id});"></td>
                    <td align="center"><a href="prodotto?id=${videogame.id}" class="nomeProdotto">${videogame.titolo}</a></td>
                    <td align="center" class="autoreProdotto">${videogame.autore}</td>
                    <td align="center" class="prezzo"><fmt:formatNumber value = "${videogame.prezzo}" type = "currency" currencySymbol="&euro;"/></td>
                    <c:set var="disponibilita" value="${videogame.disponibilita}"></c:set>
                    <c:choose>
                        <c:when test="${disponibilita<=0}">
                            <td align="center"><img src="./imagePage/add.jpg" title="Prodotto non disponibile" class="cantAdd"></td>
                        </c:when>
                        <c:otherwise>
                            <td align="center">
                                <form action="addCart" method="post">
                                    <input type="hidden" name="quantita" value="1">
                                    <input type="hidden" name="provenienza" value="index">
                                    <input type="hidden" name="idProdotto" value="${videogame.id}">
                                    <input type="image" src="./imagePage/add.jpg" title="Aggiungi al carrello" class="addCart">
                                </form>
                            </td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
        </table>
        <div class="scopri"> <a href="search?categoria=1&ricerca=">Scopri di più <i class="fa fa-arrow-circle-right"></i></a> </div>
    </div>
    </div>
</div>
<%@include file="./../footer.html"%>
</div>
</body>
</html>
