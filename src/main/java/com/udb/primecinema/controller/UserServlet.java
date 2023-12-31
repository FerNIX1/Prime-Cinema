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
            request.getRequestDispatcher("login.html").forward(request,response);
            return;
        }
        try (PrintWriter out = response.getWriter()) {
            String operacion = request.getParameter("op");
            switch (operacion) {
                case "logueo":
                    logueo(request,response);
                    break;
                case "agregarUsuario":
                    agregarUsuario(request, response);
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
        System.out.println(IdUsuario+DUI);
        try{
            UsuarioBeans usuario = modelo.Logearse(IdUsuario, DUI);
            if (usuario != null) {
                // El inicio de sesión fue exitoso, redirigir a la URL deseada (sucursales.do)
                response.sendRedirect(request.getContextPath() + "/sucursales.do");
            } else {
                // El inicio de sesión falló, redirigir a una página de inicio de sesión con un mensaje de error
                request.setAttribute("errorMensaje", "Credenciales inválidas. Por favor, inténtelo de nuevo.");
                request.getRequestDispatcher("login.html").forward(request, response);
            }
        }catch (SQLException | ServletException | IOException exception ){
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE,null,exception);
        }

    }
    private void agregarUsuario(HttpServletRequest request, HttpServletResponse response) {
        try {
            // Recupera los parámetros del formulario
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            int dui = Integer.parseInt(request.getParameter("dui"));
            String direccion = request.getParameter("direccion");
            String telefono = request.getParameter("telefono");
            String email = request.getParameter("email");

            //Verificar Datos
            boolean datosRepetidos = modelo.VerificarDatosUnicos(id,dui);

            if(datosRepetidos){
                request.setAttribute("MensajeError", "ID o DUI repetidos. Por favor intente con otros datos");
            } else{
                // Crea un nuevo objeto UsuarioBeans
                UsuarioBeans nuevoUsuario = new UsuarioBeans();
                nuevoUsuario.setID_Usuario(id);
                nuevoUsuario.setNombre(nombre);
                nuevoUsuario.setApellido(apellido);
                nuevoUsuario.setDUI(dui);
                nuevoUsuario.setDireccion(direccion);
                nuevoUsuario.setTelefono(telefono);
                nuevoUsuario.setEmail(email);

                // Llama al método en el modelo para agregar el usuario
                boolean registro_status =  modelo.AgregarUsuario(nuevoUsuario);

                if(registro_status){
                    request.setAttribute("MensajeExito", "Su registro fue exitoso, puede iniciar sesión");
                } else {
                    request.setAttribute("MensajeError", "Usuario no registrado.");
                }
            }



            // Redirige a la página deseada después de agregar el usuario
            request.getRequestDispatcher("agregarUsuario.jsp").forward(request, response);

        } catch (SQLException | IOException | NumberFormatException exception) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, exception);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }
}
