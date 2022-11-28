package org.example.rest;

import org.example.application.ServiceCorrectlyMappingToDomainData;
import org.example.domain.DomainData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class RestControllerCorrectlyUsingDomainData {

    @Autowired
    private ServiceCorrectlyMappingToDomainData service;

    @GetMapping(value = "/rest/data/{id}", produces = "application/json")
    public DomainData findById(@PathVariable String id) {
        Optional<DomainData> data = service.findById(id);

        if (data.isEmpty()) {
            throw new IllegalStateException("data " + id + " not available");
        }
        else {
            return data.get(); // transformed to JSON automatically
        }
    }

}
