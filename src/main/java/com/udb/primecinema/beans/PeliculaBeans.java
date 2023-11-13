package com.udb.primecinema.beans;

public class PeliculaBeans {
   private int ID_pelicula;
    private String Nombre;
    private int ID_genero;
    private int ID_clasificacion;
    private  int ID_formato;

    public PeliculaBeans(int ID_pelicula, String Nombre, int ID_genero, int ID_clasificacion, int ID_formato) {
        this.ID_pelicula = ID_pelicula;
        this.Nombre = Nombre;
        this.ID_genero = ID_genero;
        this.ID_clasificacion = ID_clasificacion;
        this.ID_formato = ID_formato;

    }
    public PeliculaBeans(){

    }
    public int getID_pelicula() {
        return this.ID_pelicula;
    }

    public void setID_pelicula(int ID_pelicula) {
        this.ID_pelicula = ID_pelicula;
    }

    public String getNombre() {
        return this.Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getID_genero() {
        return this.ID_genero;
    }

    public void setID_genero(int ID_genero) {
        this.ID_genero = ID_genero;
    }

    public int getID_clasificacion() {
        return this.ID_clasificacion;
    }

    public void setID_clasificacion(int ID_clasificacion) {
        this.ID_clasificacion = ID_clasificacion;
    }

    public int getID_formato() {
        return this.ID_formato;
    }

    public void setID_formato(int ID_formato) {
        this.ID_formato = ID_formato;
    }

}
