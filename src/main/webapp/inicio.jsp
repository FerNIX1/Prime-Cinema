<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <form>
                    <p>Elegir Sucursal Actual</p>
                    <select>
                        <option value="Sucursal 1">Sucursal 1</option>
                        <option value="Sucursal 2">Sucursal 2</option>
                        <option value="Sucursal 3">Sucursal 2</option>
                    </select>
                    <input type="submit" class="btn btn-primary btn_cinema" value="Seleccionar"/>
                </form>
            </div>
            <div class="col-md-8 form_box">

                <h2>Cartelera de sucursal</h2>
            </div>
        </div>

        <!--Busquedas-->
        <div class="row justify-content-center">
            <div class="col-md-6 form_box">
                <h2> Bucar Peliculas por Sucursal</h2>
            </div>
            <div class="col-md-6 form_box">
                <h2>Buscar salas por Pelicula</h2>
            </div>
        </div>
    </div>

</body>
</html>
