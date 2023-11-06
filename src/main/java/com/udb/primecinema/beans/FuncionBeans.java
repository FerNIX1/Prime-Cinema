package com.udb.primecinema.beans;

public class FuncionBeans {
    private  int ID_funcion;
    private  int ID_Sala;
    private  int ID_pelicula;
    private  double duracion;

    private String Nombrepelicula;

    private String NombreSala;

    private String NombreSucursal;

    public FuncionBeans() {
    }

    public FuncionBeans(int ID_funcion, int ID_Sala, int ID_pelicula, double duracion) {
        this.ID_funcion = ID_funcion;
        this.ID_Sala = ID_Sala;
        this.ID_pelicula = ID_pelicula;
        this.duracion = duracion;
    }

    public int getID_funcion() {
        return ID_funcion;
    }

    public void setID_funcion(int ID_funcion) {
        this.ID_funcion = ID_funcion;
    }

    public int getID_Sala() {
        return ID_Sala;
    }

    public void setID_Sala(int ID_Sala) {
        this.ID_Sala = ID_Sala;
    }

    public int getID_pelicula() {
        return ID_pelicula;
    }

    public void setID_pelicula(int ID_pelicula) {
        this.ID_pelicula = ID_pelicula;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public String getNombrepelicula() {
        return Nombrepelicula;
    }

    public void setNombrepelicula(String nombrepelicula) {
        Nombrepelicula = nombrepelicula;
    }

    public void setNombreSala(String nombreSala) {
        NombreSala = nombreSala;
    }

    public String getNombreSala() {
        return NombreSala;
    }

    public void setNombreSucursal(String nombreSucursal) {
        NombreSucursal=nombreSucursal;
    }

    public String getNombreSucursal() {
        return NombreSucursal;
    }
}
