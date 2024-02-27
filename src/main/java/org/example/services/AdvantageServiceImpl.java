package org.example.services;

import org.example.repositories.AdvantageRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AdvantageServiceImpl implements AdvantageService {

    private AdvantageRepository advantageRepository;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public void setAdvantageRepository(AdvantageRepository advantageRepository) {
        this.advantageRepository = advantageRepository;
    }
}
