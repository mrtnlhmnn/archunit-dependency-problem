package org.example.web;

import org.example.application.ServiceIncorrectlyReturningPersistenceData;
import org.example.persistence.EntityData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * This class is using EntityData from ...infrastructure.persistence
 * BUT: ArchUnit tests does NOT fail because of this class (which is unexpected) when line 36 is commented out
 */

@Controller
public class WebControllerIncorrectlyUsingPersistenceData {

    @Autowired
    private ServiceIncorrectlyReturningPersistenceData service;

    @GetMapping(value="/data/{id}")
    public String dataDetails(@PathVariable("id") String id, Model model) {
        Optional<EntityData> data = service.findById(id);

        if (data.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "data " + id + " not available");
        }
        else {
            // *** comment in the following line to see that than the dependency violation is indeed found and shown
            // int counter = data.get().counter;

            model.addAttribute("dataDetails", data.get());

            return "dataDetails"; // direct to Thymeleaf template
        }
    }
}
