package com.udb.primecinema.model;

import com.udb.primecinema.beans.UsuarioBeans;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuariosModel extends Conexion {
    public UsuarioBeans Logearse(int userId,int Dui) throws SQLException {
        try {
            UsuarioBeans usuario = null;
            String sql = "SELECT * FROM usuarios WHERE ID_usuario = ? AND DUI = ?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, userId);
            st.setInt(2,Dui);
            rs = st.executeQuery();
            if (rs.next()) {
                usuario = new UsuarioBeans(
                        rs.getInt("ID_usuario"),
                        rs.getString("Nombre"),
                        rs.getString("Apellido"),
                        rs.getInt("DUI"),
                        rs.getString("Direccion"),
                        rs.getString("Email"),
                        rs.getString("Telefono")
                );
            }
            return usuario;
        } finally {
            this.desconectar();
        }
    }
    public void AgregarUsuario(UsuarioBeans nuevoUsuario) throws SQLException {
        try {
            String sql = "INSERT INTO usuarios (Nombre,Apellido,DUI,Direccion,Telefono,Email) VALUES (?, ?, ?, ?, ?, ?)";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, nuevoUsuario.getNombre());
            st.setString(2, nuevoUsuario.getApellido());
            st.setInt(3, nuevoUsuario.getDUI());
            st.setString(4, nuevoUsuario.getDireccion());
            st.setString(5, nuevoUsuario.getTelefono());
            st.setString(6, nuevoUsuario.getEmail());

            int affectedRows = st.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Usuario agregado exitosamente.");
            } else {
                System.out.println("Error al agregar el usuario.");
            }
        } catch (SQLException e) {
            Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            this.desconectar();
        }
    }
}
