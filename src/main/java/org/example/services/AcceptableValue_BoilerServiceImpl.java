package org.example.services;

import org.example.repositories.AcceptableValue_BoilerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AcceptableValue_BoilerServiceImpl implements AcceptableValue_BoilerService {

    private AcceptableValue_BoilerRepository acceptableValueBoilerRepository;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public void setAcceptableValueBoilerRepository(AcceptableValue_BoilerRepository acceptableValueBoilerRepository) {
        this.acceptableValueBoilerRepository = acceptableValueBoilerRepository;
    }
}
