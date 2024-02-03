package org.example.exception;

public class ImageServiceException extends RuntimeException {
    public ImageServiceException(String message) {
        super(message);
    }
}
