package com.example.restservice.repositories;

import java.util.List;

import com.example.restservice.models.Institucion;

public interface InstitucionRepository {
    public int countInstitucion();

    public List<Institucion> getAllInstitucion();

    public Institucion getInstitucionById(Integer id);

    public Institucion createInstitucion(Institucion institucion);

    public void updateInstitucion(int id_Institucion, Institucion institucion);

    public void deleteInstitucion(int id_Institucion, Institucion institucion);
}