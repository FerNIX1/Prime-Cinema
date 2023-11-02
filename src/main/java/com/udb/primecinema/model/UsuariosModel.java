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
}
