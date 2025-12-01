package com.ai.experiment.controllers;

import com.ai.experiment.dto.OpenApiMetadata;
import com.ai.experiment.services.OpenApiService;
import java.io.File;
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

    // Save temporarily
    File temp = File.createTempFile("upload-", file.getOriginalFilename());
    file.transferTo(temp);

    // Parse and extract metadata
    OpenApiMetadata metadata = openApiService.parseFile(temp);

    temp.delete(); // optional

    return metadata;
  }
}
