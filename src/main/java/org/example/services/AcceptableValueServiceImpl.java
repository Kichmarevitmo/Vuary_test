package org.example.services;

import org.example.entities.Value;
import org.example.repositories.AcceptableValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcceptableValueServiceImpl implements AcceptableValueService {

    private AcceptableValueRepository acceptableValueRepository;

    @Autowired
    public void setAcceptableValueRepository(AcceptableValueRepository acceptableValueRepository) {
        this.acceptableValueRepository = acceptableValueRepository;
    }

    @Override
    public void addAll(List<Value> values) {
        if(!values.isEmpty()){
            acceptableValueRepository.saveAllAndFlush(values);
        }
    }
}
