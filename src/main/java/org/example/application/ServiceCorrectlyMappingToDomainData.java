package org.example.application;

import org.example.domain.DomainData;
import org.example.persistence.EntityData;
import org.example.persistence.EntityJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceCorrectlyMappingToDomainData {

    @Autowired
    private EntityJpaRepository jpaRepository;

    public Optional<DomainData> findById(String id) {
        Optional<EntityData> optionalEntity = jpaRepository.findById(id);
        if (optionalEntity.isEmpty()) {
            return Optional.empty();
        }
        else {
            EntityData entity = optionalEntity.get();

            // map to domain class and return
            DomainData domainData = new DomainData(entity.counter, entity.text);
            return Optional.of( domainData );
        }
    }
}
