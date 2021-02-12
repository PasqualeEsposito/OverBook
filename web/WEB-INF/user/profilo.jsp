<%--
  Created by IntelliJ IDEA.
  User: Pasquale
  Date: 08/06/2020
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="model.Cliente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<%Cliente cliente = (Cliente) request.getAttribute("cliente");%>
    <jsp:include page="../header.jsp">
        <jsp:param name="titolo" value="Profilo"/>
    </jsp:include>
    <div class="corpo">
        <div id="containerProfilo">
            <img id="immagineprofilo" src="./imagePage/user.jpg">
            <h1 id="titoloprofilo">Profilo</h1>
        </div>
        <div id="corpoprofilo">
            <p><b>Cognome: </b><%=cliente.getCognome()%></p>
            <p><b>Nome: </b> <%=cliente.getNome()%></p>
            <p><b>e-mail: </b> <%=cliente.getEmail()%></p>
            <label><b>Password: </b></label><button onclick="showFields();" class="bottoneprofilo">Cambia password</button>
            <c:if test="${verificato==false}">
                <h4 id="alertPassword">
                    Attenzione! La vecchia password è errata. Riprova
                </h4>
            </c:if>
            <c:if test="${verificato==true}">
                <h4 id="donePassword">
                    Password aggiornata con successo!
                </h4>
            </c:if>
            <div id="cambiaPassword" style="display: none">
                <form name="password" action="change-password" method="post" onsubmit="return verifica()">
                    <table id="formCambioPassword">
                        <tr>
                            <th></th>
                            <th></th>
                        </tr>
                        <tr>
                            <td>Password: </td>
                            <td><input type="password" name="oldPassword" id="pass" pattern="^(?=.*\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\w\d\s:])([^\s]){8,16}$" required></td>
                        </tr>
                        <tr>
                            <td>Nuova password: </td>
                            <td><input type="password" name="newPassword" id="pass1" pattern="^(?=.*\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\w\d\s:])([^\s]){8,16}$"
                                       title="La password deve contenere minimo 8 massimo 16 caratteri, deve contenere almeno un numero e un carattere speciale" required></td>
                        </tr>
                        <tr>
                            <td>Reinserisci nuova password: </td>
                            <td><input type="password" name="password2" id="pass2" pattern="^(?=.*\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\w\d\s:])([^\s]){8,16}$"
                                       title="La password deve contenere minimo 8 massimo 16 caratteri, deve contenere almeno un numero e un carattere speciale" required></td>
                        </tr>
                    </table>
                    <input type="hidden" name="email" value="<%=cliente.getEmail()%>">
                    <input type="Submit" value="Conferma" class="bottoneprofilo">
                </form>
                </div>
            <p><b>Data di nascita: </b><%=cliente.getData()%></p>
            <p><b>Regione: </b><%=cliente.getRegione()%></p>
            <p><b>Provincia: </b><%=cliente.getProvincia()%></p>
            <p><b>Comune: </b><%=cliente.getComune()%></p>
        </div>
        <br>
        <br>

        <div id="ordiniProfilo">
            <c:if test="${ordini!=null}">
                <c:forEach items="${ordini}" var="ordine">
                    <div id="ordiniP">
                        <h4 align="center"> ID Ordine: ${ordine.idOrdine} - Data Ordine: ${ordine.dataOrdine} - Data Arrivo: ${ordine.dataArrivo} </h4> <br>
                        <table width="100%" id="tabellaOrdini">
                            <tr>
                                <th></th>
                                <th></th>
                                <th></th>
                                <th>Quantità</th>
                            </tr>
                            <c:forEach items="${ordine.prodotti}" var="prodotto">
                                <tr>
                                    <td align="center"><img src="${prodotto.immagine}" class="ordiniImmagine"></td>
                                    <td align="center">${prodotto.titolo}</td>
                                    <td align="center"><fmt:formatNumber value = "${prodotto.prezzo}" type = "currency" currencySymbol="&euro;"/></td>
                                    <td align="center">${prodotto.disponibilita}</td>
                                </tr>
                            </c:forEach>
                        </table>
                        <h4 align="center"> Totale: <fmt:formatNumber value = "${ordine.totale}" type = "currency" currencySymbol="&euro;"/></h4>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </div>

    <%@include file="../footer.html"%>

    <script src="./script/cambiaPassword.js"></script>
</body>
</html>
