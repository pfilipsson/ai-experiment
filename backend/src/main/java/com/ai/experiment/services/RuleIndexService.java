package com.ai.experiment.services;

import com.ai.experiment.dto.RelevantRule;
import com.ai.experiment.dto.RuleChunk;
import com.ai.experiment.repository.ChromaRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RuleIndexService {

  private final RuleLoaderService ruleLoaderService;
  private final RagEmbeddingService ragEmbeddingService;
  private final ChromaRepository chromaRepository;

  public RuleIndexService(
      RuleLoaderService ruleLoaderService,
      RagEmbeddingService ragEmbeddingService,
      ChromaRepository chromaRepository) {
    this.ruleLoaderService = ruleLoaderService;
    this.ragEmbeddingService = ragEmbeddingService;
    this.chromaRepository = chromaRepository;
  }

  public int reindexAllRules() {
    chromaRepository.ensureCollectionExists();

    List<RuleChunk> chunks = ruleLoaderService.loadAllRuleChunks();
    int count = 0;

    for (RuleChunk chunk : chunks) {
      List<Double> vector = ragEmbeddingService.embed(chunk.getText());
      chromaRepository.upsertRule(chunk.getId(), chunk.getText(), vector, chunk.getFileName());
      count++;
    }

    System.out.println("✓ Successfully indexed " + count + " rule chunks in memory");
    return count;
  }

  public List<RelevantRule> findRelevantRules(String query, int topK) {
    return chromaRepository.queryByText(query, topK);
  }
}
