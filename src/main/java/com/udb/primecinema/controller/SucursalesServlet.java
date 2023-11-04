package com.udb.primecinema.controller;

import com.udb.primecinema.beans.SucursalBeans;
import com.udb.primecinema.beans.UsuarioBeans;
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
                case "":

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
            List<SucursalBeans> listaSucursales = modelo.ListarSucursales();
            System.out.println(listaSucursales);
            request.setAttribute("sucursales", listaSucursales);
            RequestDispatcher rd = request.getRequestDispatcher("inicio.jsp");
            rd.forward(request, response);
        } catch (IOException | SQLException | ServletException ex) {
            Logger.getLogger(SucursalesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
