package com.example.juegodsarest3.models;

public class Ranking {

    String nickname;
    String fecha;
    double puntos;

    String avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Ranking(){


    }

    public Ranking(String nickname,String fecha, double puntos,String avatar){
        setNickname(nickname);
        setFecha(fecha);
        setPuntos(puntos);
        setAvatar(avatar);
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getPuntos() {
        return puntos;
    }

    public void setPuntos(double puntos) {
        this.puntos = puntos;
    }
}
