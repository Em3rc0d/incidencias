package com.incidencias.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

@RestController
@RequestMapping("/api/files")
public class FileController {

    // Cambia esta ruta a la carpeta que desees
    private static final String UPLOAD_DIR = "C:/Users/farid/OneDrive/Escritorio/dsw/evidences";

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // Crear el directorio si no existe
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Ruta completa del archivo
            String filePath = UPLOAD_DIR + "/" + file.getOriginalFilename();

            // Guardar archivo
            Path path = Paths.get(filePath);
            Files.write(path, file.getBytes());

            return ResponseEntity.ok(filePath); // Devolver ruta absoluta
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al subir archivo.");
        }
    }
}
