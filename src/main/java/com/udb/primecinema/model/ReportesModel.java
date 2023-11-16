package com.udb.primecinema.model;

import com.udb.primecinema.beans.ReporteSala;
import com.udb.primecinema.beans.ReporteSucursal;
import com.udb.primecinema.controller.ReportesServlet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportesModel extends Conexion {
    public List<ReporteSucursal> reportarSucursales() throws SQLException {
        try {
            List<ReporteSucursal> lista = new ArrayList<>();
            String Sql = "SELECT suc.nombre AS `Sucursal`, " +
                    "           COUNT(*) AS `Entradas Vendidas` " +
                    "    FROM `salas` s \n" +
                    "    INNER JOIN `sucursales` suc ON s.ID_sucursal = suc.ID_sucursal " +
                    "    INNER JOIN `funcion` f ON s.ID_sala = f.ID_sala " +
                    "    INNER JOIN `compras` c ON f.ID_funcion = c.ID_funcion " +
                    "    GROUP BY `Sucursal` " +
                    "    ORDER BY `Entradas Vendidas` DESC";
            this.conectar();
            st = conexion.prepareStatement(Sql);
            rs = st.executeQuery();
            while (rs.next()) {
                ReporteSucursal sucursal = new ReporteSucursal();
                sucursal.setNombreSucursal(rs.getString("Sucursal"));
                sucursal.setEntradas(rs.getInt("Entradas Vendidas"));
                lista.add(sucursal);
            }
            this.desconectar();
            return lista;
        } catch (SQLException e) {
            this.desconectar();
            return null;
        }
    }
    public List<ReporteSala> reportarSalas() throws  SQLException{
        try {
            List<ReporteSala> lista = new ArrayList<>();
            String Sql = "SELECT s.ID_sala, s.Nombre, suc.nombre AS `Sucursal` , SUM(c.Costo) AS `Recaudacion de Sala` " +
                    "FROM `salas` s " +
                    "INNER JOIN `funcion` f ON s.ID_sala = f.ID_sala " +
                    "INNER JOIN `compras` c ON f.ID_funcion = c.ID_funcion " +
                    "INNER JOIN `sucursales`suc ON s.ID_sucursal = suc.ID_sucursal " +
                    "GROUP BY s.ID_sala, s.Nombre " +
                    "ORDER BY `Recaudacion de Sala` DESC";
            this.conectar();
            st = conexion.prepareStatement(Sql);
            rs = st.executeQuery();
            while (rs.next()) {
                ReporteSala sala = new ReporteSala();
                sala.setID_sala(rs.getInt("ID_sala"));
                sala.setNombre_Sala(rs.getString("Nombre"));
                sala.setNombre_Sucursal(rs.getString("Sucursal"));
                sala.setIngreso(rs.getDouble("Recaudacion de Sala"));
                lista.add(sala);
            }
            this.desconectar();
            return lista;
        } catch (SQLException e) {
            this.desconectar();
            return null;
        }
    }
}
