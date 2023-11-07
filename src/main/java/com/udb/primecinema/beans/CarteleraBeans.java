package com.udb.primecinema.beans;

public class CarteleraBeans {
    private int ID_sala;
    private String pelicula;
    private Double duracion;
    private String clasificacion;
    private String genero;
    private String formato;

    public CarteleraBeans() {
    }

    public CarteleraBeans(int ID_sala, String pelicula, Double duracion, String clasificacion, String genero, String formato) {
        this.ID_sala = ID_sala;
        this.pelicula = pelicula;
        this.duracion = duracion;
        this.clasificacion = clasificacion;
        this.genero = genero;
        this.formato = formato;
    }

    public int getID_sala() {
        return ID_sala;
    }

    public void setID_sala(int ID_sala) {
        this.ID_sala = ID_sala;
    }

    public String getPelicula() {
        return pelicula;
    }

    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }

    public Double getDuracion() {
        return duracion;
    }

    public void setDuracion(Double duracion) {
        this.duracion = duracion;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }
}
