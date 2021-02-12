//xml delle regioni e delle province
var xmlRegioni, xmlProvince;
//fa una chiamata al server con ajax per recuperare il file xml delle regioni
//e memorizza il file xml nella variabile globale per altri utilizzi
function caricaRegioni(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var regioni = this.responseXML;
            //per ogni regione nel file xml crea un tag option con il nome della regione da appendere alla select nel form
            for (var i = 0; i < 20; i++) {
                var nome = regioni.getElementsByTagName("regione")[i].getElementsByTagName("campo")[1].childNodes[0].nodeValue;
                var option = document.createElement("option");
                var valueAttr=document.createAttribute("value");
                valueAttr.value=nome;
                option.setAttributeNode(valueAttr);
                option.innerHTML = nome;
                document.getElementById("regione").append(option);
                //memorizzazzione xml regioni per utilizzi successivi
                xmlRegioni=regioni;
            }
        }
    }
    xhttp.open("GET", "xml/regioni.xml", true);
    xhttp.send();
}
//dato il nome di una regione recupera il suo id nell'xml delle regioni per poter essere usato nella ricerca delle province
function getIdByNomeRegione(xml, nome){
    var regioni=xml.getElementsByTagName("regione");
    for(var i=0; i<20; i++){
        if(regioni[i].getElementsByTagName("campo")[1].childNodes[0].nodeValue==nome){
            return regioni[i].getElementsByTagName("campo")[0].childNodes[0].nodeValue;
        }
    }
}

//fa una chiamata al server con ajax per recuperare il file xml delle province
//e memorizza il file xml nella variabile globale per altri utilizzi
function caricaProvince(value){
    //svuota il contenuto della select sia delle province che dei comuni
    document.getElementById("provincia").innerHTML="";
    document.getElementById("comune").innerHTML="";
    var option = document.createElement("option");
    document.getElementById("provincia").append(option);

    var xhttp= new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var province=this.responseXML;
            var id_regione=getIdByNomeRegione(xmlRegioni, value);
            console.log("Id regione: "+id_regione);
            //scorre tutte le province nell'xml delle province e seleziona solo quelle con id della regione uguale alla regione scelta
            //e mette tutti valori in degli option che verrano appesi alla select delle province
            for (var i = 0; i < 110; i++) {
                //console.log(province.getElementsByTagName("provincia")[i].getElementsByTagName("campo")[1].childNodes[0].nodeValue);
                if(province.getElementsByTagName("provincia")[i].getElementsByTagName("campo")[1].childNodes[0].nodeValue==id_regione){
                    var nome = province.getElementsByTagName("provincia")[i].getElementsByTagName("campo")[3].childNodes[0].nodeValue;
                    var option = document.createElement("option");
                    var valueAttr=document.createAttribute("value");
                    valueAttr.value=nome;
                    option.setAttributeNode(valueAttr);
                    option.innerHTML = nome;
                    document.getElementById("provincia").append(option);
                    //memorizzazzione xml regioni per utilizzi successivi
                    xmlProvince=province;
                }
            }
        }
    }
    xhttp.open("GET", "xml/province.xml", true);
    xhttp.send();
}

//dato il nome di una provincia recupera il suo id nell'xml delle province per poter essere usato nella ricerca dei comuni
function getIdByNomeProvincia(xml, nome){
    var province=xml.getElementsByTagName("provincia");
    for(var i=0; i<110; i++){
        if(province[i].getElementsByTagName("campo")[3].childNodes[0].nodeValue==nome){
            return province[i].getElementsByTagName("campo")[0].childNodes[0].nodeValue;
        }
    }
}

//fa una chiamata al server con ajax per recuperare il file xml dei comuni
function caricaComuni(value){
    //svuota la select dei comuni
    document.getElementById("comune").innerHTML="";

    var xhttp= new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var comuni=this.responseXML;
            var id_provincia=getIdByNomeProvincia(xmlProvince, value);
            console.log("Id provincia: "+id_provincia);
            //scorre tutti i comuni nell'xml dei comuni e seleziona solo quelle con id della provincia uguale alla provincia scelta
            //e mette tutti valori in degli option che verrano appesi alla select dei comuni
            for (var i = 0; i < 110010; i++) {
                //console.log(comuni.getElementsByTagName("comune")[i].getElementsByTagName("campo")[2].childNodes[0].nodeValue);
                if(comuni.getElementsByTagName("comune")[i].getElementsByTagName("campo")[2].childNodes[0].nodeValue==id_provincia){
                    var nome = comuni.getElementsByTagName("comune")[i].getElementsByTagName("campo")[3].childNodes[0].nodeValue;
                    var option = document.createElement("option");
                    var valueAttr=document.createAttribute("value");
                    valueAttr.value=nome;
                    option.setAttributeNode(valueAttr);
                    option.innerHTML = nome;
                    document.getElementById("comune").append(option);
                }
            }
        }
    }
    xhttp.open("GET", "xml/comuni.xml", true);
    xhttp.send();
}
