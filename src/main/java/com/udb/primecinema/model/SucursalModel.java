package com.udb.primecinema.model;

import com.udb.primecinema.beans.CarteleraBeans;
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

}
