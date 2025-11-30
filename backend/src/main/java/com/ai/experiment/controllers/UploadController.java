package com.ai.experiment.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
public class UploadController {

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadSwagger(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("No file uploaded.");
            }

            String filename = file.getOriginalFilename();
            if (filename == null ||
                    !(filename.endsWith(".yaml")
                            || filename.endsWith(".yml")
                            || filename.endsWith(".json"))) {
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                        .body("Only .yaml, .yml, or .json files are supported.");
            }

            byte[] content = file.getBytes();

            // Placeholder: parsing will be added later
            System.out.println("Received file: " + filename);
            System.out.println("Size: " + content.length + " bytes");

            return ResponseEntity.ok("File uploaded successfully.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error processing file: " + e.getMessage());
        }
    }
}
