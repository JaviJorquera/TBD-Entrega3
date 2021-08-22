package com.example.restservice.repositories;

import java.util.List;

import com.example.restservice.models.Estado;

public interface EstadoRepository {
    public int countEstados();

    public List<Estado> getAllEstados();

    public Estado getEstadoById(Integer id);

    public Estado createEstado(Estado estado);

    public void updateEstado(int id_Estado, Estado estado);

    public void deleteEstado(int id_Estado, Estado estado);
}