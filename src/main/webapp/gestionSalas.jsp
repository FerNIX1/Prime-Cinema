<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>PRIMECINEMA - Gestión de Salas</title>
    <script src="js/bootstrap.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main_styles.css">
</head>
<body>
<%@include file="nav_bar.html"%>

<div class="container mt-4">

    <!-- SALAS FORM -->
    <<div class="row justify-content-center">
    <div class="col-md-8 form_box">
        <h2>Agregar Salas</h2>
        <form action="#" method="post" id="formSalas">
            <div class="row d-flex justify-content-center">
                <div class="form-group col-md-6">
                    <label for="id">Número de Sala:</label>
                    <input type="text" class="form-control" id="id" name="id" required>
                </div>
            </div>
            <div class="row d-flex justify-content-center">
                <div class="form-group col-md-6">
                    <label for="nombre">Nombre:</label>
                    <input type="text" class="form-control" id="nombre" name="nombre" required>
                </div>
            </div>
            <div class="row d-flex justify-content-center">
                <div class="form-group col-md-6">
                    <label for="sucursal">Sucursal:</label>
                    <select id="sucursal" name="sucursal" class="form-select">
                        <option value="1"> Sucursal 1</option>
                    </select>
                </div>
            </div>
            <br>
            <input type="submit" class="btn btn-primary btn_cinema" id="btn-Agregar-Sala" value="Guardar"/>
        </form>
    </div>
</div>

    <!-- SUCURSALES TABLE -->
    <<div class="row justify-content-center">
    <div class="col-md-8 form_box">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Sucursal</th>
                <th>Editar</th>
                <th>Eliminar</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="sala" items="${requestScope.listaSalas}">
                <tr>
                    <td>${sala.ID_sala}</td>
                    <td>${sala.Nombre}</td>
                    <td>${sala.ID_sucursal}</td>
                    <td>
                        <button class="btn btn-danger" onclick="alertaBorrar(${sala.ID_sala})">Eliminar</button>
                    </td>
                    <td> <button class="btn btn-info" onclick="Table_TO_Form('${sala.ID_sala}','${sala.Nombre}','${sala.ID_sucursal}')">Modificar</button>
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
        var opcion = confirm("Esta seguro de eliminar esta sala?");
        if (opcion == true) {
            location.href ="../sala.do?operacion=delete&id="+id;
        }
    }
    function Table_TO_Form(id,nombre,id_sucursal)
    {
        document.getElementById("id").value=id;
        document.getElementById("nombre").value=nombre;
        document.getElementById("sucursal").value=id_sucursal;

        hijo = document.getElementById("id");
        padre = hijo.parentNode;
        padre.removeChild(hijo);
        var btnGuardar = document.getElementById("btn-Agregar-Sala");
        btnGuardar.setAttribute("onclick", GuardarActualizacion(id));
    }
    function ConfirmarActualizacion(id){
        var form = document.getElementById("formSalas");
        form.setAttribute("action", "../sala.do?operacion=modificar&id="+id);
    }
</script>
</body>
</html>
