package com.ai.experiment.controllers;

import com.ai.experiment.dto.RelevantRule;
import com.ai.experiment.services.RuleIndexService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rules")
public class RuleController {

  private final RuleIndexService ruleIndexService;

  public RuleController(RuleIndexService ruleIndexService) {
    this.ruleIndexService = ruleIndexService;
  }

  @PostMapping("/reindex")
  public String reindexRules() {
    int count = ruleIndexService.reindexAllRules();
    return "Indexed " + count + " rule chunks into Chroma.";
  }

  @GetMapping("/search")
  public List<RelevantRule> searchRules(
      @RequestParam String query, @RequestParam(defaultValue = "5") int topK) {
    return ruleIndexService.findRelevantRules(query, topK);
  }
}
