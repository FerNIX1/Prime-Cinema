<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Crear Nuevo Usuario</title>
    <script src="js/bootstrap.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main_styles.css">
</head>
<body>

<nav class="navbar navbar-expand-lg">
  <a class="navbar-brand" href="login.html">Regresar a Inicio de Sesión</a>
</nav>

<div class="container mt-4">

  <div class="row justify-content-center">
    <div class="col-md-6 justify-content-center">
      <small class="text-info">${MensajeExito}</small>
    </div>
  </div>
  <div class="row justify-content-center">
    <div class="col-md-6 justify-content-center">
      <small class="text-danger">${MensajeError}</small>
    </div>
  </div>

  <div class="row justify-content-center">
    <div class="col-md-8 form_box">
      <h2>Crear Nuevo Usuario</h2>
      <form action="${pageContext.request.contextPath}/usuario.do?op=agregarUsuario" method="post" id="formNuevoUsuario">
        <div class="form-group">
          <label for="id">Identificador de Usuario:</label>
          <input type="number" class="form-control" id="id" name="id" required>
        </div>
        <div class="form-group">
          <label for="nombre">Nombre:</label>
          <input type="text" class="form-control" id="nombre" name="nombre" required>
        </div>
        <div class="form-group">
          <label for="apellido">Apellido:</label>
          <input type="text" class="form-control" id="apellido" name="apellido" required>
        </div>
        <div class="form-group">
          <label for="dui">DUI:</label>
          <input type="text" class="form-control" id="dui" name="dui" required>
        </div>
        <div class="form-group">
          <label for="direccion">Dirección:</label>
          <input type="text" class="form-control" id="direccion" name="direccion" required>
        </div>
        <div class="form-group">
          <label for="telefono">Teléfono:</label>
          <input type="text" class="form-control" id="telefono" name="telefono" required>
        </div>
        <div class="form-group">
          <label for="email">Email:</label>
          <input type="email" class="form-control" id="email" name="email" required>
        </div>
        <br>
        <input type="submit" class="btn btn-primary btn_cinema" id="btn-Agregar-Usuario" value="Guardar"/>
      </form>
    </div>
  </div>
</div>

</body>
</html>
