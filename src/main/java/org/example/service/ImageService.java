package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.exception.ImageServiceException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.zip.DataFormatException;

/*
@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public Image uploadImage(MultipartFile imageFile) throws IOException {
        Optional<Image> existingImage = imageRepository.findByName(imageFile.getName());
        if (existingImage.isPresent()) {
            throw new ImageServiceException("Изображение с именем " + imageFile.getName() + " уже существует.");
        }
        var imageToSave = Image.builder()
                .name(imageFile.getOriginalFilename())
                .type(imageFile.getContentType())
                .imageData(ImageUtils.compressImage(imageFile.getBytes()))
                .build();
        imageRepository.save(imageToSave);
        return imageToSave;
    }

    public byte[] downloadImage(String imageName) {
        Optional<Image> dbImage = imageRepository.findByName(imageName);

        return dbImage.map(image -> {
            try {
                return ImageUtils.decompressImage(image.getImageData());
            } catch (DataFormatException | IOException exception) {
                throw new ImageServiceException("Ошибка при отгрузке изображения");
            }
        }).orElse(null);
    }
}
 */