package com.example.juegodsarest3.models;

public class Mapa {

    int id;
    String nombremapa;
    String mapatxt;

    public Mapa(){}

    public Mapa(String nombremapa, String mapatxt){
        this.nombremapa=nombremapa;
        this.mapatxt=mapatxt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombremapa() {
        return nombremapa;
    }

    public void setNombremapa(String nombremapa) {
        this.nombremapa = nombremapa;
    }

    public String getMapatxt() {
        return mapatxt;
    }

    public void setMapatxt(String mapatxt) {
        this.mapatxt = mapatxt;
    }
}

