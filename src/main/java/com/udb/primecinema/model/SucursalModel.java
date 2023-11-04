package com.udb.primecinema.model;

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
}
