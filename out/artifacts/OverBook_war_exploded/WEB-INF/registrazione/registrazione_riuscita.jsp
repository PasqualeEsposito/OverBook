<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <jsp:include page="./../header.jsp">
        <jsp:param name="titolo" value="Successo"/>
    </jsp:include>
    <div class="corpo">
        <c:choose>
            <c:when test="${amministratore}">
                <h1 class="regBenvenuto">${client.nome} ${client.cognome} è stato registrato come nuovo amministratore</h1>
                <a class="return" href="./index.jsp">Torna alla homepage</a>
            </c:when>
            <c:otherwise>
                <h1 class="regBenvenuto">Benvenuto ${client.nome}! La registrazione è avvenuta con successo</h1>
                <a class="return" href="./index.jsp">Torna alla homepage</a>
            </c:otherwise>
        </c:choose>
    </div>
    <%@include file="./../footer.html"%>
</div>
</body>
</html>

