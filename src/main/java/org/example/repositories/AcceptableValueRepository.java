package org.example.repositories;

import org.example.entities.Value;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcceptableValueRepository extends JpaRepository<Value, Long> {
}
