package com.example.restservice.models;

import java.lang.String;

public class Voluntario {

    // Parametros.
    private Integer id_Voluntario;
    private String nombreVoluntario;
    private String flg_participa;
    private Double latitud;
    private Double longitud;
    private Integer borrado;
    private Integer id_Tarea;

    // Getters y Setters.
    public Integer getId_Voluntario() {
        return id_Voluntario;
    }

    public void setId_Voluntario(Integer id_Voluntario) {
        this.id_Voluntario = id_Voluntario;
    }

    public String getNombreVoluntario() {
        return nombreVoluntario;
    }

    public void setNombreVoluntario(String nombreVoluntario) {
        this.nombreVoluntario = nombreVoluntario;
    }

    public String getFlg_participa() {
        return flg_participa;
    }

    public void setFlg_participa(String flg_participa) {
        this.flg_participa = flg_participa;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Integer getBorrado() {
        return borrado;
    }

    public void setBorrado(Integer borrado) {
        this.borrado = borrado;
    }

    public Integer getId_Tarea() {
        return id_Tarea;
    }

    public void setId_Tarea(Integer id_Tarea) {
        this.id_Tarea = id_Tarea;
    }
}