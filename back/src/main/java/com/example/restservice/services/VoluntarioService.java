package com.example.restservice.services;

import com.example.restservice.models.Voluntario;
import com.example.restservice.repositories.VoluntarioRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "voluntarios")
public class VoluntarioService {

    private final VoluntarioRepository voluntarioRepository;

    VoluntarioService(VoluntarioRepository voluntarioRepository) {
        this.voluntarioRepository = voluntarioRepository;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Voluntario> getAllVoluntario() {
        return voluntarioRepository.getAllVoluntario();
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ResponseBody
    public String countVoluntario() {
        int total = voluntarioRepository.countVoluntario();
        return String.format("Tienes %s voluntarios!!", total);
    }

    @RequestMapping(value = "/getById/{id_Voluntario}", method = RequestMethod.GET)
    @ResponseBody
    public Voluntario getVoluntarioById(@PathVariable(value = "id_Voluntario") Integer id) {
        return this.voluntarioRepository.getVoluntarioById(id);
    }

    @RequestMapping(value = "/getById_Tarea/{id_Tarea}", method = RequestMethod.GET)
    @ResponseBody
    public List<Voluntario> getVoluntarioById_Tarea(@PathVariable(value = "id_Tarea") Integer id_Tarea) {
        return this.voluntarioRepository.getVoluntarioById_Tarea(id_Tarea);
    }

    @RequestMapping(value = "/getVoluntariosByID_Emergencia/{id_Emergencia}", method = RequestMethod.GET)
    @ResponseBody
    public List<Voluntario> getVoluntariosByID_Emergencia(
            @PathVariable(value = "id_Emergencia") Integer id_Emergencia) {
        return this.voluntarioRepository.getVoluntariosByID_Emergencia(id_Emergencia);
    }

    @PostMapping("/newVoluntario")
    @ResponseBody
    public Voluntario createVoluntario(@RequestBody Voluntario voluntario) {
        Voluntario result = voluntarioRepository.createVoluntario(voluntario);
        return result;
    }

    @PutMapping("/update/{id_Voluntario}")
    @ResponseBody
    public void updateVoluntario(@PathVariable(value = "id_Voluntario") int id, Voluntario voluntario) {
        voluntarioRepository.updateVoluntario(id, voluntario);
    }

    @PutMapping("/delete/{id_Voluntario}")
    @ResponseBody
    public void deleteVoluntario(@PathVariable(value = "id_Voluntario") int id, Voluntario voluntario) {
        voluntarioRepository.deleteVoluntario(id, voluntario);
    }
}