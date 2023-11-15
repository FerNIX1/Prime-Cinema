<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reportes de ventas</title>
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
            <h2>Reporte de ventas por sucursal</h2>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Sucursal</th>
                    <th>Entradas Vendidas</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="sucursal" items="${SucursalReporte}">
                    <tr>
                        <td>${sucursal.getNombreSucursal()}</td>
                        <td>${sucursal.getEntradas()}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <div class="row justify-content-center">
        <div class="col-md-6 form_box">
            <h2>Reporte de ingresos por sala</h2>
            <table class="table">
                <thead>
                <tr>
                    <th>ID Sala</th>
                    <th>Nombre Sala</th>
                    <th>Sucursal</th>
                    <th>Ingresos Totales</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="sala" items="${SalaReporte}">
                    <tr>
                        <td>${sala.getID_sala()}</td>
                        <td>${sala.getNombre_Sala()}</td>
                        <td>${sala.getNombre_Sucursal()}</td>
                        <td><p>$ ${sala.getIngreso()} </p></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>
