package com.udb.primecinema.controller;

import com.udb.primecinema.beans.SalaBeans;
import com.udb.primecinema.beans.SucursalBeans;
import com.udb.primecinema.model.ComprasModel;
import com.udb.primecinema.model.SalaModel;
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

@WebServlet(name = "SalasServlet", value = "/Salas.do")
public class SalasServlet extends HttpServlet {
    SalaModel modelo = new SalaModel();
    SucursalModel modelo2 = new SucursalModel();
    ComprasModel modelCompras = new ComprasModel();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if (request.getParameter("op") == null) {
            listartodo(request,response);
            return;
        }
        try (PrintWriter out = response.getWriter()) {
            String operacion = request.getParameter("op");
            switch (operacion) {
                case "listartodo":
                    listartodo(request,response);
                case "Agregar":
                    Agregar(request,response);
                case "editar":
                     editar(request,response);
                case "actualizar":
                    actualizar(request,response);
                case "eliminar":
                    eliminar(request,response);
                case "Vaciar":
                    vaciar(request,response);

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
    private void listartodo(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<SalaBeans> listaSalas = modelo.ListarTodo();
            request.setAttribute("listaSalas", listaSalas);
            List<SucursalBeans> listaSucursales = modelo2.ListarSucursales();
            request.setAttribute("sucursales", listaSucursales);
            RequestDispatcher rd = request.getRequestDispatcher("gestionSalas.jsp");
            rd.forward(request, response);
        } catch (IOException | SQLException | ServletException ex) {
            Logger.getLogger(SalasServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void Agregar(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        int id_sucursal = Integer.parseInt(request.getParameter("gerente"));
        try {
            SalaBeans nuevaSucursal= new SalaBeans();
            nuevaSucursal.setID_sala(id);
            nuevaSucursal.setNombre(nombre);
            nuevaSucursal.setID_sucursal(id_sucursal);
            modelo.agregarSala(nuevaSucursal);
            response.sendRedirect(request.getContextPath() + "/Salas.do?op=listartodo");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void editar(HttpServletRequest request, HttpServletResponse response) {
        String idStr = request.getParameter("id");
        if (idStr != null) {
            try {
                int userId = Integer.parseInt(idStr);
                SalaBeans salas = modelo.obtenerSalaPorId(userId);
                request.setAttribute("sala", salas);
                request.getRequestDispatcher("editarSala.jsp").forward(request, response);
            } catch (NumberFormatException | SQLException | ServletException | IOException e) {
                Logger.getLogger(SalasServlet.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    private void actualizar(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        int id_sucursal = Integer.parseInt(request.getParameter("id_sucursal"));
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("ID_sucursal: " + id_sucursal);
        // Crear un objeto UsuariosBean con los nuevos datos
        SalaBeans salas = new SalaBeans();
        salas.setID_sala(id);
        salas.setNombre(nombre);
        salas.setID_sucursal(id_sucursal);
        try {
            // Actualizar el usuario en la base de datos
            modelo.actualizarSala(salas);
            // Redirigir a la página de listado después de la actualización
            response.sendRedirect(request.getContextPath() + "/Salas.do?op=listartodo");
        } catch (SQLException | IOException e) {
            Logger.getLogger(SalasServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    private void eliminar(HttpServletRequest request, HttpServletResponse response) {
        // Obtener el ID del usuario a eliminar
        int idSala = Integer.parseInt(request.getParameter("id"));

        try {
            // Eliminar el usuario de la base de datos
            modelo.eliminarSala(idSala);

            // Redirigir a la página de listado después de la eliminación
            response.sendRedirect(request.getContextPath() + "/Salas.do?op=listartodo");
        } catch (SQLException | IOException e) {
            Logger.getLogger(SalasServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    private void vaciar(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            modelCompras.VaciarFuncion(id);
            response.sendRedirect(request.getContextPath() + "/Salas.do?op=listartodo");
        } catch (SQLException | IOException e){
            Logger.getLogger(SalasServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
