package com.example.restservice.repositories;

import java.util.ArrayList;
import org.bson.Document;

public interface TareaMongoDBRepository {
    public ArrayList<Document> getTareaActivaEmergencias(Integer iD);
}