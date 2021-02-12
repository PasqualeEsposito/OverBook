<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


    <jsp:include page="../header.jsp">
        <jsp:param name="admin" value="true"></jsp:param>
        <jsp:param name="pagina" value="registrazioneAdmin"/>
        <jsp:param name="titolo" value="Pannello di controllo"/>
    </jsp:include>
    <div class="corpo">
        <c:if test="${notifica=='erroreAggiungi'}">
            <div class="alert">
                <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
                <strong>Attenzione!</strong> C'è stato un errore nell'aggiunta del prodotto. Controlla bene i campi.
            </div>
        </c:if>

        <c:if test="${notifica=='erroreModifica'}">
            <div class="alert">
                <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
                <strong>Attenzione!</strong> C'è stato un errore nella modifica del prodotto. Controlla bene i campi.
            </div>
        </c:if>

        <c:if test="${notifica=='erroreSconto'}">
            <div class="alert">
                <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
                <strong>Attenzione!</strong> Inserisci una percentuale di sconto valida.
            </div>
        </c:if>

        <div class="btn-group">
            <button onclick="registrazioneAdmin();">Nuovo admin</button>
            <button onclick="visualizzaProdotti();">Visualizza prodotti</button>
            <button onclick="visualizzaClienti();">Visualizza Clienti</button>
        </div>
        <hr>
        <div id="utentiAdmin" style="display:none">
            <form action="registrazione" method="post" name="registrazione" onsubmit="return validateFormR()">
                <label for="nome">Nome:</label>
                <input type="text" name="nome" id="nome" required>
                <label for="cognome">Cognome:</label>
                <input type="text" name="cognome" id="cognome" required>
                <label for="data">Data di Nascita:</label>
                <input type="date" name="data" id="data" required>
                <label for="emailR">E-mail:</label>
                <input type="email" name="email" id="emailR" required>
                <label for="passwordR">Password:</label>
                <input type="password" name="password" id="passwordR" pattern="^(?=.*\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\w\d\s:])([^\s]){8,16}$"
                       title="La password deve contenere minimo 8 massimo 16 caratteri, deve contenere almeno un numero e un carattere speciale" required>
                <label for="regione">Regione:</label>
                <select id="regione" name="regione" onchange="caricaProvince(value)" required>
                    <option value=""></option>
                </select>
                <label for="provincia">Provincia:</label>
                <select id="provincia" name="provincia" onchange="caricaComuni(value)" required>
                    <option value=""></option>
                </select>
                <label for="comune">Comune:</label>
                <select id="comune" name="comune" required>
                    <option value=""></option>
                </select>
                <input type="submit" value="Registrata nuovo amministratore">
            </form>
        </div>
        <div id="adminProdotti" style="display:none">
            <h2>Prodotti:</h2>

            <!-- Apre form per l'aggiunta di un prodotto -->
            <button id="myBtn">Aggiungi Prodotto</button>

            <!-- Modal con form per l'aggiunta prodotto -->
            <div id="myModal" class="modal">

                <!-- Modal content -->
                <div class="modal-content">
                    <!--Modal Header-->
                    <div class="modal-header">
                        <span class="close">&times;</span>
                        <h2>Aggiungi Prodotto</h2>
                    </div>

                    <!--Modal body-->
                    <div class="modal-body">
                        <form action="aggiungi-prodotto" method="post" enctype="multipart/form-data" name="aggiungiProdotto" onsubmit="return validationProdotto();">
                            <div class="alert erroreAggiunta" style="display: none">
                                <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
                                <strong>Attenzione!</strong>Controlla bene i campi.
                            </div>
                            <label for="categoria">Categoria:</label>
                            <select name="categoria" id="categoria" oninput="visualizzaCampi(value);" required>
                                <option value=""></option>
                                <c:forEach items="${categorie}" var="cat">
                                    <option value="${cat.idCategoria}">${cat.nome}</option>
                                </c:forEach>
                            </select>
                            <label for="img">Immagine:</label>
                            <input type="file" id="img" name="immagine">
                            <label for="titolo">Titolo:</label>
                            <input type="text" id="titolo" name="titolo" required>
                            <label for="descrizione">Descrizione:</label>
                            <input type="text" id="descrizione" name="descrizione" required>
                            <label for="autore">Autore:</label>
                            <input type="text" id="autore" name="autore" required>
                            <label for="prezzo">Prezzo:</label>
                            <input type="text" id="prezzo" name="prezzo" required>
                            <label for="disponibilita">Disponibilita:</label>
                            <input type="number" id="disponibilita" name="disponibilita" required>
                            <label for="data_pubblicazione">Data Pubblicazione:</label>
                            <input type="date" id="data_pubblicazione" name="data_pubblicazione" required>
                            <div id="piattaformaForm" style="display: none">
                                <label for="piattaforma">Piattaforma:</label>
                                <select id="piattaforma" name="piattaforma">
                                    <option value=""></option>
                                    <option value="PS4">PS4</option>
                                    <option value="XBOX">XBox One</option>
                                </select>
                            </div>
                            <div id="casaForm" style="display: none">
                                <label for="casa_editrice">Casa Editrice:</label>
                                <input type="text" id="casa_editrice" name="casa_editrice">
                            </div>
                            <input type="submit" value="Aggiungi">
                        </form>
                    </div>
                </div>
            </div>

            <!--Visualizzazione prodotti desktop-->
            <table>
                <tr>
                    <th>Immagine</th>
                    <th>Nome prodotto</th>
                    <th>Descrizione</th>
                    <th>Autore</th>
                    <th>Prezzo</th>
                    <th>Disponibilit&aacute;</th>
                    <th>Data Pubblicazione</th>
                    <th>Modifica</th>
                    <th>Sconta</th>
                </tr>
                <%int i=0;%>
                <c:forEach items="${prodotti}" var="prodotto">
                    <%i++;%>
                    <tr>
                        <td align="center"><img src="${prodotto.immagine}" alt="${prodotto.titolo}" width="100"></td>
                        <td align="center"><a href="prodotto?id=${prodotto.id}">${prodotto.titolo}</a></td>
                        <td align="center">${prodotto.descrizione}</td>
                        <td align="center">${prodotto.autore}</td>
                        <td align="center">
                            <fmt:formatNumber value = "${prodotto.prezzo}" type = "currency" currencySymbol="&euro;"/></td>
                        <td align="center">${prodotto.disponibilita}</td>
                        <td align="center">${prodotto.data_pubblicazione}</td>
                        <td align="center">
                            <button class="modifica" value="<%=i%>">Modifica</button>
                        </td>
                        <td>
                            <form action="admin-sconta" name="<%="form-sconto"+i%>" method="post" onsubmit="return validateSconto(name)">
                                <input type="hidden" name="id" value="${prodotto.id}">
                                <input type="number" name="percentuale" placeholder="Percentuale da scontare">
                                <input type="submit" name="sconta" class="sconta" value="Sconta Prodotto">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>

            <!--Finestre modal per la modifica dei prodotti-->
            <%i=0;%>
            <c:forEach items="${prodotti}" var="prodotto">
                <%i++;%>
                <div class="modalModifica modal">
                    <div class="modal-content">
                        <div class="modal-header">
                            <span class="close">&times;</span>
                            <h2>Modifica Prodotto</h2>
                        </div>

                        <div class="modal-body">
                            <form action="update-prodotto" name="<%="modificaProdotto"+i%>" id="<%="modificaProdotto"+i%>" method="post" onsubmit="return validationModificaProdotto(name);">
                                <div class="alert erroreModifica" style="display: none">
                                    <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
                                    <strong>Attenzione!</strong> Controlla bene i campi.
                                </div>
                                <input type="hidden" name="id" value="${prodotto.id}">
                                <input type="hidden" name="categoria" value="${prodotto.categoria}">
                                <label for="titoloM">Titolo:</label>
                                <input type="text" id="titoloM" name="titolo" value="${prodotto.titolo}">
                                <label for="descrizioneM">Descrizione:</label>
                                <input type="text" id="descrizioneM" name="descrizione" value="${prodotto.descrizione}">
                                <label for="autoreM">Autore:</label>
                                <input type="text" id="autoreM" name="autore" value="${prodotto.autore}">
                                <label for="prezzoM">Prezzo:</label>
                                <input type="text" id="prezzoM" name="prezzo" value="${prodotto.prezzo}">
                                <label for="disponibilitaM">Disponibilita:</label>
                                <input type="number" id="disponibilitaM" name="disponibilita" value="${prodotto.disponibilita}">
                                <label for="data_pubblicazioneM">Data Pubblicazione:</label>
                                <input type="date" id="data_pubblicazioneM" name="data_pubblicazione" value="${prodotto.data_pubblicazione}">
                                <c:choose>
                                    <c:when test="${!empty prodotto.piattaforma}">
                                        <label for="piattaformaM">Piattaforma:</label>
                                        <select id="piattaformaM" name="piattaforma">
                                            <option value="PS4"
                                                    <c:if test="${prodotto.piattaforma=='PS4'}">selected="selected"</c:if>>PS4</option>
                                            <option value="XBOX"
                                                    <c:if test="${prodotto.piattaforma=='XBOX'}">selected="selected"</c:if> >XBox One</option>
                                        </select>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="hidden" name="piattaforma" value="${prodotto.piattaforma}">
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${!empty prodotto.casa_editrice}">
                                        <label for="casa_editriceM">Casa Editrice:</label>
                                        <input type="text" id="casa_editriceM" name="casa_editrice" value="${prodotto.casa_editrice}">
                                    </c:when>
                                    <c:otherwise>
                                        <input type="hidden" id="casa_editriceM" name="casa_editrice" value="${prodotto.casa_editrice}">
                                    </c:otherwise>
                                </c:choose>
                                <input type="submit" class="modifica-prodotto" value="Modifica">
                            </form>
                        </div>
                    </div>
                </div>
                <!-- </form>-->
            </c:forEach>

            <!--visualizzazione prodotti responsive-->
            <div id="galleria" style="display: none">
                <%i=0;%>
                <c:forEach items="${prodotti}" var="prodotto">
                    <%i++;%>
                    <div class="item">
                        <img class="modalInformazioni" src="${prodotto.immagine}" alt="${prodotto.titolo}" value="<%=i%>">
                        <p>${prodotto.titolo}</p>

                        <button class="modificaResponsive" value="<%=i%>">Modifica</button>


                        <form action="admin-sconta" name="<%="form-sconto-responsive"+i%>" method="post" onsubmit="return validateSconto(name)">
                            <input type="hidden" name="id" value="${prodotto.id}">
                            <input type="number" name="percentuale" placeholder="Percentuale da scontare">
                            <input type="submit" name="sconta" class="sconta" value="Sconta Prodotto">
                        </form>

                        <!-- Modal con form per la visualizzazione dei prodotti -->
                        <div class="infoProdottoResponsive modal">
                            <!-- Modal content -->
                            <div class="modal-content">
                                <!--Modal Header-->
                                <div class="modal-header">
                                    <span class="close">&times;</span>
                                    <h2>Informazioni sul prodotto</h2>
                                </div>

                                <!--Modal body-->
                                <div class="modal-body">
                                    <img src="${prodotto.immagine}">
                                    <div>
                                        <p><span>Nome Prodotto: </span> ${prodotto.titolo}</p>
                                        <p><span>Descrizione: </span>${prodotto.descrizione}</p>
                                        <p><span>Autore: </span>${prodotto.autore}</p>
                                        <p><span>Prezzo: </span><fmt:formatNumber value = "${prodotto.prezzo}" type = "currency" currencySymbol="&euro;"/></p>
                                        <p><span>Disponibilit&aacute;: </span>${prodotto.disponibilita}</p>
                                        <p><span>Data di pubblicazione: </span>${prodotto.data_pubblicazione}</p>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </c:forEach>
            </div>
        </div>

        <div id="visualizzaClienti" style="display: none">
            <table id="tabellaClienti" width="100%">
                <tr>
                    <th>Email</th>
                    <th>Nome</th>
                    <th>Cognome</th>
                    <th>Data Nascita</th>
                    <th>Admin</th>
                    <th>Ordini</th>
                </tr>
                <c:forEach items="${clienti}" var="cliente">
                    <tr>
                        <td align="center">${cliente.email}</td>
                        <td align="center">${cliente.nome}</td>
                        <td align="center">${cliente.cognome}</td>
                        <td align="center">${cliente.data}</td>
                        <td align="center">${cliente.admin ? "Si" : "No"}</td>
                        <td align="center">
                            <form action="visualizza-ordini" method="post">
                                <input type="hidden" name="emailCliente" value="${cliente.email}">
                                <input type="submit" class="bottoneOrdini" value="Visualizza ordini">
                                <input type="image" class="imgOrdini" src="./imagePage/ordini.png" alt="Submit">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>

            <table id="tabellaClienti3" width="100%">
                <tr>
                    <th>Email</th>
                    <th></th>
                    <th>Ordini</th>
                </tr>
                <c:set var="i" value="0" scope="page"></c:set>
                <c:forEach items="${clienti}" var="cliente">
                    <c:set var="i" value="${i+1}" scope="page"></c:set>
                    <tr>
                        <td align="center"><i id="plus${i}"class="fa fa-plus" onclick="mostra(${i})"></i> ${cliente.email}</td>
                        <td>

                        </td>
                        <td align="center">
                            <form action="visualizza-ordini" method="post">
                                <input type="hidden" name="emailCliente" value="${cliente.email}">
                                <input type="image" class="imgOrdini" src="./imagePage/ordini.png" alt="Submit">
                            </form>
                        </td>
                    </tr>
                    <tr id="riga${i}" class="nascosti">
                        <td align="center">
                            <span style="font-weight: bold">Nome: </span><span>${cliente.nome} ${cliente.cognome}</span>
                        </td>
                        <td align="center">
                            <span style="font-weight: bold">Nato il:</span><span>${cliente.data}</span>
                        </td>
                        <td align="center">
                            <span >${cliente.admin ? "<img src='./imagePage/admin.png' style='width:25%'>" : ""} </span>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    <%@include file="./../footer.html"%>
</div>
    <script src="./script/validation.js"></script>
    <script src="./script/adminScript.js"></script>
    <script src="script/ajax-indirizzo.js"></script>
    <script src="script/registrazioneJquery.js"></script>
    <script src="script/modalAggiungiProdotto.js"></script>
    <script src="script/finestreModifica.js"></script>
    <script src="script/validateSconto.js"></script>
    <script src="script/scriptGalleriaAdmin.js"></script>
    <script src="script/tabellaUtentiPannello.js"></script>
    <script>
        <c:if test="${modificheDaProdotto}">
            visualizzaProdotti();
        </c:if>
        <c:if test="${param.visualizzaClienti}">
            visualizzaClienti();
        </c:if>
    </script>
</body>
</html>
