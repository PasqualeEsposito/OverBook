
bottoniMod=document.getElementsByClassName("modifica");
var i=0;
while(i<bottoniMod.length){
    //console.log(i);
    // Get the modal
    modalModifica = document.getElementsByClassName("modalModifica")[i];

    // Get the <span> element that closes the modal
    var span = modalModifica.getElementsByClassName("close")[0];


    // When the user clicks on <span> (x), close the modal
    span.onclick = function() {
        console.log("test");
        this.parentNode.parentNode.parentNode.style.display = "none";
    }

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        for(var i=0; i<document.getElementsByClassName("modal").length+1; i++){
            if(event.target==document.getElementsByClassName("modal")[i])
                document.getElementsByClassName("modal")[i].style.display="none";
        }
    }

    bottoniMod[i].onclick=function(){
        var j=this.getAttributeNode("value").value;
        var modalModifica=document.getElementsByClassName("modalModifica")[j-1];
        modalModifica.style.display = "block";
    }
    i++;
}