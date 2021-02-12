$(document).ready(function(){
    $(".nascosti").hide();
})

function mostra(i){
    identifica="#riga"+i;
    idIcona="#plus"+i;

    if($(identifica).css("display")=="none"){
        $(identifica).show(500);
        $(idIcona).removeClass("fa fa-plus");
        $(idIcona).addClass("fa fa-minus");
    }
    else{
        $(identifica).hide(500);
        $(idIcona).removeClass("fa fa-minus");
        $(idIcona).addClass("fa fa-plus");
    }


}