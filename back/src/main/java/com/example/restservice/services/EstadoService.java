package com.example.restservice.services;

import com.example.restservice.models.Estado;
import com.example.restservice.repositories.EstadoRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "estados")
public class EstadoService {

    private final EstadoRepository estadoRepository;

    EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Estado> getAllEstados() {
        return estadoRepository.getAllEstados();
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ResponseBody
    public String countEstados() {
        int total = estadoRepository.countEstados();
        return String.format("Tienes %s estados!!", total);
    }

    @RequestMapping(value = "/getById/{id_Estado}", method = RequestMethod.GET)
    @ResponseBody
    public Estado getEstadoById(@PathVariable(value = "id_Estado") Integer id) {
        return this.estadoRepository.getEstadoById(id);
    }

    @PostMapping("/newEstado")
    @ResponseBody
    public Estado createEstado(@RequestBody Estado estado) {
        Estado result = estadoRepository.createEstado(estado);
        return result;
    }

    @PutMapping("/update/{id_Estado}")
    @ResponseBody
    public void updateEstado(@PathVariable(value = "id_Estado") int id, @RequestBody Estado estado) {
        estadoRepository.updateEstado(id, estado);
    }

    @PutMapping("/delete/{id_Estado}")
    @ResponseBody
    public void deleteEstado(@PathVariable(value = "id_Estado") int id, Estado estado){
        estadoRepository.deleteEstado(id, estado);
    }
}