<%@ page import="model.Prodotto" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<%Prodotto p = (Prodotto) request.getAttribute("prodotto");%>

    <jsp:include page="./../header.jsp">
        <jsp:param name="titolo" value="<%=p.getTitolo()%>"/>
    </jsp:include>
    <div class="corpo">
        <h2 class="nome">${prodotto.titolo}</h2>
        <p class="descrizione">${prodotto.descrizione}</p>
        <div id="containerProdotto">
            <img src="${prodotto.immagine}" alt="${prodotto.titolo}" id="immagineProdotto">
            <div id="descrizioneProdotto">
                <%if(p.getPiattaforma()!=null) {%>
                    <p class="info"><b>Piattaforma: </b>${prodotto.piattaforma}</p>
                <%}%>
                <%if(p.getCasa_editrice()!=null) {%>
                <p class="info"><b>Casa editrice: </b>${prodotto.casa_editrice}</p>
                <%}%>
                <p class="info"><b>Data di pubblicazione: </b>${prodotto.data_pubblicazione}</p>
                <p class="info"><b>Autore: </b>${prodotto.autore}</p>
            </div>
            <div id="aggiungiCarrello">
                <p id="testo"><b>Prezzo: </b><fmt:formatNumber value = "${prodotto.prezzo}" type = "currency" currencySymbol="&euro;"/></p>
                <c:set var="disponibilita" value="${prodotto.disponibilita}"></c:set>
                <c:choose>
                    <c:when test="${disponibilita<=0}">
                        <img src="./imagePage/add.jpg" title="Prodotto non disponibile" class="cantAdd" id="notAvailable">
                    </c:when>
                    <c:otherwise>
                        <form action="addCart" method="post" id="myForm">
                            <div id="submit">
                            <select name="quantita" id="select">
                                    <%for(int i = 1; i <= p.getDisponibilita() && i<=5; i++) {%>
                                        <option value = "<%=i%>"><%=i%></option>
                                    <%}%>
                                    <input type="hidden" name="provenienza" value="paginaProdotto">
                                    <input type="hidden" name = "idProdotto" value="${prodotto.id}">
                                </select>
                                <input type="image" src="./imagePage/add.jpg" title="Aggiungi al carrello" class="addCart" id="available" form="myForm">
                            </div>
                        </form>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
    <%@include file="./../footer.html"%>
</div>
</body>
</html>
