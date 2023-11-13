package com.udb.primecinema.controller;

import com.udb.primecinema.beans.*;
import com.udb.primecinema.model.SucursalModel;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "SucursalesServlet", value = "/sucursales.do")
public class SucursalesServlet extends HttpServlet {

    SucursalModel modelo = new SucursalModel();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if (request.getParameter("op") == null) {
            imprimir(request,response);
            return;
        }
        try (PrintWriter out = response.getWriter()) {
            String operacion = request.getParameter("op");
            switch (operacion) {
                case "Buscar":
                  Buscar(request,response);
                    break;
                case "listartodo":
                    listartodo(request,response);
                case "agregar":
                    agregar(request,response);
                case "editar":
                    editar(request,response);
                case "actualizar":
                    // Manejar la operación de actualizar
                    actualizar(request, response);
                case "eliminar":
                    // Manejar la operación de actualizar
                    eliminar(request, response);
            }
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       processRequest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
    private void imprimir(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<SucursalBeans> listaSucursales = modelo.ListarSucursales();
            System.out.println(listaSucursales);
            request.setAttribute("sucursales", listaSucursales);
            RequestDispatcher rd = request.getRequestDispatcher("inicio.jsp");
            rd.forward(request, response);
        } catch (IOException | SQLException | ServletException ex) {
            Logger.getLogger(SucursalesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void listartodo(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<SucursalBeans> listaSucursales = modelo.ListarTodo();
            System.out.println("Contenido de listaSucursales:");
            for (SucursalBeans sucursal : listaSucursales) {
                System.out.println("ID: " + sucursal.getID_sucursal() + ", Nombre: " + sucursal.getNombre() + ", Gerente: " + sucursal.getGerente() + ", Teléfono: " + sucursal.getTelefono() + ", Dirección: " + sucursal.getDireccion());
            }
            request.setAttribute("sucursales", listaSucursales);
            RequestDispatcher rd = request.getRequestDispatcher("gestionSucursales.jsp");
            rd.forward(request, response);
        } catch (IOException | SQLException | ServletException ex) {
            Logger.getLogger(SucursalesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void Buscar(HttpServletRequest request, HttpServletResponse response) {
        String id_sucursal = request.getParameter("sucursalSeleccionada");
        System.out.println(id_sucursal);
        try {
            List<CarteleraBeans> funciones = modelo.ListarCartelera(id_sucursal);
            System.out.println(funciones);
            if (funciones != null) {
                request.setAttribute("funciones", funciones);
                request.getRequestDispatcher("inicio.jsp").forward(request, response);
            } else {
                // Manejar el caso cuando no se encuentran funciones
                request.setAttribute("errorMensaje", "No se encontraron funciones para esta sucursal.");
                request.getRequestDispatcher("inicio.jsp").forward(request, response);
            }
        } catch (SQLException | ServletException | IOException exception) {
            Logger.getLogger(SucursalesServlet.class.getName()).log(Level.SEVERE, null, exception);
        }
    }
    private void agregar(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String gerente = request.getParameter("gerente");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
        try {
            SucursalBeans nuevaSucursal= new SucursalBeans();
            nuevaSucursal.setID_sucursal(id);
            nuevaSucursal.setNombre(nombre);
            nuevaSucursal.setGerente(gerente);
            nuevaSucursal.setTelefono(telefono);
            nuevaSucursal.setDireccion(direccion);
            modelo.agregarSucursal(nuevaSucursal);
            response.sendRedirect(request.getContextPath() + "/sucursales.do?op=listartodo");
        } catch (SQLException ex) {
            Logger.getLogger(SucursalesServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void editar(HttpServletRequest request, HttpServletResponse response) {
        String idStr = request.getParameter("id");
        if (idStr != null) {
            try {
                int userId = Integer.parseInt(idStr);
                SucursalBeans sucursal = modelo.obtenerSucursalPorId(userId);
                request.setAttribute("sucursal", sucursal);
                request.getRequestDispatcher("editarSucursales.jsp").forward(request, response);
            } catch (NumberFormatException | SQLException | ServletException | IOException e) {
                Logger.getLogger(SucursalesServlet.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    private void actualizar(HttpServletRequest request, HttpServletResponse response) {
        // Obtener los datos del formulario
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String gerente = request.getParameter("gerente");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");

        // Crear un objeto UsuariosBean con los nuevos datos
        SucursalBeans sucursalActualizada = new SucursalBeans();
        sucursalActualizada.setID_sucursal(id);
        sucursalActualizada.setNombre(nombre);
        sucursalActualizada.setGerente(gerente);
        sucursalActualizada.setTelefono(telefono);
        sucursalActualizada.setDireccion(direccion);

        try {
            // Actualizar el usuario en la base de datos
            modelo.actualizarSucursal(sucursalActualizada);
            // Redirigir a la página de listado después de la actualización
            response.sendRedirect(request.getContextPath() + "/sucursales.do?op=listartodo");
        } catch (SQLException | IOException e) {
            Logger.getLogger(SucursalesServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    private void eliminar(HttpServletRequest request, HttpServletResponse response) {
        int idUsuario = Integer.parseInt(request.getParameter("id"));

        try {
            // Eliminar el usuario de la base de datos
            modelo.eliminarSucursal(idUsuario);

            // Redirigir a la página de listado después de la eliminación
            response.sendRedirect(request.getContextPath() + "/sucursales.do?op=listartodo");
        } catch (SQLException | IOException e) {
            Logger.getLogger(SucursalesServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
