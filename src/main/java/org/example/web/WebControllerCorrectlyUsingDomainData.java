package org.example.web;

import org.example.application.ServiceCorrectlyMappingToDomainData;
import org.example.domain.DomainData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Controller
public class WebControllerCorrectlyUsingDomainData {

    @Autowired
    private ServiceCorrectlyMappingToDomainData service;

    @GetMapping(value="/data/{id}")
    public String dataDetails(@PathVariable("id") String id, Model model) {
        Optional<DomainData> data = service.findById(id);

        if (data.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "data " + id + " not available");
        }
        else {
            model.addAttribute("dataDetails", data);

            return "dataDetails"; // direct to Thymeleaf template
        }
    }
}
