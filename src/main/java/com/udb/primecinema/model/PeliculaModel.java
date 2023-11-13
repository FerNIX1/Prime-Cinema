package com.udb.primecinema.model;

import com.udb.primecinema.beans.PeliculaBeans;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PeliculaModel extends Conexion{
    public List<PeliculaBeans> ListarPeliculas()throws SQLException {
        try{
            List<PeliculaBeans> lista = new ArrayList<>();
            String Sql="SELECT * FROM peliculas;";
            this.conectar();
            st=conexion.prepareStatement(Sql);
            rs= st.executeQuery();
            while (rs.next()) {
                PeliculaBeans listar = new PeliculaBeans();
                listar.setID_pelicula(rs.getInt("ID_pelicula"));
                listar.setNombre(rs.getString("Nombre"));
                listar.setID_genero(rs.getInt("ID_genero"));
                listar.setID_clasificacion(rs.getInt("ID_clasificacion"));
                listar.setID_formato(rs.getInt("ID_formato"));
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
    public void agregarPelicula(PeliculaBeans nuevoPelicula) throws SQLException {
        try {
            // Sentencia SQL para insertar datos en la tabla de películas (peliculas)
            String sql = "INSERT INTO peliculas (ID_pelicula, Nombre, ID_genero, ID_clasificacion, ID_formato) VALUES (?, ?, ?, ?, ?)";

            this.conectar(); // Conexión a la base de datos

            // Establecer los valores en la sentencia SQL
            st = conexion.prepareStatement(sql);
            st.setInt(1, nuevoPelicula.getID_pelicula()); // ID_pelicula es de tipo int en la base de datos
            st.setString(2, nuevoPelicula.getNombre()); // Nombre es de tipo varchar
            st.setInt(3, nuevoPelicula.getID_genero()); // ID_genero es de tipo int en la base de datos
            st.setInt(4, nuevoPelicula.getID_clasificacion()); // ID_clasificacion es de tipo int en la base de datos
            st.setInt(5, nuevoPelicula.getID_formato()); // ID_formato es de tipo int en la base de datos

            // Ejecución de la sentencia SQL para insertar los datos en la base de datos
            st.executeUpdate();

            this.desconectar(); // Desconexión de la base de datos
        } catch (SQLException e) {
            Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, e);
            this.desconectar();
        }
    }
    public PeliculaBeans obtenerPeliculaPorId(int peliId) throws SQLException {
        try {
            PeliculaBeans pelicula = null;
            String sql = "SELECT * FROM peliculas WHERE ID_pelicula = ?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, peliId);
            rs = st.executeQuery();
            if (rs.next()) {
                pelicula = new PeliculaBeans();
                pelicula.setID_pelicula(rs.getInt("ID_pelicula"));
                pelicula.setNombre(rs.getString("Nombre"));
                pelicula.setID_genero(rs.getInt("ID_genero"));
                pelicula.setID_clasificacion(rs.getInt("ID_clasificacion"));
                pelicula.setID_formato(rs.getInt("ID_formato"));
            }
            return pelicula;
        } finally {
            this.desconectar();
        }
    }
    public void actualizarPeli(PeliculaBeans pelicula) throws SQLException {
        try {
            String sql = "UPDATE peliculas SET ID_pelicula=?, Nombre=?, ID_genero=?, ID_clasificacion=?, ID_formato=? WHERE ID_pelicula=?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, String.valueOf(pelicula.getID_pelicula()));
            st.setString(2, pelicula.getNombre());
            st.setString(3, String.valueOf(pelicula.getID_genero()));
            st.setString(4, String.valueOf(pelicula.getID_clasificacion()));
            st.setString(5, String.valueOf(pelicula.getID_formato()));  // Asegúrate de que este sea el índice correcto
            st.setString(6, String.valueOf(pelicula.getID_pelicula()));  // Este es el índice 6
            st.executeUpdate();
        } finally {
            this.desconectar();
        }
    }
    public void eliminarPeli(int idUsuario) throws SQLException {
        try {
            String sql = "DELETE FROM peliculas WHERE ID_pelicula=?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, idUsuario);
            st.executeUpdate();
        } finally {
            this.desconectar();
        }
    }
}
