package com.udb.primecinema.controller;

import com.udb.primecinema.beans.FuncionBeans;
import com.udb.primecinema.beans.PeliculaBeans;
import com.udb.primecinema.beans.SalaBeans;
import com.udb.primecinema.beans.SucursalBeans;
import com.udb.primecinema.model.FuncionesModel;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "FuncionesServlet", value = "/Funciones.do")
public class FuncionesServlet extends HttpServlet {
    FuncionesModel modelo = new FuncionesModel();
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
                    break;
                case "agregar":
                    agregarFuncion(request,response);
                    break;
                case "editar":
                    editarFuncion(request, response);
                    break;
                case "actualizar":
                   actualizarFuncion(request,response);
                    break;
                case "eliminar":
                    eliminarFuncion(request,response);
                    break;
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
            List<FuncionBeans> listaFunciones = modelo.ListarFunciones();
            List<SalaBeans> listaSalas = modelo.ListarSalas(); // Agrega este método a tu modelo
            List<PeliculaBeans> listaPeliculas = modelo.ListarPeliculas(); // Agrega este método a tu modelo

            request.setAttribute("listafunciones", listaFunciones);
            request.setAttribute("listasalas", listaSalas);
            request.setAttribute("listapeliculas", listaPeliculas); // Agrega la lista de películas al request

            RequestDispatcher rd = request.getRequestDispatcher("gestionFunciones.jsp");
            rd.forward(request, response);
        } catch (IOException | SQLException | ServletException ex) {
            Logger.getLogger(FuncionesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void agregarFuncion(HttpServletRequest request, HttpServletResponse response) {
        try {
            // Retrieve form data
            int idSala = Integer.parseInt(request.getParameter("idSala"));
            int idPelicula = Integer.parseInt(request.getParameter("idPelicula"));
            double duracion = Double.parseDouble(request.getParameter("duracion"));

            // Create a new FuncionBeans object
            FuncionBeans nuevaFuncion = new FuncionBeans();
            nuevaFuncion.setID_Sala(idSala);
            nuevaFuncion.setID_pelicula(idPelicula);
            nuevaFuncion.setDuracion(duracion);

            // Add the new function
            modelo.AgregarFuncion(nuevaFuncion);

            // Redirect to the list after adding
            listartodo(request, response);
        } catch (SQLException | NumberFormatException ex) {
            Logger.getLogger(FuncionesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void editarFuncion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idFuncion = Integer.parseInt(request.getParameter("id"));
            System.out.println(idFuncion);
            FuncionBeans funcion = modelo.ObtenerFuncionPorID(idFuncion);
            request.setAttribute("funcionEditar", funcion);
            List<SalaBeans> listaSalas = modelo.ListarSalas(); // Agrega este método a tu modelo
            request.setAttribute("listasalas", listaSalas);
            List<PeliculaBeans> listaPeliculas = modelo.ListarPeliculas(); // Agrega este método a tu modelo
            request.setAttribute("listapeliculas", listaPeliculas);
            RequestDispatcher rd = request.getRequestDispatcher("editarFuncion.jsp");
            rd.forward(request, response);
        } catch (SQLException | NumberFormatException ex) {
            Logger.getLogger(FuncionesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void actualizarFuncion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Obtener los parámetros del formulario
            int idFuncion = Integer.parseInt(request.getParameter("id"));
            int idSala = Integer.parseInt(request.getParameter("idSala"));
            int idPelicula = Integer.parseInt(request.getParameter("idPelicula"));
            double duracion = Double.parseDouble(request.getParameter("duracion"));

            // Crear un objeto FuncionBeans con los nuevos valores
            FuncionBeans funcionActualizada = new FuncionBeans();
            funcionActualizada.setID_funcion(idFuncion);
            funcionActualizada.setID_Sala(idSala);
            funcionActualizada.setID_pelicula(idPelicula);
            funcionActualizada.setDuracion(duracion);

            // Actualizar la función en la base de datos
            modelo.ActualizarFuncion(funcionActualizada);

            // Redirigir a la página "gestionFunciones.jsp"
            response.sendRedirect(request.getContextPath() + "/Funciones.do?op=listartodo");
        } catch (SQLException | NumberFormatException ex) {
            Logger.getLogger(FuncionesServlet.class.getName()).log(Level.SEVERE, null, ex);
            // Puedes manejar el error de alguna manera, por ejemplo, redirigiendo a una página de error.
            System.out.println("error");
        }
    }
    private void eliminarFuncion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idFuncion = Integer.parseInt(request.getParameter("id"));
            modelo.EliminarFuncion(idFuncion);

            // Redirigir a la página de gestión de funciones después de eliminar
            listartodo(request, response);
        } catch (SQLException | NumberFormatException ex) {
            Logger.getLogger(FuncionesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
