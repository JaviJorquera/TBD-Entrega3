package com.example.restservice.services;

import com.example.restservice.models.Eme_Habilidad;
import com.example.restservice.repositories.Eme_HabilidadRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "eme_habilidades")
public class Eme_HabilidadService {

    private final Eme_HabilidadRepository eme_HabilidadRepository;

    Eme_HabilidadService(Eme_HabilidadRepository eme_HabilidadRepository) {
        this.eme_HabilidadRepository = eme_HabilidadRepository;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Eme_Habilidad> getAllEme_Habilidad() {
        return eme_HabilidadRepository.getAllEme_Habilidad();
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ResponseBody
    public String countEme_Habilidad() {
        int total = eme_HabilidadRepository.countEme_Habilidad();
        return String.format("Tienes %s emergencia_habilidad!!", total);
    }

    @RequestMapping(value = "/getById/{id_Eme_Habilidad}", method = RequestMethod.GET)
    @ResponseBody
    public Eme_Habilidad getEme_HabilidadById(@PathVariable(value = "id_Eme_Habilidad") Integer id) {
        return this.eme_HabilidadRepository.getEme_HabilidadById(id);
    }

    @PostMapping("/newEmergencia_Habilidad")
    @ResponseBody
    public Eme_Habilidad createEme_Habilidad(@RequestBody Eme_Habilidad eme_Habilidad) {
        Eme_Habilidad result = eme_HabilidadRepository.createEme_Habilidad(eme_Habilidad);
        return result;
    }

    @PutMapping("/update/{id_Eme_Habilidad}")
    @ResponseBody
    public void updateEmergencia(@PathVariable(value = "id_Eme_Habilidad") int id, Eme_Habilidad eme_Habilidad) {
        eme_HabilidadRepository.updateEme_Habilidad(id, eme_Habilidad);
    }

    @PutMapping("/delete/{id_Eme_Habilidad}")
    @ResponseBody
    public void deleteEme_Habilidad(@PathVariable(value = "id_Eme_Habilidad") int id, Eme_Habilidad eme_Habilidad){
        eme_HabilidadRepository.deleteEme_Habilidad(id, eme_Habilidad);
    }
}