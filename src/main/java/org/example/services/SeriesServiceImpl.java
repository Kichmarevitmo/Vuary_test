package org.example.services;

import org.example.entities.Series;
import org.example.repositories.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeriesServiceImpl implements SeriesService {

    private SeriesRepository seriesRepository;

    @Autowired
    public void setSeriesRepository(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }

    @Override
    public void addAll(List<Series> series) {
        if(!series.isEmpty()){
            seriesRepository.saveAllAndFlush(series);
        }
    }
}
