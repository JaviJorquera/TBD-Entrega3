package com.example.restservice.models;

public class Vol_Habilidad {

    // Parametros.
    public Integer id_Vol_Habilidad;
    public Integer id_Voluntario;
    public Integer id_Habilidad;
    private Integer borrado;

    // Getters y Setters.
    public Integer getId_Vol_Habilidad() {
        return id_Vol_Habilidad;
    }

    public void setId_Vol_Habilidad(Integer id_Vol_Habilidad) {
        this.id_Vol_Habilidad = id_Vol_Habilidad;
    }

    public Integer getId_Voluntario() {
        return id_Voluntario;
    }

    public void setId_Voluntario(Integer id_Voluntario) {
        this.id_Voluntario = id_Voluntario;
    }

    public Integer getId_Habilidad() {
        return id_Habilidad;
    }

    public void setId_Habilidad(Integer id_Habilidad) {
        this.id_Habilidad = id_Habilidad;
    }

    public Integer getBorrado() {
        return borrado;
    }

    public void setBorrado(Integer borrado) {
        this.borrado = borrado;
    }
}