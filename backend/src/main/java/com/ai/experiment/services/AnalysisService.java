package com.ai.experiment.services;

import com.ai.experiment.dto.AnalysisReport;
import com.ai.experiment.dto.ApiMetadata;
import com.ai.experiment.dto.RelevantRule;
import com.ai.experiment.utils.PromptBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.models.OpenAPI;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

@Service
public class AnalysisService {

  private final RuleIndexService ruleIndexService;
  private final OpenAiChatModel chatModel;

  public AnalysisService(RuleIndexService ruleIndexService, OpenAiChatModel chatModel) {
    this.ruleIndexService = ruleIndexService;
    this.chatModel = chatModel;
  }

  public AnalysisReport analyzeApi(OpenAPI api) {

    //
    // STEP 1 — Extract API metadata
    //
    ApiMetadata meta = ApiMetadata.from(api);
    var contentJson = meta.contentJson();

    System.out.println("API Content JSON:\n" + contentJson);

    //
    // STEP 2 — Retrieve naming-related rules
    //
    List<RelevantRule> naming = ruleIndexService.findRelevantRules("naming", 5);

    System.out.println("Naming Rules:");
    for (RelevantRule rule : naming) {
      System.out.println("- " + rule.text());
    }

    //
    // STEP 3 — Retrieve versioning rules based on paths
    //
    var pathsJoined =
        meta.paths().entrySet().stream()
            .flatMap(AnalysisService::streamPaths)
            .collect(Collectors.joining(", "));

    System.out.println("API Paths: " + pathsJoined);

    var versionQuery = pathsJoined.isBlank() ? "versioning" : pathsJoined;
    List<RelevantRule> version = ruleIndexService.findRelevantRules(versionQuery, 5);

    System.out.println("Versioning Rules:");
    for (RelevantRule rule : version) {
      System.out.println("- " + rule.text());
    }

    //
    // STEP 4 — Retrieve error handling rules safely
    //

    // Safely extract components and schemas
    var components = api.getComponents();
    var schemas =
        (components != null && components.getSchemas() != null)
            ? components.getSchemas()
            : Map.<String, Object>of();

    // Try to retrieve an "error" schema if it exists
    var errorSchema = schemas.containsKey("error") ? schemas.get("error") : null;

    // Convert schema to JSON safely
    String errorJson;
    try {
      errorJson = Json.mapper().writeValueAsString(errorSchema != null ? errorSchema : Map.of());
    } catch (JsonProcessingException e) {
      errorJson = "{}";
    }

    List<RelevantRule> errors = ruleIndexService.findRelevantRules(errorJson, 5);

    System.out.println("Error Handling Rules:");
    for (RelevantRule rule : errors) {
      System.out.println("- " + rule.text());
    }

    //
    // STEP 5 — Build final prompt and invoke LLM
    //
    String prompt = PromptBuilder.build(meta, naming, version, errors);

    System.out.println("Generated Prompt:\n" + prompt);

    String llmResponse = chatModel.call(prompt);

    return new AnalysisReport(llmResponse);
  }

  private static Stream<String> streamPaths(Entry<String, List<String>> e) {
    return e.getValue().stream().map(v -> v + " " + e.getKey());
  }
}
