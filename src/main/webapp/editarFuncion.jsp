<%--
  Created by IntelliJ IDEA.
  User: fernando
  Date: 15/11/2023
  Time: 18:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
  <meta charset="UTF-8">
  <title>PRIMECINEMA - Gestión de peliculas</title>
  <script src="js/bootstrap.min.js"></script>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/main_styles.css">
</head>
<body>
<%@include file="nav_bar.html"%>

<div class="container mt-4">
  <div class="row justify-content-center">
    <div class="col-md-8 form_box">
      <h2>Editar Función</h2>
      <form action="${pageContext.request.contextPath}/Funciones.do?op=actualizar" method="post" id="formEditarFuncion">
        <div class="row">
          <div class="form-group col-md-6">
            <label for="id">Identificador de Funcion:</label>
            <input type="text" class="form-control" id="id" name="id" value="${funcionEditar.ID_funcion}" readonly>
          </div>
          <div class="form-group col-md-6">
            <label for="sala">Sala:</label>
            <select id="sala" name="idSala" class="form-select">
              <c:forEach var="sala" items="${listasalas}">
                <option value="${sala.ID_sala}">${sala.ID_sala}</option>
              </c:forEach>
            </select>
          </div>
        </div>
        <div class="form-group col-md-6">
          <label for="pelicula">Pelicula:</label>
          <select id="pelicula" name="idPelicula" class="form-select">
            <c:forEach var="pelicula" items="${listapeliculas}">
              <option value="${pelicula.ID_pelicula}" ${pelicula.ID_pelicula == funcionEditar.ID_pelicula ? 'selected' : ''}>${pelicula.ID_pelicula}</option>
            </c:forEach>
          </select>
        </div>
        <div class="form-group col-md-6">
          <label for="duracion">Duracion:</label>
          <input type="text" class="form-control" id="duracion" name="duracion" value="${funcionEditar.duracion}" required>
        </div>
        <br>
        <input type="submit" class="btn btn-primary btn_cinema" id="btn-Actualizar-Funcion" value="Actualizar"/>
      </form>
    </div>
  </div>
</div>
</body>
</html>
