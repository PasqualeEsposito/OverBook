<%--
  Created by IntelliJ IDEA.
  User: Antonio
  Date: 08/05/2020
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../header.jsp">
    <jsp:param name="titolo" value="Registrazione Fallita"/>
</jsp:include>
    <div class="corpo">
        <c:choose>
            <c:when test="${check}">
                <h1>Qualcuno con questa email: ${client.email} è già registrato</h1>
            </c:when>
            <c:otherwise>
                <h1>Errore nella registrazione. Riprova</h1>
            </c:otherwise>
        </c:choose>
        <a class="return" href="./registrazione.jsp">Torna indietro</a>
    </div>
    <%@include file="./../footer.html"%>
</div>
</body>
</html>
