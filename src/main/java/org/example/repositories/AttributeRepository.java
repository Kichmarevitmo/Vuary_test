package org.example.repositories;

import org.example.entities.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeRepository  extends JpaRepository<Attribute, Long> {
}
