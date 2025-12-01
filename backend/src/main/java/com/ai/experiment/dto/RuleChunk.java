package com.ai.experiment.dto;

public class RuleChunk {
  private final String id;
  private final String fileName;
  private final String text;

  public RuleChunk(String id, String fileName, String text) {
    this.id = id;
    this.fileName = fileName;
    this.text = text;
  }

  public String getId() {
    return id;
  }

  public String getFileName() {
    return fileName;
  }

  public String getText() {
    return text;
  }
}
