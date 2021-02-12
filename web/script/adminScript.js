function registrazioneAdmin(){
    document.getElementById("utentiAdmin").style.display='';
    document.getElementById("adminProdotti").style.display= 'none';
    document.getElementById("visualizzaClienti").style.display= 'none';
}

function visualizzaProdotti(){
    document.getElementById("adminProdotti").style.display='';
    document.getElementById("utentiAdmin").style.display= 'none';
    document.getElementById("visualizzaClienti").style.display= 'none';
}

function visualizzaClienti() {
    document.getElementById("adminProdotti").style.display='none';
    document.getElementById("utentiAdmin").style.display= 'none';
    document.getElementById("visualizzaClienti").style.display= '';
}

function visualizzaCampi(value){
    if(value==1){
        document.getElementById("piattaformaForm").style.display='';
        document.getElementById("casaForm").style.display='none';
    }else if(value==2){
        document.getElementById("piattaformaForm").style.display='none';
        document.getElementById("casaForm").style.display='';
    }else {
        document.getElementById("piattaformaForm").style.display = 'none';
        document.getElementById("casaForm").style.display = 'none';
    }
}

function validationProdotto(){
    var categoria = document.forms["aggiungiProdotto"]["categoria"].value;
    var titolo = document.forms["aggiungiProdotto"]["titolo"].value;
    var descrizione = document.forms["aggiungiProdotto"]["descrizione"].value;
    var autore = document.forms["aggiungiProdotto"]["autore"].value;
    var prezzo = document.forms["aggiungiProdotto"]["prezzo"].value;
    var prezzoPattern=new RegExp("[0-9.]+");
    var disponibilita = document.forms["aggiungiProdotto"]["disponibilita"].value;
    var data_pubblicazione = document.forms["aggiungiProdotto"]["data_pubblicazione"].value;
    var piattaforma=document.forms["aggiungiProdotto"]["piattaforma"].value;
    var casa_editrice=document.forms["aggiungiProdotto"]["casa_editrice"].value;


    if(categoria==""){
        alert("Selezionare una categoria");
        $(".erroreAggiunta").show();
        return false;
    }

    if(titolo==""){
        alert("Inserire un titolo");
        $(".erroreAggiunta").show();
        return false;
    }

    if(descrizione==""){
        alert("Inserire una descrizione");
        $(".erroreAggiunta").show();
        return false;
    }

    if(autore==""){
        alert("Inserire un autore");
        $(".erroreAggiunta").show();
        return false;
    }

    if(prezzo=="" || !prezzoPattern.test(prezzo) || prezzo<=0){
        alert("Inserire un prezzo valido");
        $(".erroreAggiunta").show();
        return false;
    }

    if(disponibilita=="" || disponibilita<=0){
        alert("La dispoibilità non può essere 0");
        $(".erroreAggiunta").show();
        return false;
    }

    if(data_pubblicazione==""){
        alert("Inserire una data di pubblicazione");
        $(".erroreAggiunta").show();
        return false;
    }

    if((categoria==1 && piattaforma=="") || (categoria==2 && casa_editrice=="")){
        alert("Inserire una piattaforma o una casa editrice");
        $(".erroreAggiunta").show();
        return false;
    }
}

function validationModificaProdotto(name){
    var categoria = document.forms[name]["categoria"].value;
    var titolo = document.forms[name]["titolo"].value;
    var descrizione = document.forms[name]["descrizione"].value;
    var autore = document.forms[name]["autore"].value;
    var prezzo = document.forms[name]["prezzo"].value;
    var prezzoPattern=new RegExp("[0-9.]+");
    var disponibilita = document.forms[name]["disponibilita"].value;
    var data_pubblicazione = document.forms[name]["data_pubblicazione"].value;
    var piattaforma=document.forms[name]["piattaforma"].value;
    var casa_editrice=document.forms[name]["casa_editrice"].value;


    if(titolo==""){
        alert("Inserire un titolo");
        $("#"+name + " .erroreModifica").show();
        return false;
    }

    if(descrizione==""){
        alert("Inserire una descrizione");
        $("#"+name + " .erroreModifica").show();
        return false;
    }

    if(autore==""){
        alert("Inserire un autore");
        $("#"+name + " .erroreModifica").show();
        return false;
    }

    if(prezzo=="" || !prezzoPattern.test(prezzo) || prezzo<=0){
        alert("Inserire un prezzo valido");
        $("#"+name + " .erroreModifica").show();
        return false;
    }

    if(disponibilita=="" || disponibilita<=0){
        alert("La dispoibilità non può essere 0");
        $("#"+name + " .erroreModifica").show();
        return false;
    }

    if(data_pubblicazione==""){
        alert("Inserire una data di pubblicazione");
        $("#"+name + " .erroreModifica").show();
        return false;
    }

    if((categoria==1 && piattaforma=="") || (categoria==2 && casa_editrice=="")){
        alert("Inserire una piattaforma o una casa editrice");
        $("#"+name + " .erroreModifica").show();
        return false;
    }


}