package org.example.services;

import org.example.repositories.ImageForSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ImageForSeriesServiceImpl implements ImageForSeriesService {

    private ImageForSeriesRepository imageForSeriesRepository;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public void setImageForSeriesRepository(ImageForSeriesRepository imageForSeriesRepository) {
        this.imageForSeriesRepository = imageForSeriesRepository;
    }
}
