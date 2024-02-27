package org.example.services;

import org.example.entities.Characteristic;
import org.example.repositories.CharacteristicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacteristicServiceImpl implements CharacteristicService {

    private CharacteristicRepository characteristicRepository;

    @Autowired
    public void setCharacteristicRepository(CharacteristicRepository characteristicRepository) {
        this.characteristicRepository = characteristicRepository;
    }

    @Override
    public void addAll(List<Characteristic> characteristics) {
        if(!characteristics.isEmpty()){
           characteristicRepository.saveAllAndFlush(characteristics);
        }
    }
}
