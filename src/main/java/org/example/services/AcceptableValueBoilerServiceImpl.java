package org.example.services;

import org.example.entities.AcceptableValueBoiler;
import org.example.repositories.AcceptableValueBoilerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcceptableValueBoilerServiceImpl implements AcceptableValueBoilerService {

    private AcceptableValueBoilerRepository acceptableValueBoilerRepository;

    @Autowired
    public void setAcceptableValueBoilerRepository(AcceptableValueBoilerRepository acceptableValueBoilerRepository) {
        this.acceptableValueBoilerRepository = acceptableValueBoilerRepository;
    }

    @Override
    public void addAll(List<AcceptableValueBoiler> acceptableValueBoilers) {
        if(!acceptableValueBoilers.isEmpty()){
            acceptableValueBoilerRepository.saveAllAndFlush(acceptableValueBoilers);
        }
    }
}
