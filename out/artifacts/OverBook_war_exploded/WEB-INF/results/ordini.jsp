<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../header.jsp">
    <jsp:param name="titolo" value="Ordini"/>
    <jsp:param name="admin" value="true"/>
</jsp:include>
<div class="corpo">
    <div id="ordiniAdmin">
        <h1 id="utenteOrdini">Ordini di ${c.nome} ${c.cognome}</h1>
        <c:choose>
            <c:when test="${ordini!=null}">
                <c:forEach items="${ordini}" var="ordine">
                    <div id="ordini">
                        <h4 align="center"> ID Ordine: ${ordine.idOrdine} | Data Ordine: ${ordine.dataOrdine} | Data Arrivo: ${ordine.dataArrivo} </h4> <br>
                        <table id="tabellaOrdiniAdmin" width="100%">
                            <tr>
                                <th></th>
                                <th></th>
                                <th></th>
                                <th>Quantità</th>
                            </tr>
                            <c:forEach items="${ordine.prodotti}" var="prodotto">
                                <tr>
                                    <td align="center"><img src="${prodotto.immagine}" alt="${prodotto.titolo}"></td>
                                    <td align="center">${prodotto.titolo}</td>
                                    <td align="center"><fmt:formatNumber value = "${prodotto.prezzo}" type = "currency" currencySymbol="&euro;"/></td>
                                    <td align="center">${prodotto.disponibilita}</td>
                                </tr>
                            </c:forEach>
                        </table>
                        <h4 align="center"> Totale: <fmt:formatNumber value = "${ordine.totale}" type = "currency" currencySymbol="&euro;"/></h4>
                    </div>
                </c:forEach>
                <a class="return" href="admin?visualizzaClienti=true" >Torna al pannello <i class="fa fa-arrow-circle-left"></i></a>
            </c:when>
            <c:otherwise>
                <h3> Non ci sono ordini da visualizzare</h3>
                <a class="return" href="admin?visualizzaClienti=true" >Torna al pannello <i class="fa fa-arrow-circle-left"></i></a>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<%@include file="./../footer.html"%>
</div>
</body>
</html>