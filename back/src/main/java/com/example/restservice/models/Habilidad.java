package com.example.restservice.models;

import java.lang.String;

public class Habilidad {

    // Parametros.
    public Integer id_Habilidad;
    private String nombreHabilidad;
    private String Tipo;
    private Integer borrado;

    // Getters y Setters.
    public Integer getId_Habilidad() {
        return id_Habilidad;
    }

    public void setId_Habilidad(Integer id_Habilidad) {
        this.id_Habilidad = id_Habilidad;
    }

    public String getNombreHabilidad() {
        return nombreHabilidad;
    }

    public void setNombreHabilidad(String nombreHabilidad) {
        this.nombreHabilidad = nombreHabilidad;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public Integer getBorrado() {
        return borrado;
    }

    public void setBorrado(Integer borrado) {
        this.borrado = borrado;
    }
}