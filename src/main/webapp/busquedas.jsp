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
<div class="container-fluid mt-4">
    <div class="row justify-content-center">
        <div class="col-md-6 form_box">
            <h2> Buscar Peliculas por Sucursal</h2>
            <br>
            <form action="${pageContext.request.contextPath}/busquedas.do?op=BuscarSucursal" method="post" id="elegirSucursal">
                <div class="row justify-content-center">
                    <div class="form-group col-md-6">
                        <select id="sucursal" name="sucursal" class="form-select">
                            <c:forEach var="sucursal" items="${sucursales}">
                                <p>${sucursal.nombre}</p>
                                <option value="${sucursal.ID_sucursal}">${sucursal.nombre}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group col-md-2">
                        <input type="submit" class="btn btn-primary btn_cinema" id="btn_elegirSucursal" value="Seleccionar Sucursal" />
                    </div>
                </div>
            </form>

            <table class="table">
                <thead>
                <tr>
                    <th>ID Sala</th>
                    <th>Nombre Sala</th>
                    <th>Pelicula</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="fila" items="${PeliculasPorSucursal}">
                    <tr>
                        <td>${fila.getId_Sala()}</td>
                        <td>${fila.getNombre_Sala()}</td>
                        <td>${fila.getNombre_Pelicula()}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col-md-6 form_box">
            <h2>Buscar salas por Pelicula</h2>
            <br>
            <form action="${pageContext.request.contextPath}/busquedas.do?op=BuscarPelicula" method="post" id="elegirPelicula">
                <div class="row justify-content-center">
                    <div class="form-group col-md-6">
                        <select id="pelicula" name="pelicula" class="form-select">
                            <c:forEach var="pelicula" items="${peliculas}">
                                <p>${pelicula.getNombre()}</p>
                                <option value="${pelicula.getID_pelicula()}">${pelicula.getNombre()}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group col-md-2">
                        <input type="submit" class="btn btn-primary btn_cinema" value="Seleccionar Pelicula" />
                    </div>
                </div>
            </form>

            <table class="table">
                <thead>
                <tr>
                    <th>Sucursal</th>
                    <th>ID Sala</th>
                    <th>Nombre Sala</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="fila" items="${SalasPorPelicula}">
                    <tr>
                        <td>${fila.getNombre_Sucursal()}</td>
                        <td>${fila.getId_Sala()}</td>
                        <td>${fila.getNombre_Sala()}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>
    </div>
</div>
</body>
</html>