<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>PRIMECINEMA - Gestión de Sucursales</title>
    <script src="js/bootstrap.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main_styles.css">
</head>
<body>
<%@include file="nav_bar.html"%>

<div class="container mt-4">

    <!-- SUCURSALES FORM -->
    <<div class="row justify-content-center">
    <div class="col-md-8 form_box">
        <h2>Agregar Sucursal</h2>
        <form action="#" method="post" id="formSucursal">
            <div class="row">
                <div class="form-group col-md-6">
                    <label for="id">Número de Sucursal:</label>
                    <input type="text" class="form-control" id="id" name="id" required>
                </div>
                <div class="form-group col-md-6">
                    <label for="nombre">Nombre:</label>
                    <input type="text" class="form-control" id="nombre" name="nombre" required>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-6">
                    <label for="gerente">Gerente:</label>
                    <input type="text" class="form-control" id="gerente" name="gerente" required>
                </div>
                <div class="form-group col-md-6">
                    <label for="telefono">Telefono:</label>
                    <input type="text" class="form-control" id="telefono" name="telefono" required>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label for="direccion">Dirección:</label>
                    <input type="text" class="form-control" id="direccion" name="direccion" required>
                </div>
            </div>
            <br>
            <input type="submit" class="btn btn-primary btn_cinema" id="btn-Agregar-Sucursal" value="Guardar"/>
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
                <th>Gerente</th>
                <th>Telefono</th>
                <th>Direccion</th>
                <th>Editar</th>
                <th>Eliminar</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="sucursal" items="${requestScope.sucursales}">
                <tr>
                    <td>${sucursal.ID_sucursal}</td>
                    <td>${sucursal.nombre}</td>
                    <td>${sucursal.gerente}</td>
                    <td>${sucursal.telefono}</td>
                    <td>${sucursal.direccion}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/sucursales.do?op=eliminar&id=${sucursal.ID_sucursal}" class="btn btn-danger">Eliminar</a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/sucursales.do?op=editar&id=${sucursal.ID_sucursal}" class="btn btn-primary">Modificar</a>
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
    function Table_TO_Form(id,nombre,gerente,telefono,direccion)
    {
        document.getElementById("id").value=id;
        document.getElementById("nombre").value=nombre;
        document.getElementById("gerente").value=gerente;
        document.getElementById("telefono").value=telefono;
        document.getElementById("direccion").value=direccion;

        hijo = document.getElementById("id");
        padre = hijo.parentNode;
        padre.removeChild(hijo);
        var btnGuardar = document.getElementById("btn-Agregar-Sucursal");
        btnGuardar.setAttribute("onclick", GuardarActualizacion(id));
    }
    function ConfirmarActualizacion(id){
        var form = document.getElementById("formSucursal");
        form.setAttribute("action", "../sucursal.do?operacion=modificar&id="+id);
    }
</script>
</body>
</html>
