function validateSconto(name){
        var percentuale = document.forms[name]["percentuale"].value;
        if(percentuale=="" || (percentuale<0 || percentuale>100)){
            alert("Inserire un valore di percentuale corretto (compreso tra 0 e 100)");
            return false;
        }
}
