package com.example.restservice.repositories;

import java.util.List;

import com.example.restservice.models.Tarea;

public interface TareaRepository {
    public int countTarea();

    public int biggestIdTarea();

    public List<Tarea> getAllTarea();

    public Tarea getTareaById(Integer id);

    public List<Tarea> getTareaById_Emergencia(Integer id_Emergencia);

    public Tarea createTarea(Tarea tarea);

    public void updateTarea(int id_Tarea, Tarea tarea);

    public void deleteTarea(int id_Tarea, Tarea tarea);
}