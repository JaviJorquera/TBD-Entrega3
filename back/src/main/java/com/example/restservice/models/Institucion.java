package com.example.restservice.models;

import java.lang.String;

public class Institucion {

    // Parametros.
    private Integer id_Institucion;
    private String nombreInstitucion;
    private String coordinador;
    private Integer borrado;

    // Getters y Setters.
    public Integer getId_Institucion() {
        return id_Institucion;
    }

    public void setId_Institucion(Integer id_Institucion) {
        this.id_Institucion = id_Institucion;
    }

    public String getNombreInstitucion() {
        return nombreInstitucion;
    }

    public void setNombreInstitucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
    }

    public String getCoordinador() {
        return coordinador;
    }

    public void setCoordinador(String coordinador) {
        this.coordinador = coordinador;
    }

    public Integer getBorrado() {
        return borrado;
    }

    public void setBorrado(Integer borrado) {
        this.borrado = borrado;
    }
}
