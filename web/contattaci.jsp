<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <jsp:include page="WEB-INF/header.jsp">
        <jsp:param name="posizione" value="contattaci"/>
        <jsp:param name="titolo" value="Contattaci"/>
    </jsp:include>
    <div class="corpo">
        <div id="containerContact">
            <img id="call" alt="immagine call center" src="./imagePage/callcenter2.bmp">

            <div id="titolocall">
                <h1>Contattaci</h1>
                <h2>Siamo qui per consigliarti e offrirti supporto</h2>
            </div>
        </div>
        <h1 id="grazieContattaci" align="center">Grazie per averci contattato! I nostri operatori ti risponderanno al più presto!</h1>
        <div id="contattaci">
            <form id="formContattaci" onsubmit="grazie()">
                <label for="nomeCo">Nome</label>
                <input type="text" id="nomeCo" required><br>
                <label for="cognomeCo">Cognome</label>
                <input type="text" id="cognomeCo" required><br>
                <label for="emailCo">Email</label>
                <input type="email" id="emailCo" required><br>
                <label for="messaggioCo">Messaggio</label>
                <textarea id="messaggioCo" rows="6" cols="50" required ></textarea><br>
                <input type="submit" value="Invia">
            </form>
        </div>
    </div>
    <%@include file="WEB-INF/footer.html"%>
</div>
<script src="script/contattaciForm.js"></script>
</body>
</html>
