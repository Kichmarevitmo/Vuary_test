package org.example.services;

import org.example.entities.Boiler;
import org.example.repositories.BoilerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoilerServiceImpl implements BoilerService {

    private BoilerRepository boilerRepository;

    @Autowired
    public void setBoilerRepository(BoilerRepository boilerRepository) {
        this.boilerRepository = boilerRepository;
    }

    @Override
    public void addAll(List<Boiler> boilers) {
        if(!boilers.isEmpty()){
            boilerRepository.saveAllAndFlush(boilers);
        }
    }
}
