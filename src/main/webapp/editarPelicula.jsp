<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
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
  <!-- Formulario de edición de PELICULAS -->
  <div class="row justify-content-center">
    <div class="col-md-8 form_box">
      <h2>Editar Pelicula</h2>
      <form action="${pageContext.request.contextPath}/Peliculas.do?op=actualizar" method="post" id="formEditarPelicula">
        <div class="row">
          <div class="form-group col-md-6">
            <label for="id">Número de Pelicula:</label>
            <input type="text" class="form-control" id="id" name="id" value="${peli.ID_pelicula}" required>
          </div>
          <div class="form-group col-md-6">
            <label for="nombre">Nombre:</label>
            <input type="text" class="form-control" id="nombre" name="nombre" value="${peli.nombre}" required>
          </div>
          <div class="form-group col-md-6">
            <label for="nombre">ID_genero:</label>
            <input type="text" class="form-control" id="id_genero" name="id_genero" value="${peli.ID_genero}" required>
          </div>
          <div class="form-group col-md-6">
            <label for="nombre">ID_clasificacion:</label>
            <input type="text" class="form-control" id="id_clasificacion" name="id_clasificacion" value="${peli.ID_clasificacion}" required>
          </div>
          <div class="form-group col-md-6">
            <label for="nombre">ID_formato:</label>
            <input type="text" class="form-control" id="id_formato" name="id_formato" value="${peli.ID_formato}" required>
          </div>
        </div>
        <br>
        <input type="submit" class="btn btn-primary btn_cinema" id="btn-Editar-Pelicula" value="Guardar"/>
      </form>
    </div>
  </div>
</div>
</body>
</html>