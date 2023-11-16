package com.udb.primecinema.model;

import com.udb.primecinema.beans.BusquedaBeans;
import com.udb.primecinema.beans.CarteleraBeans;
import com.udb.primecinema.beans.PeliculaBeans;
import com.udb.primecinema.beans.SucursalBeans;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BusquedasModel extends Conexion{
    public List<BusquedaBeans> ListaPeliculas(int idSucursal) throws SQLException {
        try {
            List<BusquedaBeans> lista = new ArrayList<>();
            String Sql = "SELECT s.ID_sala AS `SalaNumero`, s.Nombre as `NombreSala`, p.Nombre as `Pelicula` " +
                    "FROM `salas` s " +
                    "INNER JOIN `funcion` f  ON f.ID_sala = s.ID_sala " +
                    "INNER JOIN `peliculas` p ON f.ID_pelicula = p.ID_pelicula " +
                    "WHERE s.ID_sucursal = ?";
            this.conectar();
            st = conexion.prepareStatement(Sql);
            st.setInt(1,idSucursal);
            rs = st.executeQuery();
            while (rs.next()) {
                BusquedaBeans fila = new BusquedaBeans();
                fila.setId_Sala(rs.getInt("SalaNumero"));
                fila.setNombre_Sala(rs.getString("NombreSala"));
                fila.setNombre_Pelicula(rs.getString("Pelicula"));
                lista.add(fila);
            }
            this.desconectar();
            return lista;
        } catch (SQLException e) {
            this.desconectar();
            return null;
        }
    }

    public List<BusquedaBeans> ListarSalaSucursal(int idPelicula) throws SQLException {
        try {
            List<BusquedaBeans> lista = new ArrayList<>();
            String Sql = "SELECT suc.nombre AS `Sucursal`, s.ID_sala AS `SalaNumero`, s.Nombre as `NombreSala` " +
                    "FROM `salas` s " +
                    "INNER JOIN `funcion` f  ON f.ID_sala = s.ID_sala " +
                    "INNER JOIN `peliculas` p ON f.ID_pelicula = p.ID_pelicula " +
                    "INNER JOIN `sucursales` suc ON s.ID_sucursal = suc.ID_sucursal " +
                    "WHERE p.ID_pelicula =  ?";
            this.conectar();
            st = conexion.prepareStatement(Sql);
            st.setInt(1,idPelicula);
            rs = st.executeQuery();
            while (rs.next()) {
                BusquedaBeans fila = new BusquedaBeans();
                fila.setNombre_Sucursal(rs.getString("Sucursal"));
                fila.setId_Sala(rs.getInt("SalaNumero"));
                fila.setNombre_Sala(rs.getString("NombreSala"));
                lista.add(fila);
            }
            this.desconectar();
            return lista;
        } catch (SQLException e) {
            this.desconectar();
            return null;
        }
    }
    public List<PeliculaBeans> PeliculasSelect()throws SQLException {
        try{
            List<PeliculaBeans> lista = new ArrayList<>();
            String Sql="SELECT ID_pelicula, nombre FROM peliculas";
            this.conectar();
            st=conexion.prepareStatement(Sql);
            rs= st.executeQuery();
            while (rs.next()){
                PeliculaBeans pelicula = new PeliculaBeans();
                pelicula.setID_pelicula(rs.getInt("ID_pelicula"));
                pelicula.setNombre(rs.getString("nombre"));
                lista.add(pelicula);
            }
            this.desconectar();
            return lista;
        }catch (SQLException e){
            this.desconectar();
            return null;
        }
    }
}
