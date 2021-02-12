
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <jsp:include page="WEB-INF/header.jsp">
        <jsp:param name="posizione" value="chisiamo"/>
        <jsp:param name="titolo" value="Chi Siamo"/>
    </jsp:include>
    <div class="corpo">
        <h1 id="breve">OverBook in breve</h1>

        <div id="contenitorechisiamo">
            <div id="primoblocco">
                <p id="primochisiamo">Il 1 Maggio 2019 a mezzanotte nasce a Fisciano Overbook.it.
                    Pochi minuti dopo un cliente italiano da Fremont in California compra
                    una copia de "Il piccolo principe" di Antoine de Saint-Exupéry.
                    È il primo libro venduto online da Overbook. Inizia così la nostra storia.
                </p>
                <img id="primaimmagine" alt="prima immagine" src="./imagePage/NewEcommerce.jpg">
            </div>
            <div id="secondoblocco">
                <p id="secondochisiamo">
                    1 milione di clienti registrati, 500.000 prodotti a catalogo,
                    quasi 100.000 pacchi spediti nell’ultimo mese e una movimentazione a magazzino di circa 1.000 prodotti al giorno.
                    Nel nostro primo mese di vita abbiamo consegnato libri scritti da oltre 500 autori e videogiochi di tutte le piattaforme esisenti.
                    Ci proponiamo a chiunque voglia informarsi, aggiornarsi ed acquistare un prodotto culturale o per il tempo libero: libri in lingua italiana/inglese e videogiochi.
                </p>
                <img id="secondaimmagine" alt="seconda immagine" src="./imagePage/Warehouse.jpg">
            </div>
            <div id="terzoblocco">
                <p id="terzochisiamo">
                    Il costante miglioramento della vostra esperienza di acquisto e la conquista della vostra fiducia
                    sono ciò per cui lavoriamo ogni giorno, con rinnovata passione.
                    Un obiettivo chiaro che si affianca ai servizi che da sempre ci contraddistinguono: completezza del catalogo,
                    consegne rapide e puntuali e un attento customer care. Tutto questo e molto altro è Overbook.it!
                </p>
                <img id="terzaimmagine" alt="terza immagine" src="./imagePage/customer.png">
            </div>
        </div>
    </div>
    <%@include file="WEB-INF/footer.html"%>
</div>
</body>
</html>