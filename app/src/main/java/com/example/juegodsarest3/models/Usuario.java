package com.example.juegodsarest3.models;

public class Usuario {
    private String correo;
    private String password;
    private String nombre;

    private double dsacoins;

    public Usuario(){}

    public double getDsacoins() {
        return dsacoins;
    }

    public void setDsacoins(double dsaCoins) {
        this.dsacoins = dsaCoins;
    }

    public Usuario(String correo, String password, String nombre, double dsacoins) {
        this.correo = correo;
        this.password = password;
        this.nombre = nombre;
        this.dsacoins=dsacoins;

    }

    public String getCorreo() {
        return correo;
    }

    public String getPassword() {
        return password;
    }

    public String getNombre() {
        return nombre;
    }


}