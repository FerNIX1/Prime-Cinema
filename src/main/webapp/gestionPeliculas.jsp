<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

        <!-- PELICULAS FORM -->
        <<div class="row justify-content-center">
            <div class="col-md-8 form_box">
                <h2>Agregar Pelicula</h2>
                <form action="#" method="post" id="formPelicula">
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label for="id">Número de Pelicula:</label>
                            <input type="text" class="form-control" id="id" name="id" required>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="nombre">Nombre:</label>
                            <input type="text" class="form-control" id="nombre" name="nombre" required>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label for="genero">Género:</label>
                            <select id="genero" name="genero" class="form-select">
                                <option value="1">Acción</option>
                                <option value="2">Comedia</option>
                                <option value="3">Romance</option>
                                <option value="4">Animacion</option>
                                <option value="5">Ciencia-Ficción</option>
                                <option value="6">Terror</option>
                            </select>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="clasificacion">Clasificacion:</label>
                            <select id="clasificacion" name="clasificacion" class="form-select">
                                <option value="1">TP</option>
                                <option value="2">M-12</option>
                                <option value="3">M-15</option>
                                <option value="4">M-18</option>
                                <option value="5">M-21</option>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label for="formato">Formato:</label>
                            <select id="formato" name="formato" class="form-select">
                                <option value="1">Tradicional</option>
                                <option value="2">3D</option>
                            </select>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="sucursal">Sucursal:</label>
                            <select id="sucursal" name="sucursal" class="form-select">
                                <option value="1">Sucursal 1</option>
                            </select>
                        </div>
                    </div>
                    <br>
                    <input type="submit" class="btn btn-primary btn_cinema" id="btn-Agregar-Pelicula" value="Guardar"/>
                </form>
            </div>
        </div>

        <!-- PELICULAS TABLE -->
        <<div class="row justify-content-center">
            <div class="col-md-8 form_box">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Género</th>
                        <th>Clasificación</th>
                        <th>Formato</th>
                        <th>Sucursal</th>
                        <th>Editar</th>
                        <th>Eliminar</th>
                    </tr>
                    </thead>
                    <tbody>
                     <c:forEach var="pelicula" items="${requestScope.listaPeliculas}">
                        <tr>
                            <td>${pelicula.ID_pelicula}</td>
                            <td>${pelicula.Nombre}</td>
                            <td>${pelicula.ID_genero}</td>
                            <td>${pelicula.ID_clasificacion}</td>
                            <td>${pelicula.ID_formato}</td>
                            <td>${pelicula.ID_sucursal}</td>
                            <td>
                                <button class="btn btn-danger" onclick="alertaBorrar(${pelicula.ID_pelicula})">Eliminar</button>
                            </td>
                            <td> <button class="btn btn-info" onclick="Table_TO_Form('${pelicula.ID_pelicula}','${pelicula.Nombre}','${pelicula.ID_genero}','${pelicula.ID_clasificacion}','${pelicula.ID_formato}','${pelicula.ID_sucursal}')">Modificar</button>
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
            var opcion = confirm("Esta seguro de eliminar esta pelicula");
            if (opcion == true) {
                location.href ="../pelicula.do?operacion=delete&id="+id;
            }
        }
        function Table_TO_Form(id, nombre,genero,clasificacion,formato, sucursal)
        {
            document.getElementById("id").value=id;
            document.getElementById("nombre").value=nombre;
            document.getElementById("genero").value=genero;
            document.getElementById("clasificacion").value=clasificacion;
            document.getElementById("formato").value=formato;
            document.getElementById("sucursal").value=sucursal;

            hijo = document.getElementById("id");
            padre = hijo.parentNode;
            padre.removeChild(hijo);
            var btnGuardar = document.getElementById("btn-Agregar-Pelicula");
            btnGuardar.setAttribute("onclick", GuardarActualizacion(id));
        }
        function ConfirmarActualizacion(id){
            var formPelicual = document.getElementById("formPelicula");
            formPelicual.setAttribute(action, "../pelicula.do?operacion=modificar&id="+id);
        }
    </script>
</body>
</html>
