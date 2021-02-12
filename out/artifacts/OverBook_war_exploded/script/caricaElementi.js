
$(document).ready(function(){
    var size_tab = $("table.tabellaCategoria tr").length;
    $(" table.tabellaCategoria tr").hide();
    x=6;
    $('table.tabellaCategoria tr').slice(0,x).show();

    if(size_tab<5){
        $("span.bottoneCategoria").hide();
        $("span.top").hide();
    }

    $('span.bottoneCategoria').click(function () {
        // condizione ? valore1 : valore2
        // se la condizione è vera in X ci mette x+5 altrimenti ci mette size_tab
        x = (x+5 <= size_tab) ? x+5 : size_tab;
        $('table.tabellaCategoria tr').slice(0,x).show(1000);
        if(x===size_tab){
            $("span.bottoneCategoria").hide();
        }
    });



    $(window).scroll(function(){
        if($(this).scrollTop() < 650){
            $("span.top").hide();
        }else{
            $("span.top").show(500);
        }
    })

    $("span.top").click(function () {
        $("html,body").animate({scrollTop:0},500);
    })


});

