package org.example.services;

import org.example.repositories.AcceptableValueBoilerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AcceptableValueBoilerServiceImpl implements AcceptableValueBoilerService {

    private AcceptableValueBoilerRepository acceptableValueBoilerRepository;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public void setAcceptableValueBoilerRepository(AcceptableValueBoilerRepository acceptableValueBoilerRepository) {
        this.acceptableValueBoilerRepository = acceptableValueBoilerRepository;
    }
}
