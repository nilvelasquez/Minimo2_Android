package com.example.juegodsarest3.models;

public class Objeto {

    private String nombre;
    private String descripcion;
    private double precio;

    private String fotoimagen;

    public String getFotoimagen() {
        return fotoimagen;
    }

    public void setFotoimagen(String fotoImagen) {
        this.fotoimagen = fotoImagen;
    }

    public Objeto(){}
    public Objeto(String nombre, String descripcion, double precio,String fotoimagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fotoimagen=fotoimagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
