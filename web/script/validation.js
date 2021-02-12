
//funzione che valida al submit del form di registrazione la correttezza dei dati
function validateFormR() {
    var nome = document.forms["registrazione"]["nome"].value;
    var cognome = document.forms["registrazione"]["cognome"].value;
    var ddn = document.forms["registrazione"]["data"].value;
    var email = document.forms["registrazione"]["email"].value;
    var emailPattern=new RegExp("[A-Za-z.]+[0-9]*@[A-Za-z.]+\\.[A-Za-z]+");
    var password = document.forms["registrazione"]["password"].value;
    var passwordPattern=new RegExp("^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){8,16}$");
    var regione= document.forms["registrazione"]["regione"].value;
    var provincia= document.forms["registrazione"]["provincia"].value;
    var comune= document.forms["registrazione"]["comune"].value;

    //controllo se il cf è vuoto oppure se non corrisponde al pattern
    if(nome==""){
        alert("Il Nome non può essere vuoto");
        return false;
    }
    if(cognome==""){
        alert("Il cognome non può essere vuoto");
        return false;
    }
    if(ddn==""){
        alert("La data di nascita non può essere vuota");
        return false;
    }
    if(email=="" || !emailPattern.test(email)){
        alert("L'email deve rispettare il formato richiesto (es. prova@email.it)");
        return false;
    }
    if(password=="" || !passwordPattern.test(password)){
        alert("La password deve contenere minimo 8 massimo 16 caratteri, deve contenere almeno un numero e un carattere speciale");
        return false;
    }

    if(regione==""){
        alert("Inserire una regione");
        return false;
    }

    if(provincia==""){
        alert("Inserire una provincia");
        return false;
    }

    if(comune==""){
        alert("Inserire un comune");
        return false;
    }
}

//funzione che valida al submit del form di login la correttezza dei dati
function validateFormL(){
    var email = document.forms["login"]["email"].value;
    var emailPattern=new RegExp("[A-Za-z.]+[0-9]*@[A-Za-z.]+");
    var password = document.forms["login"]["password"].value;

    if(email=="" || !emailPattern.test(email)){
        alert("L'email deve rispettare il formato richiesto (es. prova@email.it)");
        return false;
    }
    if(password==""){
        alert("Inserire la password");
        return false;
    }
}

//mostra password
function viewpass() {
    var x = document.getElementById("passwordL");
    if (x.type === "password") {
        x.type = "text";
    } else {
        x.type = "password";
    }
}