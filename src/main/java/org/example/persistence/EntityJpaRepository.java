package org.example.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntityJpaRepository extends JpaRepository<EntityData, String> {
    @Override
    Optional<EntityData> findById(String id);
}
