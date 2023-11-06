package com.udb.primecinema.model;

import com.udb.primecinema.beans.FuncionBeans;
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
    public List<FuncionBeans> ListarFunciones(String idSucursal) throws SQLException {
        try {
            List<FuncionBeans> lista = new ArrayList<>();
            String Sql = "SELECT f.*, p.nombre AS NombrePelicula, s.Nombre AS NombreSala, su.nombre AS NombreSucursal " +
                    "FROM funcion f " +
                    "JOIN salas s ON f.ID_sala = s.ID_sala " +
                    "JOIN sucursales su ON s.ID_sucursal = su.ID_sucursal " +
                    "JOIN peliculas p ON f.ID_pelicula = p.ID_pelicula " +
                    "WHERE su.ID_sucursal = ? " +
                    "GROUP BY f.ID_funcion"; // Agrupar por ID_funcion para obtener solo un registro por función
            this.conectar();
            st = conexion.prepareStatement(Sql);
            st.setString(1, idSucursal);
            rs = st.executeQuery();
            while (rs.next()) {
                FuncionBeans funcionBeans = new FuncionBeans();
                funcionBeans.setID_funcion(rs.getInt("ID_funcion"));
                funcionBeans.setID_Sala(rs.getInt("ID_sala"));
                funcionBeans.setID_pelicula(rs.getInt("ID_pelicula"));
                funcionBeans.setDuracion(rs.getDouble("duracion"));
                funcionBeans.setNombrepelicula(rs.getString("NombrePelicula"));
                funcionBeans.setNombreSala(rs.getString("NombreSala"));
                funcionBeans.setNombreSucursal(rs.getString("NombreSucursal"));
                lista.add(funcionBeans);
            }
            this.desconectar();
            return lista;
        } catch (SQLException e) {
            Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, e);
            this.desconectar();
            return null;
        }
    }

}
