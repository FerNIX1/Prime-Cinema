package com.udb.primecinema.beans;

public class SucursalBeans {
    int ID_sucursal;
    String Nombre;
    String Gerente;
    String Direccion;
    String Telefono;

    public SucursalBeans(int ID_Sucursal, String nombre, String Gerente, String direccion, String telefono) {
        this.ID_sucursal = ID_Sucursal;
        this.Nombre = nombre;
        this.Gerente = Gerente;
        this.Direccion = direccion;
        this.Telefono = telefono;
    }

    public SucursalBeans() {
    }

    public int getID_sucursal() {
        return ID_sucursal;
    }

    public void setID_sucursal(int ID_sucursal) {
        this.ID_sucursal = ID_sucursal;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getGerente() {
        return Gerente;
    }

    public void setGerente(String gerente) {
        Gerente = gerente;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }
}
