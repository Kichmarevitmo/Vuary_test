package org.example.services;

import org.example.repositories.FileDataRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class FileDataServiceImpl implements FileDataService{

    private FileDataRepository fileDataRepository;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public void setFileDataRepository(FileDataRepository fileDataRepository) {
        this.fileDataRepository = fileDataRepository;
    }
}
