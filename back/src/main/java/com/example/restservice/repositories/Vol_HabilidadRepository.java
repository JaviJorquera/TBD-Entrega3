package com.example.restservice.repositories;

import java.util.List;

import com.example.restservice.models.Vol_Habilidad;

public interface Vol_HabilidadRepository {
    public int countVol_Habilidad();

    public List<Vol_Habilidad> getAllVol_Habilidad();

    public Vol_Habilidad getVol_HabilidadById(Integer id);

    public Vol_Habilidad createVol_Habilidad(Vol_Habilidad vol_Habilidad);

    public void updateVol_Habilidad(int id_Vol_Habilidad, Vol_Habilidad vol_Habilidad);

    public void deleteVol_Habilidad(int id_Vol_Habilidad, Vol_Habilidad vol_Habilidad);
}