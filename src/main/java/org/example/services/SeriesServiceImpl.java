package org.example.services;

import org.example.repositories.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class SeriesServiceImpl implements SeriesService {

    private SeriesRepository seriesRepository;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public void setSeriesRepository(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }
}
