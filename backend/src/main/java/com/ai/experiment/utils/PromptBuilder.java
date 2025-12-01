package com.ai.experiment.utils;

import com.ai.experiment.dto.ApiMetadata;
import com.ai.experiment.dto.RelevantRule;
import java.util.List;
import java.util.stream.Collectors;

public class PromptBuilder {

  public static String build(
      ApiMetadata meta,
      List<RelevantRule> naming,
      List<RelevantRule> versioning,
      List<RelevantRule> errors) {

    return """
                You are an API guideline compliance expert.

                === API Metadata ===
                Title: %s
                Version: %s
                Paths: %s
                Schemas: %s
                Security: %s

                === Naming Rules ===
                %s

                === Versioning Rules ===
                %s

                === Error Handling Rules ===
                %s

                Provide a structured report containing:
                - Critical issues
                - Warnings
                - Suggestions
                - Positive observations
                """
        .formatted(
            meta.title(),
            meta.version(),
            meta.paths(),
            meta.schemas(),
            meta.securitySchemes(),
            join(naming),
            join(versioning),
            join(errors));
  }

  private static String join(List<RelevantRule> rules) {
    return rules.stream().map(r -> "- " + r.text()).collect(Collectors.joining("\n"));
  }
}
