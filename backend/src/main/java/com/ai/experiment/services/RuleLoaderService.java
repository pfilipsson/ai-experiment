package com.ai.experiment.services;

import com.ai.experiment.dto.RuleChunk;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RuleLoaderService {

  private final Path rulesDirectory;

  public RuleLoaderService(@Value("${rules.path:../rules}") String rulesPath) {
    this.rulesDirectory = Paths.get(rulesPath).toAbsolutePath().normalize();
  }

  public List<RuleChunk> loadAllRuleChunks() {
    List<RuleChunk> chunks = new ArrayList<>();

    if (!Files.exists(rulesDirectory)) {
      throw new IllegalStateException("Rules directory does not exist: " + rulesDirectory);
    }

    try (DirectoryStream<Path> stream = Files.newDirectoryStream(rulesDirectory, "*.md")) {
      for (Path file : stream) {
        String fileName = file.getFileName().toString();
        String content = Files.readString(file, StandardCharsets.UTF_8);

        // Simple chunking: split on blank lines into paragraphs
        String[] parts = content.split("\\n\\s*\\n");
        int index = 0;
        for (String part : parts) {
          String trimmed = part.trim();
          if (trimmed.isEmpty()) continue;

          String id = fileName + "#" + index;
          chunks.add(new RuleChunk(id, fileName, trimmed));
          index++;
        }
      }
    } catch (IOException e) {
      throw new RuntimeException("Failed to load rules from " + rulesDirectory, e);
    }

    return chunks;
  }
}
