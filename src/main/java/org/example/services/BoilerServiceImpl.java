package org.example.services;

import org.example.repositories.BoilerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class BoilerServiceImpl implements BoilerService {

    private BoilerRepository boilerRepository;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public void setBoilerRepository(BoilerRepository boilerRepository) {
        this.boilerRepository = boilerRepository;
    }
}
