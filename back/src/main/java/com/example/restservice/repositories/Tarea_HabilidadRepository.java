package com.example.restservice.repositories;

import java.util.List;

import com.example.restservice.models.Tarea_Habilidad;

public interface Tarea_HabilidadRepository {
    public int countTarea_Habilidad();

    public List<Tarea_Habilidad> getAllTarea_Habilidad();

    public Tarea_Habilidad getTarea_HabilidadById(Integer id);

    public Tarea_Habilidad createTarea_Habilidad(Tarea_Habilidad tarea_Habilidad);

    public void updateTarea_Habilidad(int id_Tarea_Habilidad, Tarea_Habilidad tarea_Habilidad);

    public void deleteTarea_Habilidad(int id_Tarea_Habilidad, Tarea_Habilidad tarea_Habilidad);
}