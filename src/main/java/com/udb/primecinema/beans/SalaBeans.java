package com.udb.primecinema.beans;

public class SalaBeans {
private int ID_sala;
private String nombre;
private int ID_sucursal;

    public SalaBeans(int ID_sala, String nombre, int ID_sucursal) {
        this.ID_sala = ID_sala;
        this.nombre = nombre;
        this.ID_sucursal = ID_sucursal;
    }

    public SalaBeans() {
    }

    public int getID_sala() {
        return ID_sala;
    }

    public void setID_sala(int ID_sala) {
        this.ID_sala = ID_sala;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getID_sucursal() {
        return ID_sucursal;
    }

    public void setID_sucursal(int ID_sucursal) {
        this.ID_sucursal = ID_sucursal;
    }
}
