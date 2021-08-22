package com.example.restservice.models;

import java.lang.String;

public class Ranking {

    // Parametros.
    public Integer id_Ranking;
    private String nombreRanking;
    public Integer id_Voluntario;
    public Integer id_Tarea;
    private Integer borrado;

    // Getters y Setters.
    public Integer getId_Ranking() {
        return id_Ranking;
    }

    public void setId_Ranking(Integer id_Ranking) {
        this.id_Ranking = id_Ranking;
    }

    public String getNombreRanking() {
        return nombreRanking;
    }

    public void setNombreRanking(String nombreRanking) {
        this.nombreRanking = nombreRanking;
    }

    public Integer getId_Voluntario() {
        return id_Voluntario;
    }

    public void setId_Voluntario(Integer id_Voluntario) {
        this.id_Voluntario = id_Voluntario;
    }

    public Integer getId_Tarea() {
        return id_Tarea;
    }

    public void setId_Tarea(Integer id_Tarea) {
        this.id_Tarea = id_Tarea;
    }

    public Integer getBorrado() {
        return borrado;
    }

    public void setBorrado(Integer borrado) {
        this.borrado = borrado;
    }
}