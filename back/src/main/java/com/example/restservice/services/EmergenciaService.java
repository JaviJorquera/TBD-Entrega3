package com.example.restservice.services;

import com.example.restservice.models.Emergencia;
import com.example.restservice.repositories.EmergenciaRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "emergencias")
public class EmergenciaService {

    private final EmergenciaRepository emergenciaRepository;

    EmergenciaService(EmergenciaRepository emergenciaRepository) {
        this.emergenciaRepository = emergenciaRepository;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Emergencia> getAllEmergencias() {
        return emergenciaRepository.getAllEmergencias();
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ResponseBody
    public String countEmergencias() {
        int total = emergenciaRepository.countEmergencias();
        return String.format("Tienes %s emergencias!!", total);
    }

    @RequestMapping(value = "/getById/{id_Emergencia}", method = RequestMethod.GET)
    @ResponseBody
    public Emergencia getEmergenciaById(@PathVariable(value = "id_Emergencia") Integer id) {
        return this.emergenciaRepository.getEmergenciaById(id);
    }

    @PostMapping("/newEmergencia")
    @ResponseBody
    public Emergencia createEmergencias(@RequestBody Emergencia emergencia) {
        Emergencia result = emergenciaRepository.createEmergencia(emergencia);
        return result;
    }

    @PutMapping("/update/{id_Emergencia}")
    @ResponseBody
    public void updateEmergencia(@PathVariable(value = "id_Emergencia") int id, Emergencia emergencia) {
        emergenciaRepository.updateEmergencia(id, emergencia);
    }

    @PutMapping("/delete/{id_Emergencia}")
    @ResponseBody
    public void deleteEmergencia(@PathVariable(value = "id_Emergencia") int id, Emergencia emergencia){
        emergenciaRepository.deleteEmergencia(id, emergencia);
    }
}