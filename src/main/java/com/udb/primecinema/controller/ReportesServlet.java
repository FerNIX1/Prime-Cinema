package com.udb.primecinema.controller;

import com.udb.primecinema.beans.ReporteSala;
import com.udb.primecinema.beans.ReporteSucursal;
import com.udb.primecinema.model.ReportesModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "ReportesServlet", value = "/reportes.do")
public class ReportesServlet extends HttpServlet {
    ReportesModel reportesModel = new ReportesModel();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String op = request.getParameter("operacion");
        System.out.println(op);
        if (Objects.equals(op, "Imprimir")){
            ImprimirReportes(request, response);
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
    private void ImprimirReportes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<ReporteSucursal> reporteSucursales = reportesModel.reportarSucursales();
            List<ReporteSala> reporteSalas = reportesModel.reportarSalas();

            request.setAttribute("SucursalReporte", reporteSucursales);
            request.setAttribute("SalaReporte", reporteSalas);
            request.getRequestDispatcher("reportes.jsp").forward(request, response);
        } catch (SQLException | ServletException | IOException exception) {
            System.out.println("ERROR " + exception.getMessage());
        }
    }
}
