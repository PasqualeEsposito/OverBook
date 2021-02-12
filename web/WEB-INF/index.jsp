<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jps" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <jsp:include page="header.jsp">
        <jsp:param name="posizione" value="home"/>
        <jsp:param name="titolo" value="OverBook - Oltre il libro"/>
    </jsp:include>
    <div class="corpo">
        <div id="homePageContainer">
            <img src="./imagePage/index1.jpg" id="homeBanner">
            <div id="libriContainer">
                <h2 id="titoloLibri">Libri</h2>
                <div class="prodottiContainer">
                    <c:forEach items="${libri}" begin="0" end="5" var="libro">
                        <div class="prodotto">
                            <img src="${libro.immagine}" width="100" alt="${libro.titolo}" class="immagineProdotto" onclick="itemPage(${libro.id});">
                            <a href="prodotto?id=${libro.id}" class="titoloProdotto" title="${libro.titolo}">${libro.titolo}</a>
                            <p class="autoreProdotto">${libro.autore}</p>
                            <p class="prezzoProdotto">
                                <fmt:formatNumber value = "${libro.prezzo}" type = "currency" currencySymbol="&euro;"/>
                            </p>
                            <div class="aggProdotto">
                                <c:set var="disponibilita" value="${libro.disponibilita}"></c:set>
                                <c:choose>
                                    <c:when test="${disponibilita<=0}">
                                        <img src="./imagePage/add.jpg" title="Prodotto non disponibile" class="notAvailable">
                                    </c:when>
                                    <c:otherwise>
                                        <form action="addCart" method="post">
                                            <input type="hidden" name="quantita" value="1">
                                            <input type="hidden" name="provenienza" value="index">
                                            <input type="hidden" name="idProdotto" value="${libro.id}">
                                            <input type="image" src="./imagePage/add.jpg" class="available" title="Aggiungi al carrello">
                                        </form>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class="scopri">
                    <a href="search?categoria=2&ricerca=" >Scopri di più <i class="fa fa-arrow-circle-right"></i></a>
                </div>
            </div>



            <div id="videogamesContainer">
                <h2 id="titoloVideogames">Videogiochi</h2>
                <div class="prodottiContainer">
                    <c:forEach items="${videogames}" begin="0" end="5" var="videogame">
                        <div class="prodotto">
                            <img src="${videogame.immagine}" alt="${videogame.titolo}" width="100" class="immagineProdotto" onclick="itemPage(${videogame.id});">
                            <a href="prodotto?id=${videogame.id}" class="titoloProdotto" title="${videogame.titolo}">${videogame.titolo}</a>
                            <p class="autoreProdotto">${videogame.autore}</p>
                            <p class="prezzoProdotto">
                                <fmt:formatNumber value = "${videogame.prezzo}" type = "currency" currencySymbol="&euro;"/>
                            </p>
                            <div class="aggProdotto">
                                <c:set var="disponibilita" value="${videogame.disponibilita}"></c:set>
                                <c:choose>
                                    <c:when test="${disponibilita<=0}">
                                        <img src="./imagePage/add.jpg" title="Prodotto non disponibile" class="notAvailable">
                                    </c:when>
                                    <c:otherwise>
                                        <form action="addCart" method="post">
                                            <input type="hidden" name="quantita" value="1">
                                            <input type="hidden" name="provenienza" value="index">
                                            <input type="hidden" name="idProdotto" value="${videogame.id}">
                                            <input type="image" src="./imagePage/add.jpg" class="available" title="Aggiungi al carrello">
                                        </form>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                    <div class="scopri">
                        <a href="search?categoria=1&ricerca=">Scopri di più <i class="fa fa-arrow-circle-right"></i></a>
                    </div>
                </div>
            </div>
        </div>
    <%@include file="footer.html"%>
</div>
</body>
</html>