package com.udb.primecinema.beans;

public class ReporteSucursal {

    private String NombreSucursal;
    private int Entradas;
    public ReporteSucursal() {
    }
    public ReporteSucursal(String nombre, int entradas) {
        NombreSucursal = nombre;
        Entradas = entradas;
    }

    public String getNombreSucursal() {
        return NombreSucursal;
    }

    public void setNombreSucursal(String nombre) {
        NombreSucursal = nombre;
    }

    public int getEntradas() {
        return Entradas;
    }

    public void setEntradas(int entradas) {
        Entradas = entradas;
    }
}
