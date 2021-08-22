package com.example.restservice.services;

import com.example.restservice.models.Habilidad;
import com.example.restservice.repositories.HabilidadRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "habilidades")
public class HabilidadService {

    private final HabilidadRepository habilidadRepository;

    HabilidadService(HabilidadRepository habilidadRepository) {
        this.habilidadRepository = habilidadRepository;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Habilidad> getAllHabilidad() {
        return habilidadRepository.getAllHabilidad();
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ResponseBody
    public String countHabilidad() {
        int total = habilidadRepository.countHabilidad();
        return String.format("Tienes %s Habilidades!!", total);
    }

    @RequestMapping(value = "/getById/{id_Habilidad}", method = RequestMethod.GET)
    @ResponseBody
    public Habilidad getHabilidadById(@PathVariable(value = "id_Habilidad") Integer id) {
        return this.habilidadRepository.getHabilidadById(id);
    }

    @PostMapping("/newHabilidad")
    @ResponseBody
    public Habilidad createHabilidad(@RequestBody Habilidad habilidad) {
        Habilidad result = habilidadRepository.createHabilidad(habilidad);
        return result;
    }

    @PutMapping("/update/{id_Habilidad}")
    @ResponseBody
    public void updateHabilidad(@PathVariable(value = "id_Habilidad") int id, Habilidad habilidad) {
        habilidadRepository.updateHabilidad(id, habilidad);
    }

    @PutMapping("/delete/{id_Habilidad}")
    @ResponseBody
    public void deleteHabilidad(@PathVariable(value = "id_Habilidad") int id, Habilidad habilidad){
        habilidadRepository.deleteHabilidad(id, habilidad);
    }
}