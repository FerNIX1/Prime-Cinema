package com.udb.primecinema.controller;

import com.udb.primecinema.beans.PeliculaBeans;
import com.udb.primecinema.beans.SucursalBeans;
import com.udb.primecinema.model.PeliculaModel;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "PeliculasServlet", value = "/Peliculas.do")
public class PeliculasServlet extends HttpServlet {
    PeliculaModel modelo = new PeliculaModel();
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
                case "Agregar":
                 Agregar(request,response);
                    break;
                case "editar":
                    editar(request,response);
                    break;
                case "actualizar":
                    actualizar(request,response);
                    break;
                case "eliminar":
                    eliminar(request,response);
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
    private void imprimir(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("listaPeliculas",modelo.ListarPeliculas());
            request.getRequestDispatcher("gestionPeliculas.jsp").forward(request,response);
        } catch (IOException | SQLException | ServletException ex) {
            Logger.getLogger(SucursalesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void Agregar(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String genero = request.getParameter("genero");
        String clasificacion = request.getParameter("clasificacion");
        String formato = request.getParameter("formato");
        try {
            PeliculaBeans nuevapelicula= new PeliculaBeans();
            nuevapelicula.setID_pelicula(id);
            nuevapelicula.setNombre(nombre);
            nuevapelicula.setID_genero(Integer.parseInt(genero));
            nuevapelicula.setID_clasificacion(Integer.parseInt(clasificacion));
            nuevapelicula.setID_formato(Integer.parseInt(formato));
            modelo.agregarPelicula(nuevapelicula);
            response.sendRedirect(request.getContextPath() + "/Peliculas.do?op=Agregar");
        } catch (SQLException ex) {
            Logger.getLogger(SucursalesServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void editar(HttpServletRequest request, HttpServletResponse response) {
        String idStr = request.getParameter("id");
        System.out.println(idStr);//esto es null
        if (idStr != null) {
            try {
                int userId = Integer.parseInt(idStr);
                PeliculaBeans peli  = modelo.obtenerPeliculaPorId(userId);
                request.setAttribute("peli", peli );
                request.getRequestDispatcher("editarPelicula.jsp").forward(request, response);
            } catch (NumberFormatException | SQLException | ServletException | IOException e) {
                Logger.getLogger(PeliculasServlet.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    private void actualizar(HttpServletRequest request, HttpServletResponse response) {
        // Obtener los datos del formulario
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        int idGenero = Integer.parseInt(request.getParameter("id_genero"));
        int idClasificacion = Integer.parseInt(request.getParameter("id_clasificacion"));
        int idFormato = Integer.parseInt(request.getParameter("id_formato"));
        System.out.println(id);
        System.out.println(nombre);
        System.out.println(idGenero);
        System.out.println(idClasificacion);
        System.out.println(idFormato);
        // Crear un objeto UsuariosBean con los nuevos datos
        PeliculaBeans peli = new PeliculaBeans();
        peli.setID_pelicula(id);
        peli.setNombre(nombre);
        peli.setID_genero(idGenero);
        peli.setID_clasificacion(idClasificacion);
        peli.setID_formato(idFormato);

        try {
            // Actualizar el usuario en la base de datos
            modelo.actualizarPeli(peli);
            // Redirigir a la página de listado después de la actualización
            response.sendRedirect(request.getContextPath() + "/Peliculas.do?op=imprimir");
        } catch (SQLException | IOException e) {
            Logger.getLogger(PeliculasServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    private void eliminar(HttpServletRequest request, HttpServletResponse response) {
        // Obtener el ID del usuario a eliminar
        int idUsuario = Integer.parseInt(request.getParameter("id"));

        try {
            // Eliminar el usuario de la base de datos
            modelo.eliminarPeli(idUsuario);

            // Redirigir a la página de listado después de la eliminación
            response.sendRedirect(request.getContextPath() + "/Peliculas.do?op=imprimir");
        } catch (SQLException | IOException e) {
            Logger.getLogger(PeliculasServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
