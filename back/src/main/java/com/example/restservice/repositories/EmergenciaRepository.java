package com.example.restservice.repositories;

import java.util.List;

import com.example.restservice.models.Emergencia;

public interface EmergenciaRepository {
    public int countEmergencias();

    public List<Emergencia> getAllEmergencias();

    public Emergencia getEmergenciaById(Integer id);

    public Emergencia getEmergenciaByPoint(Double latitud_Eme, Double longitud_Eme);

    public Emergencia createEmergencia(Emergencia emergencia);

    public void updateEmergencia(int id_Emergencia, Emergencia emergencia);

    public void deleteEmergencia(int id_Emergencia, Emergencia emergencia);
}