package org.example.services;

import org.example.repositories.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UnitServiceImpl implements UnitService {

    private UnitRepository unitRepository;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public void setUnitRepository(UnitRepository unitRepository) {this.unitRepository = unitRepository;}
}
