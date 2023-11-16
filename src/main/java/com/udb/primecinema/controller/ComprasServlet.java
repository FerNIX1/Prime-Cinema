package com.udb.primecinema.controller;

import com.udb.primecinema.beans.CarteleraBeans;
import com.udb.primecinema.beans.SucursalBeans;
import com.udb.primecinema.model.ComprasModel;
import com.udb.primecinema.model.SucursalModel;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ComprasServlet", value = "/compras.do")
public class ComprasServlet extends HttpServlet {
    SucursalModel modeloSucursales = new SucursalModel();
    ComprasModel modeloCompras = new ComprasModel();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String op = request.getParameter("operacion");
        System.out.println(op);
        switch (op) {
            case "mostrar":
                mostrar_Sucursales(request, response);
                break;
            case "VerFunciones":
                mostrar_Funciones(request, response);
                break;
            case "NuevaCompra":
                crear_Compra(request, response);
                break;
            default:
                mostrar_Sucursales(request, response);
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
    private void mostrar_Sucursales(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<SucursalBeans> listaSucursales = modeloSucursales.ListarSucursales();
            System.out.println(listaSucursales);
            request.setAttribute("sucursales", listaSucursales);
            RequestDispatcher rd = request.getRequestDispatcher("venderBoletos.jsp");
            rd.forward(request, response);
        } catch (IOException | SQLException | ServletException ex) {
            Logger.getLogger(ComprasServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void mostrar_Funciones(HttpServletRequest request, HttpServletResponse response) {
        String id_sucursal = request.getParameter("sucursal");
        try {
            List<CarteleraBeans> funciones = modeloCompras.ListarCartelera(id_sucursal);
            if (funciones != null) {
                request.setAttribute("funciones", funciones);
                request.getRequestDispatcher("venderBoletos.jsp").forward(request, response);
            } else {
                // Manejar el caso cuando no se encuentran funciones
                request.setAttribute("errorMensaje", "No se encontraron funciones para esta sucursal.");
                request.getRequestDispatcher("venderBoletos.jsp").forward(request, response);
            }
        } catch (SQLException | ServletException | IOException exception) {
            Logger.getLogger(ComprasServlet.class.getName()).log(Level.SEVERE, null, exception);
            System.out.println("ERROR");
        }
    }
    private void crear_Compra(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Se obtienen parametros de la pagina JSP
        String id_funcion = request.getParameter("funcion");
        String butaca = request.getParameter("butaca");
        int tipoBoleto = Integer.parseInt(request.getParameter("tipo_boleto"));
        int metodoPago = Integer.parseInt(request.getParameter("metodo"));
        String efectivo = request.getParameter("efectivo");
        int id_usuario = Integer.parseInt(request.getParameter("usuario_id"));

        //Se verifica que NO esten vacios los campos
        if (id_funcion.isEmpty()) {
            request.setAttribute("errorFormulario", "Debe elegir una función para continuar");
            request.getRequestDispatcher("venderBoletos.jsp").forward(request, response);
        } else if (butaca.isEmpty()) {
            request.setAttribute("errorFormulario", "Debe elegir una butaca para continuar");
            request.getRequestDispatcher("venderBoletos.jsp").forward(request, response);
        } else {
            //Se procede a crear la compra
            try {


                double precio = modeloCompras.CalcularPrecio(Integer.parseInt(id_funcion), tipoBoleto); //Se calcula el precio
                modeloCompras.RegistrarVenta(id_usuario, Integer.parseInt(id_funcion), Integer.parseInt(butaca), precio); //Se registra la compra

                if (metodoPago == 1){
                    double vuelto = modeloCompras.CalcularVuelto(precio, Double.parseDouble(efectivo));
                    if( vuelto < 0){
                        request.setAttribute("errorFormulario", "Compra no realizada, Efectivo NO suficiente");
                    } else {
                        request.setAttribute("MensajeExito", "Compra realizada con exito, su vuelto es de $" + vuelto);
                    }
                } else{
                    request.setAttribute("MensajeExito", "Compra realizada con exito");
                }
                request.getRequestDispatcher("venderBoletos.jsp").forward(request, response);

            } catch (SQLException | ServletException | IOException exception) {
                Logger.getLogger(SucursalesServlet.class.getName()).log(Level.SEVERE, null, exception);
            }
        }
    }
}
