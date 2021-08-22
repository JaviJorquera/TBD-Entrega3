package com.example.restservice.models;

public class Eme_Habilidad {

    // Parametros.
    public Integer id_Eme_Habilidad;
    public Integer id_Habilidad;
    public Integer id_Emergencia;
    private Integer borrado;

    // Getters y Setters.
    public Integer getId_Eme_Habilidad() {
        return id_Eme_Habilidad;
    }

    public void setId_Eme_Habilidad(Integer id_Eme_Habilidad) {
        this.id_Eme_Habilidad = id_Eme_Habilidad;
    }

    public Integer getid_Habilidad() {
        return id_Habilidad;
    }

    public void setid_Habilidad(Integer id_Habilidad) {
        this.id_Habilidad = id_Habilidad;
    }

    public Integer getId_Emergencia() {
        return id_Emergencia;
    }

    public void setId_Emergencia(Integer id_Emergencia) {
        this.id_Emergencia = id_Emergencia;
    }

    public Integer getBorrado() {
        return borrado;
    }

    public void setBorrado(Integer borrado) {
        this.borrado = borrado;
    }
}