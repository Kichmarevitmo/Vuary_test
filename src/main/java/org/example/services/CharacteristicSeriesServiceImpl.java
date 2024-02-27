package org.example.services;

import org.example.entities.CharacteristicSeries;
import org.example.repositories.CharacteristicSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacteristicSeriesServiceImpl implements CharacteristicSeriesService {

    private CharacteristicSeriesRepository characteristicSeriesRepository;

    @Autowired
    public void setCharacteristicSeriesRepository(CharacteristicSeriesRepository characteristicSeriesRepository) {
        this.characteristicSeriesRepository = characteristicSeriesRepository;
    }

    @Override
    public void addAll(List<CharacteristicSeries> characteristicSeries) {
        if(!characteristicSeries.isEmpty()){
            characteristicSeriesRepository.saveAllAndFlush(characteristicSeries);
        }
    }
}
