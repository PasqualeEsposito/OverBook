$("document").ready(function(){
    $(".alert").hide();

    $("#formAccesso").submit(function(e){
        e.preventDefault();
        var email= $('#emailL').val();
        var password= $('#passwordL').val();
        $.ajax({
            type:"POST",
            url: "login",
            data:  {"email":email, "password":password},
            dataType: "text",
            success: function(data){
                if(data.includes("ok")){
                    $(".alert").hide();
                    location.href='index.jsp';
                }else{
                    $(".alert").show();
                }
            },
            error: function () {
                alert("Errore nella login");
            }
        });

    })
})
