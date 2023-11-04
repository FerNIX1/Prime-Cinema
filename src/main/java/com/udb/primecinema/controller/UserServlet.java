package com.udb.primecinema.controller;

import com.udb.primecinema.beans.UsuarioBeans;
import com.udb.primecinema.model.UsuariosModel;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "UserServlet", urlPatterns = {"/usuario.do"})
public class UserServlet extends HttpServlet {
    UsuariosModel modelo = new UsuariosModel();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if (request.getParameter("op") == null) {
            request.getRequestDispatcher("login.jsp").forward(request,response);
            return;
        }
        try (PrintWriter out = response.getWriter()) {
            String operacion = request.getParameter("op");
            switch (operacion) {
                case "logueo":
                    logueo(request,response);
                    break;
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    private void logueo(HttpServletRequest request, HttpServletResponse response) {
        int IdUsuario = Integer.parseInt(request.getParameter("IdUsuario"));
        int DUI = Integer.parseInt(request.getParameter("DUI"));
        try{
            UsuarioBeans usuario = modelo.Logearse(IdUsuario, DUI);
            if (usuario != null) {
                // El inicio de sesión fue exitoso, puedes redirigir a una página de inicio
                // o a cualquier otra página a la que el usuario debería ser redirigido después del inicio de sesión.
                request.getRequestDispatcher("inicio.jsp").forward(request, response);
            } else {
                // El inicio de sesión falló, redirigir a una página de inicio de sesión con un mensaje de error.
                request.setAttribute("errorMensaje", "Credenciales inválidas. Por favor, inténtelo de nuevo.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }catch (SQLException | ServletException | IOException exception ){
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE,null,exception);
        }

    }
}
