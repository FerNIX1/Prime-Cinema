package com.udb.primecinema.model;

import com.udb.primecinema.beans.SalaBeans;
import com.udb.primecinema.beans.SucursalBeans;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SalaModel extends Conexion{
    public List<SalaBeans> ListarTodo()throws SQLException {
        try{
            List<SalaBeans> lista = new ArrayList<>();
            String Sql="SELECT * FROM salas;";
            this.conectar();
            st=conexion.prepareStatement(Sql);
            rs= st.executeQuery();
            while (rs.next()){
                SalaBeans salaBeans= new SalaBeans();
                salaBeans.setID_sala(rs.getInt("ID_sala"));
                salaBeans.setNombre(rs.getString("Nombre"));
                salaBeans.setID_sucursal(Integer.parseInt(rs.getString("ID_sucursal")));
                lista.add(salaBeans);
            }
            this.desconectar();
            return lista;
        }catch (SQLException e){
            Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, e);
            this.desconectar();
            return null;
        }
    }
    public void agregarSala(SalaBeans nuevoSala) throws SQLException {
        try {
            // Sentencia SQL para insertar datos en la tabla de películas (peliculas)
            String sql = "INSERT INTO salas (ID_sala,Nombre,ID_sucursal) VALUES (?, ?, ?)";

            this.conectar();

            // Establecer los valores en la sentencia SQL
            st = conexion.prepareStatement(sql);
            st.setInt(1, nuevoSala.getID_sala());
            st.setString(2,nuevoSala.getNombre());
            st.setInt(3, nuevoSala.getID_sucursal());
            st.executeUpdate();

            this.desconectar(); // Desconexión de la base de datos
        } catch (SQLException e) {
            Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, e);
            this.desconectar();
        }
    }
    public SalaBeans obtenerSalaPorId(int sucuID) throws SQLException {
        try {
            SalaBeans salaBeans = null;
            String sql = "SELECT * FROM salas WHERE ID_sala = ?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, sucuID);
            rs = st.executeQuery();
            if (rs.next()) {
                salaBeans = new SalaBeans();
                salaBeans.setID_sucursal(rs.getInt("ID_sala"));
                salaBeans.setNombre(rs.getString("Nombre"));
                salaBeans.setID_sucursal(rs.getInt("ID_sucursal"));
            }
            return salaBeans;
        } finally {
            this.desconectar();
        }
    }
    public void actualizarSala(SalaBeans sala) throws SQLException {
        try {
            String sql = "UPDATE salas SET ID_sala =?,Nombre=?,ID_sucursal=? WHERE ID_sala =?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, sala.getID_sala());
            st.setString(2, sala.getNombre());
            st.setInt(3, sala.getID_sucursal());
            st.setInt(4, sala.getID_sala());
            st.executeUpdate();
        } finally {
            this.desconectar();
        }
    }
    public void eliminarSala(int idsala) throws SQLException {
        try {
            String sql = "DELETE FROM salas WHERE ID_sala=?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, idsala);
            st.executeUpdate();
        } finally {
            this.desconectar();
        }
    }
}
