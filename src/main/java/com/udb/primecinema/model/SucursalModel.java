package com.udb.primecinema.model;

import com.udb.primecinema.beans.CarteleraBeans;
import com.udb.primecinema.beans.FuncionBeans;
import com.udb.primecinema.beans.PeliculaBeans;
import com.udb.primecinema.beans.SucursalBeans;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SucursalModel extends Conexion {
    public List<SucursalBeans> ListarSucursales()throws SQLException {
        try{
            List<SucursalBeans> lista = new ArrayList<>();
            String Sql="SELECT ID_sucursal, nombre FROM sucursales;";
            this.conectar();
            st=conexion.prepareStatement(Sql);
            rs= st.executeQuery();
            while (rs.next()){
                SucursalBeans sucursalBeans= new SucursalBeans();
                sucursalBeans.setID_sucursal(rs.getInt("ID_sucursal"));
                sucursalBeans.setNombre(rs.getString("nombre"));
                lista.add(sucursalBeans);
            }
            this.desconectar();
            return lista;
        }catch (SQLException e){
            Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, e);
            this.desconectar();
            return null;
        }
    }
    public List<SucursalBeans>ListarTodo()throws SQLException {
        try{
            List<SucursalBeans> lista = new ArrayList<>();
            String Sql="SELECT * FROM sucursales;";
            this.conectar();
            st=conexion.prepareStatement(Sql);
            rs= st.executeQuery();
            while (rs.next()){
                SucursalBeans sucursalBeans= new SucursalBeans();
                sucursalBeans.setID_sucursal(rs.getInt("ID_sucursal"));
                sucursalBeans.setNombre(rs.getString("nombre"));
                sucursalBeans.setGerente(rs.getString("Gerente"));
                sucursalBeans.setDireccion(rs.getString("Direccion"));
                sucursalBeans.setTelefono(rs.getString("Telefono"));
                lista.add(sucursalBeans);
            }
            this.desconectar();
            return lista;
        }catch (SQLException e){
            Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, e);
            this.desconectar();
            return null;
        }
    }

    public List<CarteleraBeans> ListarCartelera(String idSucursal) throws SQLException {
        try {
            List<CarteleraBeans> lista = new ArrayList<>();
            String Sql = "SELECT f.ID_sala, p.Nombre, f.duracion, g.Nombre AS Clasificación, c.Nombre AS Género, fr.Nombre AS Formato " +
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
    public void agregarSucursal(SucursalBeans nuevoSucursal) throws SQLException {
        try {
            // Sentencia SQL para insertar datos en la tabla de películas (peliculas)
            String sql = "INSERT INTO sucursales (ID_sucursal,nombre,Gerente,Direccion,Telefono) VALUES (?, ?, ?, ?, ?)";

            this.conectar(); // Conexión a la base de datos

            // Establecer los valores en la sentencia SQL
            st = conexion.prepareStatement(sql);
            st.setInt(1, nuevoSucursal.getID_sucursal());
            st.setString(2,nuevoSucursal.getNombre());
            st.setString(3, nuevoSucursal.getGerente());
            st.setString(4, nuevoSucursal.getDireccion());
            st.setString(5, nuevoSucursal.getTelefono());

            st.executeUpdate();

            this.desconectar(); // Desconexión de la base de datos
        } catch (SQLException e) {
            Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, e);
            this.desconectar();
        }
    }
    public SucursalBeans obtenerSucursalPorId(int sucuID) throws SQLException {
        try {
            SucursalBeans sucursal = null;
            String sql = "SELECT * FROM sucursales WHERE ID_sucursal  = ?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, sucuID);
            rs = st.executeQuery();
            if (rs.next()) {
                sucursal = new SucursalBeans();
                sucursal.setID_sucursal(rs.getInt("ID_sucursal"));
                sucursal.setNombre(rs.getString("nombre"));
                sucursal.setGerente(rs.getString("Gerente"));
                sucursal.setDireccion(rs.getString("Direccion"));
                sucursal.setTelefono(rs.getString("Telefono"));
            }
            return sucursal;
        } finally {
            this.desconectar();
        }
    }
    public void actualizarSucursal(SucursalBeans sucursal) throws SQLException {
        try {
            String sql = "UPDATE sucursales SET ID_sucursal =?,nombre=?,Gerente=?, Direccion=?,Telefono=? WHERE ID_sucursal =?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, sucursal.getID_sucursal());
            st.setString(2, sucursal.getNombre());
            st.setString(3, sucursal.getGerente());
            st.setString(4, sucursal.getDireccion());
            st.setString(5, sucursal.getTelefono());
            st.setInt(6, sucursal.getID_sucursal());
            st.executeUpdate();
        } finally {
            this.desconectar();
        }
    }
    public void eliminarSucursal(int idsucursal) throws SQLException {
        try {
            String sql = "DELETE FROM sucursales WHERE ID_sucursal=?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, idsucursal);
            st.executeUpdate();
        } finally {
            this.desconectar();
        }
    }
}
