package com.ai.experiment.controllers;

import com.ai.experiment.dto.OpenApiMetadata;
import com.ai.experiment.services.OpenApiService;
import java.io.File;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class UploadController {

  @Autowired private OpenApiService openApiService;

  @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public OpenApiMetadata uploadFile(@RequestParam("file") MultipartFile file) throws Exception {

    return parseAndExecute(file, openApiService::parseFile);
  }

  @PostMapping(value = "/summary", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public String summaryFile(@RequestParam("file") MultipartFile file) throws Exception {

    return parseAndExecute(
        file,
        f -> {
          var metadata = openApiService.parseFile(f);
          return openApiService.analyzeAndSummarizeApi(metadata);
        });
  }

  private <T> T parseAndExecute(MultipartFile file, Function<File, T> function) throws Exception {
    // Save temporarily
    File temp = File.createTempFile("upload-", file.getOriginalFilename());
    file.transferTo(temp);

    // Parse and extract metadata
    T result = function.apply(temp);

    temp.delete(); // optional

    return result;
  }
}
