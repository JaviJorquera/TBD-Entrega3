package com.example.restservice.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Emergencias")
public class EmergenciaMongoDB {

    // Parametros.
    private Integer id_Emergencia;
    private String nombreEmergencia;
    private String requerimientos;
    private Double latitud_Eme;
    private Double longitud_Eme;
    private Integer id_Institucion;
    private Integer borrado;
    private Integer id_Estado;

    // Getters y Setters.
    public Integer getId_Emergencia() {
        return id_Emergencia;
    }

    public void setId_Emergencia(Integer id_Emergencia) {
        this.id_Emergencia = id_Emergencia;
    }

    public String getNombreEmergencia() {
        return nombreEmergencia;
    }

    public void setNombreEmergencia(String nombreEmergencia) {
        this.nombreEmergencia = nombreEmergencia;
    }

    public String getRequerimientos() {
        return requerimientos;
    }

    public void setRequerimientos(String requerimientos) {
        this.requerimientos = requerimientos;
    }

    public Double getLongitud_Eme() {
        return longitud_Eme;
    }

    public void setLongitud_Eme(Double longitud_Eme) {
        this.longitud_Eme = longitud_Eme;
    }

    public Double getLatitud_Eme() {
        return latitud_Eme;
    }

    public void setLatitud_Eme(Double latitud_Eme) {
        this.latitud_Eme = latitud_Eme;
    }

    public Integer getId_Institucion() {
        return id_Institucion;
    }

    public void setId_Institucion(Integer id_Institucion) {
        this.id_Institucion = id_Institucion;
    }

    public Integer getBorrado() {
        return borrado;
    }

    public void setBorrado(Integer borrado) {
        this.borrado = borrado;
    }

    public Integer getId_Estado() {
        return id_Estado;
    }

    public void setId_Estado(Integer id_Estado) {
        this.id_Estado = id_Estado;
    }
}