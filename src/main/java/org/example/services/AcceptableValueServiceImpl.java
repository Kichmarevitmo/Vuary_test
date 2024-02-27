package org.example.services;

import org.example.repositories.AcceptableValueRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AcceptableValueServiceImpl implements AcceptableValueService {

    private AcceptableValueRepository acceptableValueRepository;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public void setAcceptableValueRepository(AcceptableValueRepository acceptableValueRepository) {
        this.acceptableValueRepository = acceptableValueRepository;
    }
}
