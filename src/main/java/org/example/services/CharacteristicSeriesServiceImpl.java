package org.example.services;

import org.example.repositories.CharacteristicSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CharacteristicSeriesServiceImpl implements CharacteristicSeriesService {

    private CharacteristicSeriesRepository characteristicSeriesRepository;

   // @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
   // @Autowired
    public void setCharacteristicSeriesRepository(CharacteristicSeriesRepository characteristicSeriesRepository) {
        this.characteristicSeriesRepository = characteristicSeriesRepository;
    }
}
