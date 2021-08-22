package com.example.restservice.services;

import com.example.restservice.models.Vol_Habilidad;
import com.example.restservice.repositories.Vol_HabilidadRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "vol_habilidades")
public class Vol_HabilidadService {

    private final Vol_HabilidadRepository vol_HabilidadRepository;

    Vol_HabilidadService(Vol_HabilidadRepository vol_HabilidadRepository) {
        this.vol_HabilidadRepository = vol_HabilidadRepository;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Vol_Habilidad> getAllVol_Habilidad() {
        return vol_HabilidadRepository.getAllVol_Habilidad();
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ResponseBody
    public String countVol_Habilidad() {
        int total = vol_HabilidadRepository.countVol_Habilidad();
        return String.format("Tienes %s vol_habilidades!!", total);
    }

    @RequestMapping(value = "/getById/{id_Vol_Habilidad}", method = RequestMethod.GET)
    @ResponseBody
    public Vol_Habilidad getVol_HabilidadById(@PathVariable(value = "id_Vol_Habilidad") Integer id) {
        return this.vol_HabilidadRepository.getVol_HabilidadById(id);
    }

    @PostMapping("/newVol_Habilidad")
    @ResponseBody
    public Vol_Habilidad createVol_Habilidad(@RequestBody Vol_Habilidad vol_Habilidad) {
        Vol_Habilidad result = vol_HabilidadRepository.createVol_Habilidad(vol_Habilidad);
        return result;
    }

    @PutMapping("/update/{id_Vol_Habilidad}")
    @ResponseBody
    public void updateVol_Habilidad(@PathVariable(value = "id_Vol_Habilidad") int id, Vol_Habilidad vol_Habilidad) {
        vol_HabilidadRepository.updateVol_Habilidad(id, vol_Habilidad);
    }

    @PutMapping("/delete/{id_Vol_Habilidad}")
    @ResponseBody
    public void deleteVol_Habilidad(@PathVariable(value = "id_Vol_Habilidad") int id, Vol_Habilidad vol_Habilidad){
        vol_HabilidadRepository.deleteVol_Habilidad(id, vol_Habilidad);
    }
}