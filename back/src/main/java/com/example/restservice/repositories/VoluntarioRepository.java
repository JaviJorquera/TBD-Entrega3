package com.example.restservice.repositories;

import java.util.List;

import com.example.restservice.models.Voluntario;

public interface VoluntarioRepository {
    public int countVoluntario();

    public List<Voluntario> getAllVoluntario();

    public Voluntario getVoluntarioById(Integer id_Voluntario);

    public List<Voluntario> getVoluntariosByID_Emergencia(Integer id_Emergencia);

    public List<Voluntario> getVoluntarioById_Tarea(Integer id_Tarea);

    public Voluntario createVoluntario(Voluntario voluntario);

    public void updateVoluntario(int id_Voluntario, Voluntario voluntario);

    public void deleteVoluntario(int id_Voluntario, Voluntario voluntario);
}