

function iniciar() {

    var tbody = document.getElementById("tbody_butacas");
    var html = '';
    for (var i = 30; i > 0;) {
        html += "<tr>"
        for (var j = 0; j < 5; j++) {
            html += "<td><button class='btn btn-light' onClick='sendButacas(" + i-- + ")'><img class='img-fluid' height='40px' width='40px' src='img/butaca.png' alt='Img butaca'/></button></td>"
        }
        html += "</tr>"
    }
    tbody.innerHTML = html;

}
function DisableInput(){
    var Metodo = document.getElementById("metodo").value;
    if(Metodo == 1){
        document.getElementById("efectivo").disabled = false;
        document.getElementById("efectivo").required = true;
    } else if (Metodo == 2){
        document.getElementById("efectivo").disabled = true;
        document.getElementById("efectivo").required = false;
    }
}
function Verificar(){
    var funcion = document.getElementById("funcion").value;
    var butaca = document.getElementById("butaca").value;

    if (funcion == null){
        alert("Por favor seleccione una Función")
    } else if (butaca == null){
        alert("Por favor seleccione una butaca")
    }
}
function sendButacas(butaca){
    alert("Butaca N°" + butaca + " seleccionada.")
    document.getElementById("butaca").value = butaca;
}
function sendFuncion(id){
    alert("Función N°" + id + " seleccionada.")
    document.getElementById("funcion").value = id;
    iniciar();
}

if(window.addEventListener){
    window.addEventListener("load", iniciar, false);
}
else if(window.attachEvent){
    window.attachEvent("onload", iniciar);
}