package org.example.application;

import org.example.persistence.EntityData;
import org.example.persistence.EntityJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceIncorrectlyReturningPersistenceData {

    @Autowired
    private EntityJpaRepository jpaRepository;

    public Optional<EntityData> findById(String id) {
        return jpaRepository.findById(id);
    }
}
