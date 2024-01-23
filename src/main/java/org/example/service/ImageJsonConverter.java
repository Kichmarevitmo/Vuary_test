package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class ImageJsonConverter {

    private final String imagePath;

    public ImageJsonConverter(String imagePath) {
        this.imagePath = imagePath;
    }

    public String convertToJson() throws IOException {
        // Чтение изображения с диска
        BufferedImage image = ImageIO.read(new File(imagePath));

        // Преобразование BufferedImage в байтовый массив
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        byte[] imageData = baos.toByteArray();

        // Кодирование в Base64
        String base64Image = Base64.getEncoder().encodeToString(imageData);

        // Создание объекта для хранения данных
        ImageData imageDataObj = new ImageData(base64Image);

        // Преобразование объекта в JSON
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(imageDataObj);
    }

    public BufferedImage decodeFromJson(String jsonRepresentation) throws IOException {
        // Преобразование JSON-строки в объект ImageData
        ObjectMapper objectMapper = new ObjectMapper();
        ImageData imageDataObj = objectMapper.readValue(jsonRepresentation, ImageData.class);

        // Декодирование Base64-строки в байтовый массив
        byte[] imageData = Base64.getDecoder().decode(imageDataObj.getBase64Image());

        // Преобразование байтового массива в изображение
        ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
        return ImageIO.read(bais);
    }

    // Пример класса для хранения данных изображения
    static class ImageData {
        private String base64Image;

        public ImageData() {
            // Конструктор без аргументов для десериализации JSON
        }

        public ImageData(String base64Image) {
            this.base64Image = base64Image;
        }

        public String getBase64Image() {
            return base64Image;
        }

        public void setBase64Image(String base64Image) {
            this.base64Image = base64Image;
        }
    }

    public static void main(String[] args) {
        // Укажите путь к изображению на вашем диске
        String imagePath = "C:/Users/Admin/Desktop/photo.jpg";

        // Создание объекта ImageJsonConverter с указанием пути к изображению
        ImageJsonConverter imageJsonConverter = new ImageJsonConverter(imagePath);

        try {
            // Преобразование изображения в JSON с использованием Base64
            String jsonRepresentation = imageJsonConverter.convertToJson();

            // Вывод JSON-представления изображения
            System.out.println("JSON Representation of the Image:\n" + jsonRepresentation);

            // Декодирование изображения из JSON
            BufferedImage decodedImage = imageJsonConverter.decodeFromJson(jsonRepresentation);

            // Визуальная проверка: сохранение декодированного изображения
            ImageIO.write(decodedImage, "png", new File("C:/Users/Admin/Desktop/decoded_image.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}