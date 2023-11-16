package com.udb.primecinema.model;

import com.udb.primecinema.beans.FuncionBeans;
import com.udb.primecinema.beans.PeliculaBeans;
import com.udb.primecinema.beans.SalaBeans;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FuncionesModel extends Conexion {
    public List<FuncionBeans> ListarFunciones()throws SQLException {
        try{
            List<FuncionBeans> lista = new ArrayList<>();
            String Sql="SELECT * FROM funcion;";
            this.conectar();
            st=conexion.prepareStatement(Sql);
            rs= st.executeQuery();
            while (rs.next()) {
               FuncionBeans listar = new FuncionBeans();
                listar.setID_funcion(rs.getInt("ID_funcion"));
                listar.setID_Sala(rs.getInt("ID_sala"));
                listar.setID_pelicula(rs.getInt("ID_pelicula"));
                listar.setDuracion(rs.getDouble("duracion"));
                lista.add(listar);
            }
            this.desconectar();
            return lista;
        }catch (SQLException e){
            Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, e);
            this.desconectar();
            return null;
        }
    }
    public List<SalaBeans> ListarSalas() throws SQLException {
        try {
            List<SalaBeans> lista = new ArrayList<>();
            String Sql = "SELECT ID_sala FROM funcion;"; // Puedes personalizar tu consulta
            this.conectar();
            st = conexion.prepareStatement(Sql);
            rs = st.executeQuery();
            while (rs.next()) {
                SalaBeans sala = new SalaBeans();
                sala.setID_sala(rs.getInt("ID_sala"));
                lista.add(sala);
            }
            this.desconectar();
            return lista;
        } catch (SQLException e) {
            Logger.getLogger(FuncionesModel.class.getName()).log(Level.SEVERE, null, e);
            this.desconectar();
            return null;
        }
    }
    public List<PeliculaBeans> ListarPeliculas() throws SQLException {
        try {
            List<PeliculaBeans> lista = new ArrayList<>();
            String Sql = "SELECT ID_pelicula FROM funcion;";
            this.conectar();
            st = conexion.prepareStatement(Sql);
            rs = st.executeQuery();
            while (rs.next()) {
                PeliculaBeans pelicula = new PeliculaBeans();
                pelicula.setID_pelicula(rs.getInt("ID_pelicula"));
                lista.add(pelicula);
            }
            this.desconectar();
            return lista;
        } catch (SQLException e) {
            Logger.getLogger(FuncionesModel.class.getName()).log(Level.SEVERE, null, e);
            this.desconectar();
            return null;
        }
    }
    public List<FuncionBeans> AgregarFuncion(FuncionBeans nuevaFuncion) throws SQLException {
        try {
            String Sql = "INSERT INTO funcion (ID_funcion, ID_sala, ID_pelicula, duracion) VALUES (?, ?, ?, ?)";
            this.conectar();
            st = conexion.prepareStatement(Sql);
            st.setInt(1, nuevaFuncion.getID_funcion());
            st.setInt(2, nuevaFuncion.getID_Sala());
            st.setInt(3, nuevaFuncion.getID_pelicula());
            st.setDouble(4, nuevaFuncion.getDuracion());

            int affectedRows = st.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Funcion agregada exitosamente.");
            } else {
                System.out.println("Error al agregar la funcion.");
            }

            this.desconectar();
            return ListarFunciones();

        } catch (SQLException e) {
            Logger.getLogger(FuncionesModel.class.getName()).log(Level.SEVERE, null, e);
            this.desconectar();
            return null;
        }
    }
    public FuncionBeans ObtenerFuncionPorID(int idFuncion) throws SQLException {
        try {
            FuncionBeans funcion = null;
            String sql = "SELECT * FROM funcion WHERE ID_funcion = ?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, idFuncion);
            rs = st.executeQuery();
            if (rs.next()) {
                funcion = new FuncionBeans();
                funcion.setID_funcion(rs.getInt("ID_funcion"));
                funcion.setID_Sala(rs.getInt("ID_sala"));
                funcion.setID_pelicula(rs.getInt("ID_pelicula"));
                funcion.setDuracion(rs.getDouble("duracion"));
            }
            return funcion;
        } finally {
            this.desconectar();
        }
    }
    public void ActualizarFuncion(FuncionBeans funcion) throws SQLException {
        try {
            String sql = "UPDATE funcion SET ID_sala=?, ID_pelicula=?, duracion=? WHERE ID_funcion=?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, funcion.getID_Sala());
            st.setInt(2, funcion.getID_pelicula());
            st.setDouble(3, funcion.getDuracion());
            st.setInt(4, funcion.getID_funcion());
            st.executeUpdate();
        } finally {
            this.desconectar();
        }
    }
    public void EliminarFuncion(int idFuncion) throws SQLException {
        try {
            String sql = "DELETE FROM funcion WHERE ID_funcion=?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, idFuncion);
            st.executeUpdate();
        } finally {
            this.desconectar();
        }
    }
}
