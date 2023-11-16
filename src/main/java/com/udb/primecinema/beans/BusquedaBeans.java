package com.udb.primecinema.beans;

public class BusquedaBeans {
    private int id_Sala;
    private String nombre_Sala;
    private String nombre_Pelicula;
    private String nombre_Sucursal;

    public BusquedaBeans() {
    }

    public BusquedaBeans(int id_Sala, String nombre_Sala, String nombre_Pelicula) {
        this.id_Sala = id_Sala;
        this.nombre_Sala = nombre_Sala;
        this.nombre_Pelicula = nombre_Pelicula;
    }

    public int getId_Sala() {
        return id_Sala;
    }

    public void setId_Sala(int id_Sala) {
        this.id_Sala = id_Sala;
    }

    public String getNombre_Sala() {
        return nombre_Sala;
    }

    public void setNombre_Sala(String nombre_Sala) {
        this.nombre_Sala = nombre_Sala;
    }

    public String getNombre_Pelicula() {
        return nombre_Pelicula;
    }

    public void setNombre_Pelicula(String nombre_Pelicula) {
        this.nombre_Pelicula = nombre_Pelicula;
    }

    public String getNombre_Sucursal() {
        return nombre_Sucursal;
    }

    public void setNombre_Sucursal(String nombre_Sucursal) {
        this.nombre_Sucursal = nombre_Sucursal;
    }
}
