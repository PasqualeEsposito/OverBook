//quando la larghezza della finestra è più piccola di 1147 visualizza la galleria al posto della tabella
window.onresize=function(){
    if(window.innerWidth<=1147){
        document.getElementById("galleria").style.display="grid";
        document.getElementById("adminProdotti").getElementsByTagName("table")[0].style.display="none";
    }else{
        document.getElementById("galleria").style.display="none";
        document.getElementById("adminProdotti").getElementsByTagName("table")[0].style.display="block";
    }
}
//quando la larghezza della finestra è più piccola di 1147 visualizza la galleria al posto della tabella
if(window.innerWidth<=1147){
    document.getElementById("galleria").style.display="grid";
    document.getElementById("adminProdotti").getElementsByTagName("table")[0].style.display="none";
}else{
    document.getElementById("galleria").style.display="none";
    document.getElementById("adminProdotti").getElementsByTagName("table")[0].style.display="block";
}


//modal per visualizzare le informazioni dei vari prodotti quando si è in responsive

imgProdotti=document.getElementById("galleria").getElementsByClassName("modalInformazioni");
var i=0;
while(i<imgProdotti.length){
    // prende il modal iesimo
    modalInformazioni = document.getElementsByClassName("infoProdottoResponsive")[i];

    // prende lo span del modal
    var span = modalInformazioni.getElementsByClassName("close")[0];


    // quando l'utente clicca sulla span (x) il modal si chiude
    span.onclick = function() {
        this.parentNode.parentNode.parentNode.style.display = "none";
    }

    imgProdotti[i].onclick=function(){
        var j=this.getAttributeNode("value").value;
        var modalInformazioni=document.getElementsByClassName("infoProdottoResponsive")[j-1];
        modalInformazioni.style.display = "block";
    }
    i++;
}

//setta i bottoni modifica responsive per visualizzare i modal per modifica del prodotto

bottoniMod=document.getElementsByClassName("modificaResponsive");
var i=0;
while(i<bottoniMod.length){
    // prende il modal iesimo
    modalModifica = document.getElementsByClassName("modalModifica")[i];

    //prende lo span del modal
    var span = modalModifica.getElementsByClassName("close")[0];


    // quando l'utente clicca sulla span (x) il modal si chiude
    span.onclick = function() {
        this.parentNode.parentNode.parentNode.style.display = "none";
    }

    bottoniMod[i].onclick=function(){
        var j=this.getAttributeNode("value").value;
        var modalModifica=document.getElementsByClassName("modalModifica")[j-1];
        modalModifica.style.display = "block";
    }
    i++;
}

