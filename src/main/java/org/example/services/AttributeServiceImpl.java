package org.example.services;

import org.example.entities.Attribute;
import org.example.repositories.AttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttributeServiceImpl implements AttributeService {

    private AttributeRepository attributeRepository;

    @Autowired
    public void setAttributeRepository(AttributeRepository attributeRepository) {
        this.attributeRepository = attributeRepository;
    }

    @Override
    public void addAll(List<Attribute> attributes) {
        if(!attributes.isEmpty()){
            attributeRepository.saveAllAndFlush(attributes);
        }
    }
}
