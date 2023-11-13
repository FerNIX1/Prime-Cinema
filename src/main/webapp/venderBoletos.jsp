<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <div class="container-fluid mt-4">
        <div class="row justify-content-center">
            <div class="col-md-8 form_box">
                <h2>Paso 1. Elegir Función</h2>

                    <!--Elegir Sucursal-->
                     <form action="${pageContext.request.contextPath}/compras.do?operacion=VerFunciones" method="post" id="elegirSucursal">
                    <div class="row justify-content-center">
                        <label for="sucursal">Elija Sucursal:</label>
                            <div class="form-group col-md-6">
                                    <select id="sucursal" name="sucursal" class="form-select">
                                        <c:forEach var="sucursal" items="${sucursales}">
                                            <p>${sucursal.nombre}</p>
                                            <option value="${sucursal.ID_sucursal}">${sucursal.nombre}</option>
                                        </c:forEach>
                                    </select>
                            </div>
                            <div class="form-group col-md-2">
                                <input type="submit" class="btn btn-primary btn_cinema" id="btn_elegirSucursal" value="Seleccionar" />
                            </div>
                            <a href="${pageContext.request.contextPath}/compras.do?operacion=mostrar">Cargar Sucursales</a>
                    </div>
                     </form>

                    <!--Elegir Funcion-->
                    <div class="row justify-content-center">
                        <div class="col-md-8">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th>Sala</th>
                                    <th>Pelicula</th>
                                    <th>Duración</th>
                                    <th>Clasificación</th>
                                    <th>Género</th>
                                    <th>Formato</th>
                                    <th>...</th>
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
                <table class="table table-bordered" id="buatacas_form">
                    <thead>
                    <tr>
                        <th>Columna A</th>
                        <th>Columna B</th>
                        <th>Columna C</th>
                        <th>Columna D</th>
                        <th>Columna E</th>
                    </tr>
                    </thead>
                    <tbody id="tbody_butacas" class="justify-content-center">

                    </tbody>
                </table>
                <p>Pantalla</p>
            </div>
        </div>

        <!--Datos pago-->
        <div class="row justify-content-center">
            <div class="col-md-8 form_box">
                <h2>Paso 3.Tipo de boleto y Método de pago</h2>
                <form action="${pageContext.request.contextPath}/compras.do?operacion=NuevaCompra" method="post" id="formBoleto">
                    <input type="hidden" id="funcion" name="funcion" required>
                    <input type="hidden" id="butaca" name="butaca" required>
                    <div class="row justify-content-center">
                        <div class="col-md-6">
                            <label for="usuario_id">Ingrese Identifiación de Usuario:</label>
                            <input type="number" id="usuario_id" name="usuario_id" class="form-control" placeholder="Ingrese ID" required/>
                        </div>
                        <div class="col-md-6">
                            <label for="tipo_boleto">Tipo de Boleto:</label>
                            <select id="tipo_boleto" name="tipo_boleto" class="form-select">
                                <option value="1">Estándar</option>
                                <option value="2">Adulto Mayor</option>
                            </select>
                        </div>
                    </div>

                    <div class="row justify-content-center">
                        <div class="col-md-6">
                            <label for="metodo">Método de pago:</label>
                            <select id="metodo" onchange="DisableInput()" name="metodo" class="form-select">
                                <option value="1">Efectivo</option>
                                <option value="2">Tajeta</option>
                            </select>
                        </div>
                        <div class="col-md-6">
                            <label for="efectivo">Efectivo a pagar:</label>
                            <input type="number" id="efectivo" name="efectivo" class="form-control" placeholder="Ingrese Cantidad" required>
                        </div>
                        <small class="text-danger">${errorFormulario}</small>
                        <small class="text-info">${MensajeExito}</small>
                    </div>
                    <br>

                    <input type="submit" class="btn btn-primary btn_cinema" onclick="Verificar()" value="Comprar"/>
                </form>
            </div>
        </div>

    </div>
    <script src="js/form_compras.js"></script>
</body>
</html>
