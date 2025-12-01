package com.ai.experiment.dto;

import java.util.List;

public record OpenApiMetadata(
    String file, String title, String version, int paths, List<String> operations, String content) {
  // Backwards-compatible getters
  public String getFile() {
    return file;
  }

  public String getTitle() {
    return title;
  }

  public String getVersion() {
    return version;
  }

  public int getPaths() {
    return paths;
  }

  public List<String> getOperations() {
    return operations;
  }

  public String getContent() {
    return content;
  }
}
