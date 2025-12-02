package com.ai.experiment.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.models.OpenAPI;
import java.util.*;

public record ApiMetadata(
    String title,
    String version,
    Map<String, List<String>> paths,
    List<String> schemas,
    List<String> securitySchemes,
    String contentJson) {
  public static ApiMetadata from(OpenAPI api) {

    Map<String, List<String>> ops = new LinkedHashMap<>();

    api.getPaths()
        .forEach(
            (path, item) -> {
              List<String> methods = new ArrayList<>();
              if (item.getGet() != null) methods.add("GET");
              if (item.getPost() != null) methods.add("POST");
              if (item.getPut() != null) methods.add("PUT");
              if (item.getDelete() != null) methods.add("DELETE");
              ops.put(path, methods);
            });

    List<String> schemaNames =
        api.getComponents() != null && api.getComponents().getSchemas() != null
            ? new ArrayList<>(api.getComponents().getSchemas().keySet())
            : List.of();

    List<String> security =
        api.getComponents() != null && api.getComponents().getSecuritySchemes() != null
            ? new ArrayList<>(api.getComponents().getSecuritySchemes().keySet())
            : List.of();

    String json;
    try {
      json = Json.mapper().writeValueAsString(api);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }


    return new ApiMetadata(
        api.getInfo().getTitle(), api.getInfo().getVersion(), ops, schemaNames, security, json);
  }
}
