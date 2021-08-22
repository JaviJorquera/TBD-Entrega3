package com.example.restservice.repositories;

import com.example.restservice.models.Eme_Habilidad;
import java.util.List;

public interface Eme_HabilidadRepository {
    public int countEme_Habilidad();

    public List<Eme_Habilidad> getAllEme_Habilidad();

    public Eme_Habilidad getEme_HabilidadById(Integer id);

    public Eme_Habilidad createEme_Habilidad(Eme_Habilidad eme_Habilidad);

    public void updateEme_Habilidad(int id_Eme_Habilidad, Eme_Habilidad eme_Habilidad);
    
    public void deleteEme_Habilidad(int id_Eme_Habilidad, Eme_Habilidad eme_Habilidad);
}