package com.example.restservice.services;

import com.example.restservice.models.Institucion;
import com.example.restservice.repositories.InstitucionRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "instituciones")
public class InstitucionService {

    private final InstitucionRepository institucionRepository;

    InstitucionService(InstitucionRepository institucionRepository) {
        this.institucionRepository = institucionRepository;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Institucion> getAllInstitucion() {
        return institucionRepository.getAllInstitucion();
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ResponseBody
    public String countInstitucion() {
        int total = institucionRepository.countInstitucion();
        return String.format("Tienes %s instituciones!!", total);
    }

    @RequestMapping(value = "/getById/{id_Institucion}", method = RequestMethod.GET)
    @ResponseBody
    public Institucion getInstitucionById(@PathVariable(value = "id_Institucion") Integer id) {
        return this.institucionRepository.getInstitucionById(id);
    }

    @PostMapping("/newInstitucion")
    @ResponseBody
    public Institucion createInstitucion(@RequestBody Institucion institucion) {
        Institucion result = institucionRepository.createInstitucion(institucion);
        return result;
    }

    @PutMapping("/update/{id_Institucion}")
    @ResponseBody
    public void updateInstitucion(@PathVariable(value = "id_Institucion") int id, Institucion institucion) {
        institucionRepository.updateInstitucion(id, institucion);
    }

    @PutMapping("/delete/{id_Institucion}")
    @ResponseBody
    public void deleteInstitucion(@PathVariable(value = "id_Institucion") int id, Institucion institucion){
        institucionRepository.deleteInstitucion(id, institucion);
    }
}