package com.example.restservice.repositories;

import java.util.List;

import com.example.restservice.models.Habilidad;

public interface HabilidadRepository {
    public int countHabilidad();

    public List<Habilidad> getAllHabilidad();

    public Habilidad getHabilidadById(Integer id);

    public Habilidad createHabilidad(Habilidad habilidad);

    public void updateHabilidad(int id_Habilidad, Habilidad habilidad);

    public void deleteHabilidad(int id_Habilidad, Habilidad habilidad);
}