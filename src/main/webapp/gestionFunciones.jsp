<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>PRIMECINEMA - Gestión de Funciones</title>
    <script src="js/bootstrap.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main_styles.css">
</head>
<body>
<%@include file="nav_bar.html"%>

<div class="container mt-4">

    <!-- FUNCIONES FORM -->
    <div class="row justify-content-center">
    <div class="col-md-8 form_box">
        <h2>Agregar Funciones</h2>
        <form action="#" method="post" id="formFuncion">
            <div class="row">
                <div class="form-group col-md-6">
                    <label for="id">Identificador de Funcion:</label>
                    <input type="text" class="form-control" id="id" name="id" required>
                </div>
                <div class="form-group col-md-6">
                    <label for="sala">Sala:</label>
                    <select id="sala" name="sala" class="form-select">
                        <option></option>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-6">
                    <label for="pelicula">Pelicula:</label>
                    <select id="pelicula" name="pelicula" class="form-select">
                        <option></option>
                    </select>
                </div>
                <div class="form-group col-md-6">
                    <label for="duracion">Duracion:</label>
                    <input type="text" class="form-control" id="duracion" name="duracion" required>
                </div>
            </div>
            <br>
            <input type="submit" class="btn btn-primary btn_cinema" id="btn-Agregar-Funcion" value="Guardar"/>
        </form>
    </div>
</div>

    <!-- FUNCIONES TABLE -->
    <<div class="row justify-content-center">
    <div class="col-md-8 form_box">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Sala</th>
                <th>Pelicula</th>
                <th>Duracion</th>
                <th>Editar</th>
                <th>Eliminar</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="funcion" items="${requestScope.listaFunciones}">
                <tr>
                    <td>${funcion.ID_funcion}</td>
                    <td>${funcion.ID_Sala}</td>
                    <td>${funcion.ID_pelicula}</td>
                    <td>${funcion.Duracion}</td>
                    <td>
                        <button class="btn btn-danger" onclick="alertaBorrar(${funcion.ID_funcion})">Eliminar</button>
                    </td>
                    <td> <button class="btn btn-info" onclick="Table_TO_Form('${funcion.ID_funcion}','${funcion.ID_Sala}','${funcion.ID_pelicula}','${funcion.Duracion}')">Modificar</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</div>
<script>
    function alertaBorrar(id)
    {
        var opcion = confirm("Esta seguro de eliminar esta sucursal?");
        if (opcion == true) {
            location.href ="../sucursal.do?operacion=delete&id="+id;
        }
    }
    function Table_TO_Form(id,sala,pelicula,duracion)
    {
        document.getElementById("id").value=id;
        document.getElementById("sala").value=sala;
        document.getElementById("pelicula").value=pelicula;
        document.getElementById("duracion").value=duracion;

        hijo = document.getElementById("id");
        padre = hijo.parentNode;
        padre.removeChild(hijo);
        var btnGuardar = document.getElementById("btn-Agregar-Sala");
        btnGuardar.setAttribute("onclick", GuardarActualizacion(id));
    }
    function ConfirmarActualizacion(id){
        var form = document.getElementById("formSalas");
        form.setAttribute("action", "../salas.do?operacion=modificar&id="+id);
    }
</script>
</body>
</html>
