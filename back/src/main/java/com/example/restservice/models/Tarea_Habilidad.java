package com.example.restservice.models;

public class Tarea_Habilidad {

    // Parametros.
    public Integer id_Tarea_Habilidad;
    public Integer id_Habilidad;
    public Integer id_Tarea;
    private Integer borrado;

    // Getters y Setters.
    public Integer getId_Tarea_Habilidad() {
        return id_Tarea_Habilidad;
    }

    public void setId_Tarea_Habilidad(Integer id_Tarea_Habilidad) {
        this.id_Tarea_Habilidad = id_Tarea_Habilidad;
    }

    public Integer getId_Habilidad() {
        return id_Habilidad;
    }

    public void setId_Habilidad(Integer id_Habilidad) {
        this.id_Habilidad = id_Habilidad;
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