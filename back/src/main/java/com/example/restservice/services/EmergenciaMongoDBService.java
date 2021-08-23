package com.example.restservice.services;

import java.util.ArrayList;

import org.bson.Document;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.repositories.EmergenciaMongoDBRepository;

@RestController
public class EmergenciaMongoDBService {

    private final EmergenciaMongoDBRepository emergenciaMongoDBRepository;
    EmergenciaMongoDBService(EmergenciaMongoDBRepository emergenciaMongoDBRepository){
        this.emergenciaMongoDBRepository = emergenciaMongoDBRepository;
    }

    @RequestMapping( value = "/TareaAggregate/{iD_Emergencia}", method = RequestMethod.GET )
    @ResponseBody
    public ArrayList<Document> getTareaActivaEmergencias(@PathVariable(value = "iD_Emergencia") Integer iD){
        return emergenciaMongoDBRepository.getTareaActivaEmergencias(iD);
    }
    
    
}