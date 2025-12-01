package com.ai.experiment.repository;

import com.ai.experiment.dto.RelevantRule;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.stereotype.Repository;

/** Simple in-memory vector store for learning/development No external database needed! */
@Repository
public class ChromaRepository {

  private final EmbeddingModel embeddingModel;
  private final Map<String, StoredDocument> documents = new HashMap<>();

  public ChromaRepository(EmbeddingModel embeddingModel) {
    this.embeddingModel = embeddingModel;
  }

  public void ensureCollectionExists() {
    System.out.println("✓ Using in-memory vector store (no external DB needed)");
  }

  public void upsertRule(String id, String text, List<Double> embedding, String fileName) {
    // Convert to float array for storage
    float[] embeddingArray = new float[embedding.size()];
    for (int i = 0; i < embedding.size(); i++) {
      embeddingArray[i] = embedding.get(i).floatValue();
    }

    documents.put(id, new StoredDocument(id, text, embeddingArray, fileName));
    System.out.println("✓ Stored rule: " + id);
  }

  public List<RelevantRule> queryByText(String queryText, int topK) {
    if (documents.isEmpty()) {
      return List.of();
    }

    // Embed the query
    float[] queryEmbedding = embeddingModel.embed(queryText);

    // Calculate similarity scores for all documents
    List<ScoredDocument> scored = new ArrayList<>();
    for (StoredDocument doc : documents.values()) {
      double similarity = cosineSimilarity(queryEmbedding, doc.embedding);
      scored.add(new ScoredDocument(doc, similarity));
    }

    // Sort by similarity (highest first) and take top K
    return scored.stream()
        .sorted((a, b) -> Double.compare(b.score, a.score))
        .limit(topK)
        .map(
            sd ->
                new RelevantRule(
                    sd.doc.id,
                    sd.doc.fileName,
                    sd.doc.text,
                    1.0 - sd.score // Convert similarity to distance
                    ))
        .collect(Collectors.toList());
  }

  private double cosineSimilarity(float[] a, float[] b) {
    double dotProduct = 0.0;
    double normA = 0.0;
    double normB = 0.0;
    for (int i = 0; i < a.length; i++) {
      dotProduct += a[i] * b[i];
      normA += a[i] * a[i];
      normB += b[i] * b[i];
    }
    return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
  }

  private static class StoredDocument {
    final String id;
    final String text;
    final float[] embedding;
    final String fileName;

    StoredDocument(String id, String text, float[] embedding, String fileName) {
      this.id = id;
      this.text = text;
      this.embedding = embedding;
      this.fileName = fileName;
    }
  }

  private static class ScoredDocument {
    final StoredDocument doc;
    final double score;

    ScoredDocument(StoredDocument doc, double score) {
      this.doc = doc;
      this.score = score;
    }
  }
}
