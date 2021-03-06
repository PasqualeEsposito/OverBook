<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>${param.titolo}</title>
    <link rel="stylesheet" type="text/css" href="css/style2.css">
    <link rel="stylesheet" type="text/css" href="./css/stileOrdiniAdmin.css">
    <link rel="stylesheet" type="text/css" href="./css/stileLogReg.css">
    <link rel="stylesheet" type="text/css" href="./css/stileFooter.css">
    <link rel="stylesheet" type="text/css" href="./css/stileChisiamo.css">
    <link rel="stylesheet" type="text/css" href="./css/stileContattaci.css">
    <link rel="stylesheet" type="text/css" href="./css/stileCarrello.css">
    <link rel="stylesheet" type="text/css" href="./css/stileRicercaProdotto.css">
    <link rel="stylesheet" type="text/css" href="./css/stileProfilo.css">
    <link rel="stylesheet" type="text/css" href="./css/stileCategoria.css">
    <link rel="stylesheet" type="text/css" href="./css/stileProdotto.css">
    <link rel="stylesheet" type="text/css" href="./css/stileHomePage.css">
    <link rel="stylesheet" type="text/css" href="./css/stileResponsiveHeader.css">
    <link rel="stylesheet" type="text/css" href="./css/stileAdmin.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Fredoka+One&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="./script/suggerimenti.js"></script>
    <script src="./script/homePageScript.js"></script>
</head>
<body <c:if test="${param.pagina == 'registrazione' || param.pagina=='registrazioneAdmin'}"> onload="caricaRegioni();"</c:if>>
<div class="container">
    <div id="headerBarra">
        <header>
            <ul>
                <li id="menuResponsive"><a><i class="fa fa-bars" aria-hidden="true"></i></a></li>
                <h1><a href="./index.jsp">OverBook</a></h1>
                <li id="cercaResponsive"><a><i class="fa fa-search" aria-hidden="true"></i></a></li>
                <li id="liForm">
                    <form action="search" method="get" id="ricerca">
                        <select name="categoria">
                            <option value="0">Tutte le categorie</option>
                            <c:forEach items="${categorie}" var="cat">
                                <option value="${cat.idCategoria}">${cat.nome}</option>
                            </c:forEach>
                        </select>
                        <input type="text" name="ricerca" id="enter">
                        <input type="submit" value="Cerca">
                    </form>
                </li>
                <div>
                    <c:choose>
                        <c:when test="${cliente==null}">
                            <li><a class="active" href="./registrazione.jsp">Login/Registrazione</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="dropdown">
                                <a href="#" class="dropbtn active">${cliente.nome} ${cliente.cognome}</a>
                                <div class="dropdown-content">
                                    <a href="user">Profilo</a>
                                    <c:if test="${cliente.admin}">
                                        <a href="admin">Pannello di amministrazione</a>
                                    </c:if>
                                    <a href="logout">Logout</a>
                                </div>
                            </li>
                            <!--<li><a href="logout">Logout</a></li>-->
                        </c:otherwise>
                    </c:choose>
                    <li><a href="cart">Carrello</a></li>
                </div>
            </ul>
        </header>
        <div id="barra">
            <ul>
                <li><a <c:if test="${param.posizione=='home'}">class="active"</c:if> href="index.jsp">Home</a></li>
                <li><a <c:if test="${param.posizione=='Libri'}">class="active"</c:if> href="search?categoria=2&ricerca=">Libri</a></li>
                <li><a <c:if test="${param.posizione=='VideoGames'}">class="active"</c:if> href="search?categoria=1&ricerca=">Videogiochi</a></li>
                <li><a <c:if test="${param.posizione=='chisiamo'}">class="active"</c:if> href="chisiamo.jsp">Chi Siamo</a></li>
                <li><a <c:if test="${param.posizione=='contattaci'}">class="active"</c:if> href="contattaci.jsp">Contattaci</a></li>
            </ul>
        </div>
    </div>