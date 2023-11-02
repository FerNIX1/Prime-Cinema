package com.udb.primecinema.beans;

public class UsuarioBeans {
    private int ID_Usuario;
    private String Nombre;
    private String Apellido;
    private int DUI;
    private String Direccion;
    private String Email;
    private String Telefono;

    public UsuarioBeans(int Id_Usuario, String nombre, String apellido, int Dui, String direccion, String email, String telefono) {
        ID_Usuario = Id_Usuario;
        Nombre = nombre;
        Apellido = apellido;
        DUI = Dui;
        Direccion = direccion;
        Email = email;
        Telefono = telefono;
    }

    public UsuarioBeans() {
    }

    public int getID_Usuario() {
        return ID_Usuario;
    }

    public void setID_Usuario(int ID_Usuario) {
        this.ID_Usuario = ID_Usuario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public int getDUI() {
        return DUI;
    }

    public void setDUI(int DUI) {
        this.DUI = DUI;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }
}
