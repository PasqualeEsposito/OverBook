var passwordPattern=new RegExp("^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){8,16}$");

function showFields() {
    var div = document.getElementById("cambiaPassword");
    if(div.style.display== "none") {
        div.style.display = "";
    } else {
        div.style.display = "none";
    }
}

function verifica() {
    var pass1 = document.forms["password"]["pass1"].value;
    var pass2 = document.forms["password"]["pass2"].value;
    var oldPass = document.forms["password"]["pass"].value;
    if(pass1 != pass2 || !passwordPattern.test(pass1)) {
        alert("Le due password non coincidono!");
        document.forms["password"].reset();
        return false;
    } else {
        if(passwordPattern.test(oldPass))
            return true;
        else {
            alert("La vecchia password è errata!");
            document.forms["password"].reset();
            return false;
        }
    }
}