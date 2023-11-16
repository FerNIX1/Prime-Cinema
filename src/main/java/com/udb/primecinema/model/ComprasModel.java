package com.udb.primecinema.model;

import com.udb.primecinema.beans.CarteleraBeans;

import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComprasModel extends Conexion{
    public List<CarteleraBeans> ListarCartelera(String idSucursal) throws SQLException {
        try {
            List<CarteleraBeans> lista = new ArrayList<>();
            String Sql = "SELECT f.ID_funcion, f.ID_sala, p.Nombre, f.duracion, g.Nombre AS Clasificación, c.Nombre AS Género, fr.Nombre AS Formato " +
                    "     FROM `funcion` f " +
                    "    INNER JOIN `peliculas` p ON f.ID_pelicula = p.ID_pelicula" +
                    "    INNER JOIN `generos` g ON p.ID_genero = g.ID_genero" +
                    "    INNER JOIN `clasificaciones` c ON p.ID_clasificacion = c.ID_clasificacion" +
                    "    INNER JOIN `formatos` fr ON p.ID_formato = fr.ID_formato" +
                    "    WHERE (SELECT ID_sala FROM `salas` WHERE ID_sucursal = ?) = f.ID_sala"; // Agrupar por ID_funcion para obtener solo un registro por función
            this.conectar();
            st = conexion.prepareStatement(Sql);
            st.setString(1, idSucursal);
            rs = st.executeQuery();
            while (rs.next()) {
                CarteleraBeans funcion = new CarteleraBeans();
                funcion.setID_funcion(rs.getInt("ID_funcion"));
                funcion.setID_sala(rs.getInt("ID_sala"));
                funcion.setPelicula(rs.getString("Nombre"));
                funcion.setDuracion(rs.getDouble("duracion"));
                funcion.setClasificacion(rs.getString("Clasificación"));
                funcion.setGenero(rs.getString("Género"));
                funcion.setFormato(rs.getString("Formato"));
                lista.add(funcion);
            }
            this.desconectar();
            return lista;
        } catch (SQLException e) {
            Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, e);
            this.desconectar();
            return null;
        }
    }
    public double CalcularPrecio(int id_funcion, int tipo_boleto) throws SQLException{
        try {
            String Sql = "SELECT p.ID_formato " +
                    " FROM `funcion` f " +
                    " INNER JOIN `peliculas` p ON f.ID_pelicula = p.ID_pelicula " +
                    " WHERE f.ID_funcion = ?";
            this.conectar();
            st = conexion.prepareStatement(Sql);
            st.setInt(1, id_funcion);
            rs = st.executeQuery();
            rs.next();

                int ID_formato = rs.getInt("ID_formato");

                if (tipo_boleto == 1 && ID_formato == 1) {
                    return 5.00;
                } else if (tipo_boleto == 1 && ID_formato == 2) {
                    return 6.55;
                } else if (tipo_boleto == 2 && ID_formato == 1) {
                    return 3.90;
                } else if (tipo_boleto == 2 && ID_formato == 2) {
                    return 5.60;
                } else {
                    return 0;
                }

        } catch (SQLException e){
            Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, e);
            this.desconectar();
            return 0;
        }finally {
            this.desconectar();
        }
    }
    public void RegistrarVenta(int id_usuario, int id_funcion,int Numero_butaca, double costo) throws SQLException {

        this.conectar();
        int ID_Butaca = RegistrarAsiento(Numero_butaca, id_funcion);

        if(ID_Butaca != 0) {
            System.out.println("Se registrara la venta con los valores de Usuario: " + id_usuario + ", Funcion:" + id_funcion + ", NumeroButaca: " + Numero_butaca + ", Costo: " + costo);
            st = conexion.prepareStatement("INSERT INTO `compras`(ID_usuario,ID_funcion,ID_butaca,Costo) VALUES (?,?,?,?)");
            st.setInt(1, id_usuario);
            st.setInt(2, id_funcion);
            st.setInt(3, ID_Butaca);
            st.setDouble(4, costo);
            st.executeUpdate();
            this.desconectar();
            System.out.println("Compra Registrada");
        } else {
            this.desconectar();
            System.out.println("Butaca Ocupada");
        }
    }
    private int RegistrarAsiento(int butaca, int funcion) throws SQLException {

        this.conectar();
        String Sql = "SELECT * FROM `butacas` WHERE Numero = ? AND ID_funcion = ?";
        st = conexion.prepareStatement(Sql);
        st.setInt(1, butaca);
        st.setInt(2, funcion);
        rs = st.executeQuery();
        if (rs.next()) { //Si existe la butaca

            int Ocupada = rs.getInt("Ocupada");

            if(Ocupada == 1) { //Si la Butaca esta OCUPADA
                this.desconectar();
                return 0;
            } else if (Ocupada == 0) { //Si la Butaca esta DESOCUPADA
                int id_butaca = rs.getInt("ID_butaca");
                Update_Butaca(id_butaca);
                return id_butaca;
            } else {
                 return 0;
            }
        } else { //Si NO existe la butaca
            int ID = Insertar_Butaca(butaca,funcion);
            return ID;
        }
    }
    private int ObtenerID_Butaca (int butaca, int funcion) throws SQLException {
        //Obtener ID de butaca A partir de Numero de Butaca y Función
        String sql = "SELECT ID_butaca FROM `butacas` WHERE Numero = ? AND ID_funcion = ?";
        this.conectar();
        st = conexion.prepareStatement(sql);
        st.setInt(1, butaca);
        st.setInt(2, funcion);
        rs = st.executeQuery();
        rs.next();
        int ID = rs.getInt("ID_butaca");
        this.desconectar();
        return ID;
    }
    private int Insertar_Butaca(int butaca, int funcion) throws SQLException {
        String InsertarSQL = "INSERT INTO `butacas`(Numero,ID_funcion,Ocupada) VALUES (?,?,?)";
        this.conectar();
        st = conexion.prepareStatement(InsertarSQL);
        st.setInt(1, butaca);
        st.setInt(2, funcion);
        st.setInt(3, 1);
        st.executeUpdate();
        this.desconectar();

        int ID_Butaca = ObtenerID_Butaca(butaca, funcion); //Obtener ID de butaca que se acaba de agregar
        return ID_Butaca;
    }
    private void Update_Butaca (int id_butaca) throws SQLException{
        String UpdateSQL = "UPDATE `butacas` SET `Ocupada` = ? WHERE `ID_butaca` = ?";
        this.conectar();
        st = conexion.prepareStatement(UpdateSQL);
        st.setInt(1, 1);
        st.setInt(2, id_butaca);
        st.executeUpdate();
        this.desconectar();
    }
    public double CalcularVuelto(double precio, double efectivo){
        return efectivo - precio;
    }
    private void VaciarFuncion(int ID_funcion) throws SQLException {
        String sql = "UPDATE `butacas` SET `Ocupada` = 0 WHERE ID_funcion = ?";
        this.conectar();
        st = conexion.prepareStatement(sql);
        st.setInt(1, ID_funcion);
        st.executeUpdate();
        this.desconectar();
    }
}
