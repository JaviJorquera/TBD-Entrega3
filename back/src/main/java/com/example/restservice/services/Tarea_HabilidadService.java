package com.example.restservice.services;

import com.example.restservice.models.Tarea_Habilidad;
import com.example.restservice.repositories.Tarea_HabilidadRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "tarea_habilidades")
public class Tarea_HabilidadService {

    private final Tarea_HabilidadRepository tarea_HabilidadRepository;

    Tarea_HabilidadService(Tarea_HabilidadRepository tarea_HabilidadRepository) {
        this.tarea_HabilidadRepository = tarea_HabilidadRepository;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Tarea_Habilidad> getAllTarea_Habilidad() {
        return tarea_HabilidadRepository.getAllTarea_Habilidad();
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ResponseBody
    public String countTarea_Habilidad() {
        int total = tarea_HabilidadRepository.countTarea_Habilidad();
        return String.format("Tienes %s tareas_habilidades!!", total);
    }

    @RequestMapping(value = "/getById/{id_Tarea_Habilidad}", method = RequestMethod.GET)
    @ResponseBody
    public Tarea_Habilidad getTarea_HabilidadById(@PathVariable(value = "id_Tarea_Habilidad") Integer id) {
        return this.tarea_HabilidadRepository.getTarea_HabilidadById(id);
    }

    @PostMapping("/newTarea_Habilidad")
    @ResponseBody
    public Tarea_Habilidad createTarea_Habilidad(@RequestBody Tarea_Habilidad tarea_Habilidad) {
        Tarea_Habilidad result = tarea_HabilidadRepository.createTarea_Habilidad(tarea_Habilidad);
        return result;
    }

    @PutMapping("/update/{id_Tarea_Habilidad}")
    @ResponseBody
    public void updateTarea_Habilidad(@PathVariable(value = "id_Tarea_Habilidad") int id,
            Tarea_Habilidad tarea_Habilidad) {
        tarea_HabilidadRepository.updateTarea_Habilidad(id, tarea_Habilidad);
    }

    @PutMapping("/delete/{id_Tarea_Habilidad}")
    @ResponseBody
    public void deleteTarea_Habilidad(@PathVariable(value = "id_Tarea_Habilidad") int id,
            Tarea_Habilidad tarea_Habilidad) {
        tarea_HabilidadRepository.deleteTarea_Habilidad(id, tarea_Habilidad);
    }
}