package org.example.services;

import org.example.repositories.Characteristic_SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class Characteristic_SeriesServiceImpl implements Characteristic_SeriesService {

    private Characteristic_SeriesRepository characteristicSeriesRepository;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public void setCharacteristicSeriesRepository(Characteristic_SeriesRepository characteristicSeriesRepository) {
        this.characteristicSeriesRepository = characteristicSeriesRepository;
    }
}
