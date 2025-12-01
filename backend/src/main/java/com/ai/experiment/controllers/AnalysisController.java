package com.ai.experiment.controllers;

import com.ai.experiment.services.AnalysisService;
import com.ai.experiment.services.OpenApiService;
import java.io.File;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class AnalysisController {

  private final OpenApiService openApiService;
  private final AnalysisService analysisService;

  public AnalysisController(OpenApiService openApiService, AnalysisService analysisService) {
    this.openApiService = openApiService;
    this.analysisService = analysisService;
  }

  @PostMapping("/analyze")
  public ResponseEntity<?> analyze(@RequestParam("file") MultipartFile file) {
    try {
      // 1. Convert multipart file → temp file
      File tempFile = File.createTempFile("upload-", ".tmp");
      file.transferTo(tempFile);

      // 2. Parse API
      var api = openApiService.parseFile(tempFile);

      // 3. Run analysis
      var report = analysisService.analyzeApi(api);

      return ResponseEntity.ok(report);

    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
