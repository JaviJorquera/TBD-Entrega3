package com.example.restservice.repositories;

import java.util.ArrayList;
import java.util.Arrays;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.stereotype.Repository;

@Repository
public class EmergenciaMongoDBRepositoryImp implements EmergenciaMongoDBRepository {

    @Autowired
    MongoDatabase database;

    @Override
    // tareas -> emergencias
    // voluntarios -> tareas

    public ArrayList<Document> getTareaActivaEmergencias( Integer iD ){
        
        MongoCollection<Document> collection2 = database.getCollection("Emergencias");
        AggregateIterable<Document> algo = collection2.aggregate( Arrays.asList(new Document("$match", new Document("ID_Emergencia", iD)),
                                                                                new Document("$lookup", new Document("from", "Tareas").append("localField", "ID_Emergencia").append("foreignField", "ID_Emergencia").append("as", "TareasDeUnaEmergencia")),
                                                                                new Document("$match", new Document("TareasDeUnaEmergencia.ID_Estado", 1L))));
        
        ArrayList<Document> result = new ArrayList<>();
        for (Document document : algo) {
            
            result.add(document);
        }
        return result;
    }
}