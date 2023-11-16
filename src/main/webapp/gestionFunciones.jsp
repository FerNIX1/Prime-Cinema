<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <form action="${pageContext.request.contextPath}/Funciones.do?op=agregar" method="post" id="formFuncion">
                <div class="row">
                    <div class="form-group col-md-6">
                        <label for="id">Identificador de Funcion:</label>
                        <input type="text" class="form-control" id="id" name="id" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="sala">Sala:</label>
                            <label for="sala">Sala:</label>
                            <select id="sala" name="idSala" class="form-select">
                                <c:forEach var="sala" items="${listasalas}">
                                    <option value="${sala.ID_sala}">${sala.ID_sala}</option>
                                </c:forEach>
                            </select>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-6">
                        <label for="pelicula">Pelicula:</label>
                        <select id="pelicula" name="idPelicula" class="form-select">
                            <c:forEach var="pelicula" items="${listapeliculas}">
                                <option value="${pelicula.ID_pelicula}">${pelicula.ID_pelicula}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="duracion">Duración:</label>
                        <input type="text" class="form-control" id="duracion" name="duracion" required>
                    </div>
                </div>
                <br>
                <input type="submit" class="btn btn-primary btn_cinema" id="btn-Agregar-Funcion" value="Guardar"/>
            </form>
        </div>
    </div>

    <!-- FUNCIONES TABLE -->
    <div class="row justify-content-center">
        <div class="col-md-8 form_box">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Sala</th>
                    <th>Pelicula</th>
                    <th>Duración</th>
                    <th>Editar</th>
                    <th>Eliminar</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="funcion" items="${requestScope.listafunciones}">
                    <tr>
                        <td>${funcion.ID_funcion}</td>
                        <td>${funcion.ID_Sala}</td>
                        <td>${funcion.ID_pelicula}</td>
                        <td>${funcion.duracion}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/Funciones.do?op=editar&id=${funcion.ID_funcion}" class="btn btn-primary">Editar</a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/Funciones.do?op=eliminar&id=${funcion.ID_funcion}" class="btn btn-danger">Eliminar</a>
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