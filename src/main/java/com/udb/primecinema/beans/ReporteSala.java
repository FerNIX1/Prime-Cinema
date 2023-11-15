package com.udb.primecinema.beans;

public class ReporteSala {
    int ID_sala;
    String Nombre_Sala;
    String Nombre_Sucursal;
    double Ingreso;

    public ReporteSala() {
    }

    public ReporteSala(int ID_sala, String nombre_Sala, String nombre_Sucursal, double ingreso) {
        this.ID_sala = ID_sala;
        Nombre_Sala = nombre_Sala;
        Nombre_Sucursal = nombre_Sucursal;
        Ingreso = ingreso;
    }

    public int getID_sala() {
        return ID_sala;
    }

    public void setID_sala(int ID_sala) {
        this.ID_sala = ID_sala;
    }

    public String getNombre_Sala() {
        return Nombre_Sala;
    }

    public void setNombre_Sala(String nombre_Sala) {
        Nombre_Sala = nombre_Sala;
    }

    public String getNombre_Sucursal() {
        return Nombre_Sucursal;
    }

    public void setNombre_Sucursal(String nombre_Sucursal) {
        Nombre_Sucursal = nombre_Sucursal;
    }

    public double getIngreso() {
        return Ingreso;
    }

    public void setIngreso(double ingreso) {
        Ingreso = ingreso;
    }
}
