package org.example.services;

import org.example.repositories.AttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AttributeServiceImpl implements AttributeService {

    private AttributeRepository attributeRepository;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public void setAttributeRepository(AttributeRepository attributeRepository) {
        this.attributeRepository = attributeRepository;
    }
}
