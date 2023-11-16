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
    public boolean AgregarUsuario(UsuarioBeans nuevoUsuario) throws SQLException {
        try {
            String sql = "INSERT INTO usuarios (ID_usuario,Nombre,Apellido,DUI,Direccion,Telefono,Email) VALUES (?, ?, ?, ?, ?, ?,?)";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, nuevoUsuario.getID_Usuario());
            st.setString(2, nuevoUsuario.getNombre());
            st.setString(3, nuevoUsuario.getApellido());
            st.setInt(4, nuevoUsuario.getDUI());
            st.setString(5, nuevoUsuario.getDireccion());
            st.setString(6, nuevoUsuario.getTelefono());
            st.setString(7, nuevoUsuario.getEmail());

            int affectedRows = st.executeUpdate();

            if (affectedRows > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, e);
            return false;
        } finally {
            this.desconectar();
        }
    }
    public boolean VerificarDatosUnicos(int id, int dui) throws SQLException {
        try {
            String sql = "SELECT * FROM `usuarios` WHERE `ID_usuario` = ? OR `DUI` = ?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, id);
            st.setInt(2, dui);
            rs =  st.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, e);
            return false;
        } finally {
            this.desconectar();
        }
    }

}
