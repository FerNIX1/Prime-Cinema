package com.udb.primecinema.controller;

import com.udb.primecinema.beans.BusquedaBeans;
import com.udb.primecinema.beans.PeliculaBeans;
import com.udb.primecinema.beans.SucursalBeans;
import com.udb.primecinema.model.BusquedasModel;
import com.udb.primecinema.model.PeliculaModel;
import com.udb.primecinema.model.SucursalModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "BusquedasServlet", value = "/busquedas.do")
public class BusquedasServlet extends HttpServlet {
    BusquedasModel modeloBusquedas = new BusquedasModel();
    SucursalModel modeloSucursales = new SucursalModel();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if (request.getParameter("op") == null) {
            try {
                List<SucursalBeans> listaSucursales = modeloSucursales.ListarSucursales();
                List<PeliculaBeans> listaPeliculas = modeloBusquedas.PeliculasSelect();

                request.setAttribute("sucursales", listaSucursales);
                request.setAttribute("peliculas", listaPeliculas);

                request.getRequestDispatcher("busquedas.jsp").forward(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

        String operacion = request.getParameter("op");
        switch (operacion) {
            case "BuscarSucursal":
                BuscarSucursal(request,response);
                break;
            case "BuscarPelicula":
                BuscarPelicula(request,response);
                break;
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

    private void BuscarSucursal(HttpServletRequest request, HttpServletResponse response) {
        int id_sucursal = Integer.parseInt(request.getParameter("sucursal"));

        try {
            List<SucursalBeans> listaSucursales = modeloSucursales.ListarSucursales();
            request.setAttribute("sucursales", listaSucursales);
            List<PeliculaBeans> listaPeliculas = modeloBusquedas.PeliculasSelect();
            request.setAttribute("peliculas", listaPeliculas);

            List<BusquedaBeans> PeliculasPorSucursal = modeloBusquedas.ListaPeliculas(id_sucursal);

            request.setAttribute("PeliculasPorSucursal", PeliculasPorSucursal);
            request.getRequestDispatcher("busquedas.jsp").forward(request, response);

        } catch (SQLException | ServletException | IOException exception) {
            System.out.println("ERROR " + exception.getMessage());
        }
    }
    private void BuscarPelicula(HttpServletRequest request, HttpServletResponse response) {
        int id_pelicula = Integer.parseInt(request.getParameter("pelicula"));

        try {
            List<SucursalBeans> listaSucursales = modeloSucursales.ListarSucursales();
            request.setAttribute("sucursales", listaSucursales);
            List<PeliculaBeans> listaPeliculas = modeloBusquedas.PeliculasSelect();
            request.setAttribute("peliculas", listaPeliculas);

            List<BusquedaBeans> SalasPorPelicula = modeloBusquedas.ListarSalaSucursal(id_pelicula);

            request.setAttribute("SalasPorPelicula", SalasPorPelicula);
            request.getRequestDispatcher("busquedas.jsp").forward(request, response);

        } catch (SQLException | ServletException | IOException exception) {
            System.out.println("ERROR " + exception.getMessage());
        }
    }
}
