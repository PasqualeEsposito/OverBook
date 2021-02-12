<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


        <jsp:include page="WEB-INF/header.jsp">
            <jsp:param name="pagina" value="registrazione"/>
            <jsp:param name="titolo" value="Login/Registrazione"/>
        </jsp:include>
        <div class="corpo login-registrazione">
            <div id="login">
                <h1>Accedi</h1>
                <form action="login" method="post" name="login" id="formAccesso" onsubmit="return validateFormL()">
                    <div class="alert">
                        <strong>Attenzione!</strong> Email o Password errati
                    </div>
                    <label for="emailL">E-mail</label>
                    <input id="emailL" name="email" type="email">
                    <label for="passwordL">Password</label>
                    <input id="passwordL" name="password" type="password">
                    <input type="checkbox" id="checkboxL"onclick="viewpass()">
                    <label for="checkboxL">Mostra password</label>
                    <input type="submit" value="Accedi">
                </form>
            </div>
            <div id="registrazione">
                <h1>Registrati</h1>
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
                    <input type="submit" value="Registrati">
                </form>
            </div>
        </div>
        <%@include file="./WEB-INF/footer.html"%>
</div>
<script src="script/validation.js"></script>
<script src="script/ajax-indirizzo.js"></script>
<script src="script/loginAjax.js"></script>
<script src="script/registrazioneJquery.js"></script>
</body>
</html>
