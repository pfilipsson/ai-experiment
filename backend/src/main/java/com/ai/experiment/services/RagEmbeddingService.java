package com.ai.experiment.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.stereotype.Service;

@Service
public class RagEmbeddingService {

  private final OpenAiEmbeddingModel embeddingModel;

  public RagEmbeddingService(OpenAiEmbeddingModel embeddingModel) {
    this.embeddingModel = embeddingModel;
  }

  public List<Double> embed(String text) {

    // Your Spring AI version returns a raw float[] directly, NOT an
    // EmbeddingResponse
    float[] vector = embeddingModel.embed(text);

    // Convert float[] → List<Double>
    List<Double> result = new ArrayList<>(vector.length);
    for (float f : vector) {
      result.add((double) f);
    }

    return result;
  }
}
