var titol=[];
$(document).ready(function(){
    $.ajax({
        url: "getTitoli",
        type: "Get",
        dataType: "json",
        success: function (title,status) {
            titol=title;
        }
    });
});
$(document).ready(function(){
    $("#enter").click(function() {
        var parole = titol;
        $("#enter").autocomplete({
            source: parole,
            minLength: 1
        });
    });
});