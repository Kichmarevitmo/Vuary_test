package org.example.Images_Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
/*@RestController
@RequestMapping("/image")
public class StorageServiceController {
    @Autowired
    private StorageService service;



    /*@PostMapping("/fileSystem")
    public ResponseEntity<?> uploadImageToFIleSystem(@RequestParam("image")MultipartFile file) throws IOException {
        String uploadImage = service.uploadImageToFileSystem(file).toString();
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }*/
    /*@PostMapping("/fileSystemAvatarDefault")
    public ResponseEntity<?> uploadImageToFIleSystemAvatarDefault() throws IOException {
        String uploadImage = service.uploadImageToFileSystemDefaultAvatar().toString();
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @GetMapping("/fileSystem/{fileName}")
    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
        byte[] imageData=service.downloadImageFromFileSystem(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);

    }
}*/
