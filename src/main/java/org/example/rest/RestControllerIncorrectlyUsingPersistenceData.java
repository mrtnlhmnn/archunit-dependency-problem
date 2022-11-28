package org.example.rest;

import org.example.application.ServiceIncorrectlyReturningPersistenceData;
import org.example.persistence.EntityData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * This class is using EntityData from ...infrastructure.persistence
 * ArchUnit tests fails because of this class (which is expected and great)
 *
 * cf WebControllerIncorrectlyUsingPersistenceData
 */

@RestController
public class RestControllerIncorrectlyUsingPersistenceData {

    @Autowired
    private ServiceIncorrectlyReturningPersistenceData service;

    @GetMapping(value = "/rest/data/{id}", produces = "application/json")
    public EntityData findById(@PathVariable String id) {
        Optional<EntityData> entity = service.findById(id);

        if (entity.isEmpty()) {
            throw new IllegalStateException("nope");
        }
        else {
            return entity.get(); // transformed to JSON automatically
        }
    }

}
