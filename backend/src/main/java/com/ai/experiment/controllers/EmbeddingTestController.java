package com.ai.experiment.controllers;

import com.ai.experiment.services.RagEmbeddingService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/embeddings")
public class EmbeddingTestController {

  private final RagEmbeddingService ragEmbeddingService;

  public EmbeddingTestController(RagEmbeddingService ragEmbeddingService) {
    this.ragEmbeddingService = ragEmbeddingService;
  }

  @GetMapping("/test")
  public List<Double> testEmbedding(@RequestParam String text) {
    return ragEmbeddingService.embed(text);
  }
}
