package com.ai.experiment.services;

import com.ai.experiment.dto.AnalysisReport;
import com.ai.experiment.dto.ApiMetadata;
import com.ai.experiment.dto.RelevantRule;
import com.ai.experiment.utils.PromptBuilder;
import io.swagger.v3.oas.models.OpenAPI;
import java.util.List;
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

    List<RelevantRule> naming = ruleIndexService.findRelevantRules("naming rules", 5);

    System.out.println("Naming Rules:");
    for (RelevantRule rule : naming) {
      System.out.println("- " + rule.text());
    }

    List<RelevantRule> version = ruleIndexService.findRelevantRules("versioning rules", 5);

    System.out.println("Versioning Rules:");
    for (RelevantRule rule : version) {
      System.out.println("- " + rule.text());
    }

    List<RelevantRule> errors = ruleIndexService.findRelevantRules("error handling rules", 5);
    System.out.println("Error Handling Rules:");
    for (RelevantRule rule : errors) {
      System.out.println("- " + rule.text());
    }

    String prompt = PromptBuilder.build(meta, naming, version, errors);

    System.out.println("Generated Prompt:\n" + prompt);

    String llmResponse = chatModel.call(prompt);

    return new AnalysisReport(llmResponse);
  }
}
