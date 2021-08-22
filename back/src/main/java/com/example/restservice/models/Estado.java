package com.example.restservice.models;

import java.lang.String;

public class Estado {

    // Parametros.
    public Integer id_Estado;
    public String nombreEstado;
    private Integer borrado;

    // Getters y Setters.
    public Integer getId_Estado() {
        return id_Estado;
    }

    public void setId_Estado(Integer id_Estado) {
        this.id_Estado = id_Estado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public Integer getBorrado() {
        return borrado;
    }

    public void setBorrado(Integer borrado) {
        this.borrado = borrado;
    }
}
