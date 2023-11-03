package com.udb.primecinema.controller;

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
        int IdUsuario = Integer.parseInt(request.getParameter("nombre"));
        int DUI = Integer.parseInt(request.getParameter("apellido"));
        try{
            modelo.Logearse(IdUsuario,DUI);
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }catch (SQLException | ServletException | IOException exception ){
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE,null,exception);
        }

    }
}
