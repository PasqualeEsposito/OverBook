$(document).ready(function(){
    var nome = document.forms["registrazione"]["nome"];
    var cognome = document.forms["registrazione"]["cognome"];
    var ddn = document.forms["registrazione"]["data"];
    var email = document.forms["registrazione"]["email"];
    var emailPattern=new RegExp("[A-Za-z.]+[0-9]*@[A-Za-z.]+\\.[A-Za-z]+");
    var password = document.forms["registrazione"]["password"];
    var passwordPattern=new RegExp("^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){8,16}$");
    var regione= document.forms["registrazione"]["regione"];
    var provincia= document.forms["registrazione"]["provincia"];
    var comune= document.forms["registrazione"]["comune"];


    $(nome).blur(function(){
        if(nome.value=="")
            $(nome).css("border-color", "red");
        else $(nome).css("border-color", "green");
    })


    $(cognome).blur(function(){
        if(cognome.value=="")
            $(cognome).css("border-color", "red");
        else $(cognome).css("border-color", "green");
    })


    $(ddn).blur(function(){
        if(ddn.value=="")
            $(ddn).css("border-color", "red");
        else $(ddn).css("border-color", "green");
    })


    $(email).blur(function(){
        if(email.value=="" || !emailPattern.test(email.value))
            $(email).css("border-color", "red");
        else $(email).css("border-color", "green");
    })

    $(password).keyup(function(){
        if(password.value=="" || !passwordPattern.test(password.value))
            $(password).css("border-color", "red");
        else $(password).css("border-color", "green");
    })

    $(regione).blur(function(){
        if(regione.value=="")
            $(regione).css("border-color", "red");
        else $(regione).css("border-color", "green");
    })

    $(provincia).blur(function(){
        if(provincia.value=="")
            $(provincia).css("border-color", "red");
        else $(provincia).css("border-color", "green");
    })
    
    $(comune).blur(function(){
        if(comune.value=="")
            $(comune).css("border-color", "red");
        else $(comune).css("border-color", "green");
    })

})