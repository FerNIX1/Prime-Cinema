<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>PRIMECINEMA - Inicio</title>
    <script src="js/bootstrap.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main_styles.css">
</head>
<body>
<%@include file="nav_bar.html"%>

    <div class="container mt-4">
        <!--Cartelera de sucursal-->
        <div class="row justify-content-center">
            <div class="col-md-8 form_box">
                <form action="${pageContext.request.contextPath}/sucursales.do?op=Buscar" method="post">
                    <p>Elegir Sucursal Actual</p>
                    <select id="sucursalSeleccionada" name="sucursalSeleccionada">
                        <c:forEach var="sucursal" items="${sucursales}">
                            <option value="${sucursal.ID_sucursal}">${sucursal.nombre} - ID: ${sucursal.ID_sucursal}</option>
                        </c:forEach>
                    </select>
                    <input type="submit" class="btn btn-primary btn_cinema" value="Seleccionar"/>
                </form>
                <a href="${pageContext.request.contextPath}/sucursales.do?">Volver a Buscar</a>
            </div>
            <div class="col-md-8 form_box">

                <h2>Cartelera de sucursal:${funcion.nombreSucursal}</h2>
                <table class="table">
                    <thead>
                    <tr>
                        <th>Sala</th>
                        <th>Pelicula</th>
                        <th>Duración</th>
                        <th>Clasificación</th>
                        <th>Género</th>
                        <th>Formato</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="funcion" items="${funciones}">
                        <tr>
                            <td>${funcion.ID_sala}</td>
                            <td>${funcion.pelicula}</td>
                            <td>${funcion.duracion}</td>
                            <td>${funcion.clasificacion}</td>
                            <td>${funcion.genero}</td>
                            <td>${funcion.formato}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
<script>
    function ocultarSelect() {
        var select = document.getElementById('sucursalSeleccionada');
        select.style.display = 'none';
    }
</script>
</body>
</html>
