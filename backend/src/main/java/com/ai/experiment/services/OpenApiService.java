package com.ai.experiment.services;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import org.springframework.stereotype.Service;

@Service
public class OpenApiService {

    public OpenAPI parse(byte[] fileContent) {
        String content = new String(fileContent);

        var result = new OpenAPIV3Parser().readContents(content);

        if (result == null) {
            return null;
        }

        return result.getOpenAPI();
    }
}
