package org.example.services;

import org.example.repositories.FileDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileDataServiceImpl implements FileDataService{

    private FileDataRepository fileDataRepository;

    @Autowired
    public void setFileDataRepository(FileDataRepository fileDataRepository) {
        this.fileDataRepository = fileDataRepository;
    }
}
