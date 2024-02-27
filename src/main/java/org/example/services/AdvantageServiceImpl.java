package org.example.services;

import org.example.entities.Advantage;
import org.example.repositories.AdvantageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvantageServiceImpl implements AdvantageService {

    private AdvantageRepository advantageRepository;

    @Autowired
    public void setAdvantageRepository(AdvantageRepository advantageRepository) {
        this.advantageRepository = advantageRepository;
    }

    @Override
    public void addAll(List<Advantage> advantages) {
        if(!advantages.isEmpty()){
            advantageRepository.saveAllAndFlush(advantages);
        }
    }
}
