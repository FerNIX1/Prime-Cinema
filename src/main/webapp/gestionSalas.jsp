<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <form action="${pageContext.request.contextPath}/Salas.do?op=Agregar" method="post" id="formSalas">
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
                    <select id="sala" name="salaSucursal">
                        <c:forEach var="sucursal" items="${sucursales}">
                            <option value="${sucursal.ID_sucursal}">${sucursal.nombre} - ID: ${sucursal.ID_sucursal}</option>
                        </c:forEach>
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
                <th>Vaciar</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="sala" items="${requestScope.listaSalas}">
                <tr>
                    <td>${sala.ID_sala}</td>
                    <td>${sala.nombre}</td>
                    <td>${sala.ID_sucursal}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/Salas.do?op=eliminar&id=${sala.ID_sala}" class="btn btn-danger">Eliminar</a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/Salas.do?op=editar&id=${sala.ID_sala}" class="btn btn-primary">Modificar</a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/Salas.do?op=Vaciar&id=${sala.ID_sala}" class="btn btn-warning">Vaciar Butacas</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</div>
</body>
</html>
