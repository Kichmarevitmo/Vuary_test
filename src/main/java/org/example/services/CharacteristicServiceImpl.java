package org.example.services;

import org.example.repositories.CharacteristicRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CharacteristicServiceImpl implements CharacteristicService {

    private CharacteristicRepository characteristicRepository;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public void setCharacteristicRepository(CharacteristicRepository characteristicRepository) {
        this.characteristicRepository = characteristicRepository;
    }
}
