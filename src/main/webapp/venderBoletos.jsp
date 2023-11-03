<%--
  Created by IntelliJ IDEA.
  User: MINEDUCYT
  Date: 3/11/2023
  Time: 14:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>PRIMECINEMA - Nueva venta</title>
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
                <h2>Paso 1. Elegir Función</h2>

                    <!--Elegir Sucursal -->
                     <form action="#" method="post" id="elegirSucursal">
                    <div class="row justify-content-center">
                        <label for="sucursal">Elija Sucursal:</label>
                            <div class="form-group col-md-6">
                                    <select id="sucursal" name="sucursal" class="form-select">
                                        <option value="1"> Sucursal 1</option>
                                    </select>
                            </div>
                            <div class="form-group col-md-2">
                                <input type="submit" class="btn btn-primary btn_cinema" id="btn_elegirSucursal" value="Seleccionar"/>
                            </div>
                    </div>
                     </form>

                    <!--Elegir Funcion-->
                    <div class="row justify-content-center">
                        <div class="col-md-8">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Sala</th>
                                    <th>Pelicula</th>
                                    <th>Duracion</th>
                                    <th>...</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="funcion" items="${requestScope.listaFunciones}">
                                    <tr>
                                        <td>${funcion.ID_funcion}</td>
                                        <td>${funcion.ID_Sala}</td>
                                        <td>${funcion.ID_pelicula}</td>
                                        <td>${funcion.Duracion}</td>
                                        <td>
                                            <button class="btn btn-danger" onclick="sendFuncion(${funcion.ID_funcion})">Elegir</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
            </div>
        </div>

        <!-- Elegir Butaca-->
        <div class="row justify-content-center">
            <div class="col-md-8 form_box">
                <h2>Paso 2. Elegir Butaca</h2>
            </div>
        </div>

        <!--Datos pago-->
        <div class="row justify-content-center">
            <div class="col-md-8 form_box">
                <h2>Paso 3. Némero de boletos y Métodos de pago</h2>
                <form action="#" method="post" id="formBoleto">
                    <input type="hidden" id="funcion" name="funcion">
                    <input type="hidden" id="butaca" name="butaca">
                    <div class="row justify-content-center">
                        <div class="col-md-6">
                            <label for="estandar">Boletos estandár:</label>
                            <input type="number" id="estandar" name="estandar">
                        </div>
                        <div class="col-md-6">
                            <label for="terceraEdad"> Boletos de tercera edad:</label>
                            <input type="number" id="terceraEdad" name="terceraEdad">
                        </div>
                    </div>

                    <div class="row justify-content-center">
                        <div class="col-md-6">
                            <label for="metodo">Método de pago:</label>
                            <select id="metodo" class="form-select">
                                <option value="1">Efectivo</option>
                                <option value="2">Tajeta</option>
                            </select>
                        </div>
                    </div>
                    <br>
                    <input type="submit" class="btn btn-primary btn_cinema" value="Comprar"/>
                </form>
            </div>
        </div>
    </div>
<script>
    function sendFuncion(id){
        document.getElementById("funcion").value = id;
    }
</script>
</body>
</html>
