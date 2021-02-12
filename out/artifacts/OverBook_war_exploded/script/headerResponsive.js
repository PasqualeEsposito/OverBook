$(document).ready(function(){
    //viene premuto il pulsante della ricerca
    $("#cercaResponsive").click(function(){
        //controlla se il toggle del menu è attivo se si ripristina il css di default del foglio di stile
        if($("header ul div li").css("display")=='block'){
            $(".corpo").css("margin-top", "0");
            $("header ul div").css("display", "inline-block");
            $("header ul div li").css("display", "none");
            $("header ul div li a").css("padding", "14px 16px");
            $("header").css("height", "40%");
            $("#cercaResponsive").css("top", "25%");
            $("#menuResponsive").css("top", "25%");
        }

        //controlla se il pulsante cerca non è stato ancora premuto (form di ricerca nascosto)
        if($("header ul li#liForm").css("display")=='none'){ //modifica alcune regole css per la visualizzazione e per l'animazione
            $("header").animate({height: "67%"});
            $("#cercaResponsive").animate({"top":"6%"});
            $("#menuResponsive").animate({"top":"6%"});
            $("header ul li#liForm").css("display", "block");
            $("header ul li#liForm").animate({"opacity":"1"}, 500);
        }else{ //se il form di ricerca è visibile, rispristina i valori del css
            $("header ul li#liForm").animate({"opacity":"0"}, 500);
            $("header ul li#liForm").css("display", "none");
            $("header").animate({height: "40%"});
            $("#cercaResponsive").animate({"top":"25%"});
            $("#menuResponsive").animate({"top":"25%"});
        }
    })


    //viene premuto il pulsante del menu
    $("#menuResponsive").click(function(){
        //controlla se il toggle della ricerca è attivo se si ripristina il css di default del foglio di stile
        if($("header ul li#liForm").css("display")=='block'){
            $("header ul li#liForm").css("display", "none");
            $("header ul li#liForm").css("opacity", "0");
            $("header").css("height", "40%");
            $("#cercaResponsive").css("top", "25%");
            $("#menuResponsive").css("top", "25%");
        }
        //controlla se si è loggati e quindi se c'è il dropdown
        if($("header ul div li:first-child a").hasClass("dropbtn")){
            //se c'è il dropdown
            if($("header ul div").css("display")=='none'){ //modifica alcune regole css per la visualizzazione e per l'animazione
                $(".corpo").animate({"margin-top":"200px"});
                $("#cercaResponsive").animate({"top":"6%"});
                $("#menuResponsive").animate({"top":"6%"});
                $("header ul div").css("display", "block");
                $("header ul div").css("margin-top", "18px");
                $("header ul div li div").animate({"margin-top":"0"});
                $("header ul div li").css("display", "block");
                $("header ul div li:first-child").removeClass();
                $("header ul div li:first-child div").removeClass("dropdown-content");
                if($("header div li div a:nth-child(2)").text()=="Pannello di amministrazione")
                    $("header").animate({height: "191%"});
                else
                    $("header").animate({height: "160%"});
            }else{ //se il menu con il dropdown è visibile, rispristina i valori del css standard
                $(".corpo").animate({"margin-top":"0"});
                $("header ul div").css("display", "none");
                $("header ul div").css("margin-top", "0");
                $("header ul div li div").animate({"margin-top":"18px"});
                $("header ul div li").css("display", "none");
                $("header ul div li:first-child").addClass("dropdown");
                $("header ul div li:first-child div").addClass("dropdown-content");
                $("header").animate({height: "40%"});
                $("#cercaResponsive").animate({"top":"25%"});
                $("#menuResponsive").animate({"top":"25%"});
            }
        }else { //senza dropdown quindi non si è loggati e abbiamo solo i bottoni login/registrazione e carrello
            //controlla se il pulsante menu non è stato ancora premuto (pulsanti menu nascosti)
            if ($("header ul div li").css("display") == 'none') { //modifica alcune regole css per la visualizzazione e l'animazione
                $(".corpo").animate({"margin-top":"50px"});
                $("#cercaResponsive").animate({"top":"6%"});
                $("#menuResponsive").animate({"top":"6%"});
                $("header ul div").css("display", "block");
                $("header ul div li").css("display", "block");
                $("header ul div").animate({"margin-top":"10px"});
                $("header").animate({height: "94%"});
            } else { //se il menu è visibile, rispristina i valori del css standard
                $(".corpo").animate({"margin-top":"0"});
                $("header ul div").css("display", "inline-block");
                $("header ul div li").css("display", "none");
                $("header").animate({height: "40%"});
                $("#cercaResponsive").animate({"top":"25%"});
                $("#menuResponsive").animate({"top":"25%"});
            }
        }
    })
})

