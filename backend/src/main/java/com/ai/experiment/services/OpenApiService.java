package com.ai.experiment.services;

import com.ai.experiment.dto.OpenApiMetadata;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Service;

@Service
public class OpenApiService {

  private final ChatClient chatClient;

  public OpenApiService(ChatModel chatModel) {
    this.chatClient = ChatClient.builder(chatModel).build();
  }

  public OpenApiMetadata parseFile(File file) {
    OpenAPI openAPI = new OpenAPIV3Parser().read(file.getAbsolutePath());

    if (openAPI == null) {
      throw new RuntimeException("Failed to parse OpenAPI file: " + file.getName());
    }

    // Extract basic metadata
    String title =
        (openAPI.getInfo() != null && openAPI.getInfo().getTitle() != null)
            ? openAPI.getInfo().getTitle()
            : "Unknown Title";

    String version =
        (openAPI.getInfo() != null && openAPI.getInfo().getVersion() != null)
            ? openAPI.getInfo().getVersion()
            : "Unknown Version";

    int pathCount = (openAPI.getPaths() != null) ? openAPI.getPaths().size() : 0;

    // Extract all operations: GET /accounts, POST /customers, etc.
    List<String> operations = new ArrayList<>();

    if (openAPI.getPaths() != null) {
      openAPI
          .getPaths()
          .forEach(
              (path, pathItem) -> {
                if (pathItem.getGet() != null) operations.add("GET " + path);
                if (pathItem.getPost() != null) operations.add("POST " + path);
                if (pathItem.getPut() != null) operations.add("PUT " + path);
                if (pathItem.getDelete() != null) operations.add("DELETE " + path);
                if (pathItem.getPatch() != null) operations.add("PATCH " + path);
                if (pathItem.getOptions() != null) operations.add("OPTIONS " + path);
                if (pathItem.getHead() != null) operations.add("HEAD " + path);
                if (pathItem.getTrace() != null) operations.add("TRACE " + path);
              });
    }

    String json;
    try {
      json = Json.mapper().writeValueAsString(openAPI);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }

    return new OpenApiMetadata(file.getName(), title, version, pathCount, operations, json);
  }

  public String analyzeAndSummarizeApi(OpenApiMetadata metadata) {
    return chatClient
        .prompt("Generate a summary for the following OpenAPI specification: " + metadata.content())
        .call()
        .content();
  }
}
