<%--
  Created by IntelliJ IDEA.
  User: fernando
  Date: 02/11/2023
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/usuario.do?op=logueo" method="post">
    <div class="mb-3">
        <label for="exampleInputEmail1" class="form-label">ID Usuario</label>
        <input type="text" name="IdUsuario" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
    </div>
    <div class="mb-3">
        <label for="exampleInputPassword1" class="form-label">Dui</label>
        <input type="text" name="DUI" class="form-control" id="exampleInputPassword1">
    </div>
    <small class="text-danger">${errorMensaje}</small>
    <button type="submit" class="btn btn-primary">Enviar</button>
</form>
</body>
</html>
