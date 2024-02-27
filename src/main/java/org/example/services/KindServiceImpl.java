package org.example.services;

import org.example.repositories.KindRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class KindServiceImpl implements KindService {

    private KindRepository kindRepository;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public void setKindRepository(KindRepository kindRepository) {
        this.kindRepository = kindRepository;
    }
}
