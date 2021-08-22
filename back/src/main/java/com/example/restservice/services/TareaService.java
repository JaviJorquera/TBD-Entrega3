package com.example.restservice.services;

import com.example.restservice.models.Tarea;
import com.example.restservice.repositories.TareaRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "tareas")
public class TareaService {

    private final TareaRepository tareaRepository;

    TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Tarea> getAllTarea() {
        return tareaRepository.getAllTarea();
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ResponseBody
    public String countTarea() {
        int total = tareaRepository.countTarea();
        return String.format("Tienes %s tareas!!", total);
    }

    @RequestMapping(value = "/maxID", method = RequestMethod.GET)
    @ResponseBody
    public int biggestIdTarea() {
        int total = tareaRepository.biggestIdTarea();
        return total;
    }

    @RequestMapping(value = "/getById/{id_Tarea}", method = RequestMethod.GET)
    @ResponseBody
    public Tarea getTareaById(@PathVariable(value = "id_Tarea") Integer id) {
        return this.tareaRepository.getTareaById(id);
    }

    @RequestMapping(value = "/getById_Emergencia/{id_Emergencia}", method = RequestMethod.GET)
    @ResponseBody
    public List<Tarea> getTareaById_Emergencia(@PathVariable(value = "id_Emergencia") Integer id_Emergencia) {
        return this.tareaRepository.getTareaById_Emergencia(id_Emergencia);
    }

    @PostMapping("/newTarea")
    @ResponseBody
    public Tarea createTarea(@RequestBody Tarea tarea) {
        Tarea result = tareaRepository.createTarea(tarea);
        return result;
    }

    @PutMapping("/update/{id_Tarea}")
    @ResponseBody
    public void updateTarea(@PathVariable(value = "id_Tarea") int id, @RequestBody Tarea tarea) {
        tareaRepository.updateTarea(id, tarea);
    }

    @PutMapping("/delete/{id_Tarea}")
    @ResponseBody
    public void deleteTarea(@PathVariable(value = "id_Tarea") int id, Tarea tarea) {
        tareaRepository.deleteTarea(id, tarea);
    }
}