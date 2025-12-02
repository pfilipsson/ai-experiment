package com.ai.experiment.services;

import com.ai.experiment.dto.AnalysisReport;
import com.ai.experiment.dto.ApiMetadata;
import com.ai.experiment.dto.RelevantRule;
import com.ai.experiment.utils.PromptBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.models.OpenAPI;
import java.util.List;
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

    ApiMetadata meta = ApiMetadata.from(api);

    var contentJson = meta.contentJson();
    System.out.println("API Content JSON:\n" + contentJson);

    List<RelevantRule> naming = ruleIndexService.findRelevantRules("naming", 5);

    System.out.println("Naming Rules:");
    for (RelevantRule rule : naming) {
      System.out.println("- " + rule.text());
    }

    // Extract the paths in a comma-separated list
    var paths2 = meta.paths().entrySet().stream().flatMap(AnalysisService::getStream).collect(
        Collectors.joining(", "));

    System.out.println("API Paths: " + paths2);

    List<RelevantRule> version = ruleIndexService.findRelevantRules(paths2, 5);

    System.out.println("Versioning Rules:");
    for (RelevantRule rule : version) {
      System.out.println("- " + rule.text());
    }

    var error = api.getComponents().getSchemas().get("error");
    String errorJson;
    try {
      errorJson = Json.mapper().writeValueAsString(error);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }

    List<RelevantRule> errors = ruleIndexService.findRelevantRules(errorJson, 5);
    System.out.println("Error Handling Rules:");
    for (RelevantRule rule : errors) {
      System.out.println("- " + rule.text());
    }

    String prompt = PromptBuilder.build(meta, naming, version, errors);

    System.out.println("Generated Prompt:\n" + prompt);

    String llmResponse = chatModel.call(prompt);

    return new AnalysisReport(llmResponse);
  }

  private static Stream<String> getStream(Entry<String, List<String>> e) {
    return e.getValue().stream().map(v -> v + " " + e.getKey());
  }
}
