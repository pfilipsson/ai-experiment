package com.ai.experiment.services;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import java.io.File;
import org.springframework.stereotype.Service;

@Service
public class OpenApiService {

  public OpenAPI parseFile(File file) {

    OpenAPI openAPI = new OpenAPIV3Parser().read(file.getAbsolutePath());

    if (openAPI == null) {
      throw new RuntimeException("Failed to parse OpenAPI file: " + file.getName());
    }

    return openAPI;
  }
}
