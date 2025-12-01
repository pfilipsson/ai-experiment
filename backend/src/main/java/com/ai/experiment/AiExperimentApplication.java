package com.ai.experiment;

import com.ai.experiment.services.RuleIndexService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AiExperimentApplication {

  public static void main(String[] args) {
    SpringApplication.run(AiExperimentApplication.class, args);
  }

  @Bean
  public CommandLineRunner loadRules(RuleIndexService ruleIndexService) {
    return args -> {
      System.out.println(">>> Loading rule embeddings...");
      int count = ruleIndexService.reindexAllRules();
      System.out.println(">>> Loaded " + count + " rule chunks into vector store.");
    };
  }
}
