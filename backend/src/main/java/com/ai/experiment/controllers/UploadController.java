package com.ai.experiment.controllers;

import com.ai.experiment.services.OpenApiService;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/upload")
public class UploadController {

    private final OpenApiService openApiService;

    public UploadController(OpenApiService openApiService) {
        this.openApiService = openApiService;
    }

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> uploadSwagger(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("No file uploaded.");
            }

            String filename = file.getOriginalFilename();

            if (filename == null ||
                    !(filename.endsWith(".yaml") || filename.endsWith(".yml") || filename.endsWith(".json"))) {
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                        .body("Only .yaml, .yml, or .json files are supported.");
            }

            byte[] content = file.getBytes();
            OpenAPI openAPI = openApiService.parse(content);

            if (openAPI == null) {
                return ResponseEntity.badRequest()
                        .body("Could not parse OpenAPI specification.");
            }

            // Basic summary
            Map<String, Object> summary = new HashMap<>();
            summary.put("file", filename);
            summary.put("title", openAPI.getInfo() != null ? openAPI.getInfo().getTitle() : null);
            summary.put("version", openAPI.getInfo() != null ? openAPI.getInfo().getVersion() : null);
            summary.put("paths", openAPI.getPaths() != null ? openAPI.getPaths().size() : 0);

            return ResponseEntity.ok(summary);

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error processing file: " + e.getMessage());
        }
    }
}
