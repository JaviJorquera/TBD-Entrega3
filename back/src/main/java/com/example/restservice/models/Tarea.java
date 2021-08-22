package com.example.restservice.models;

import java.lang.String;

public class Tarea {

    // Parametros.
    public Integer id_Tarea;
    private String nombreTarea;
    private String detalle;
    private Integer vol_requeridos;
    public Integer id_Estado;
    public Integer id_Emergencia;
    private Integer borrado;
    

    // Getters y Setters.
    public Integer getId_Tarea() {
        return id_Tarea;
    }

    public void setId_Tarea(Integer id_Tarea) {
        this.id_Tarea = id_Tarea;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Integer getId_Estado() {
        return id_Estado;
    }

    public void setId_Estado(Integer id_Estado) {
        this.id_Estado = id_Estado;
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

    public Integer getVol_requeridos() {
        return vol_requeridos;
    }

    public void setVol_requeridos(Integer vol_requeridos) {
        this.vol_requeridos = vol_requeridos;
    }
}