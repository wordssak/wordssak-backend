package com.mhsk.wordssak.progress.service;

import com.mhsk.wordssak.progress.repository.ProgressRepository;
import com.mhsk.wordssak.progress.response.WordProgressResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgressService {

  private final ProgressRepository progressRepository;

  public ProgressService(ProgressRepository progressRepository) {
    this.progressRepository = progressRepository;
  }

  public List<WordProgressResponse> getWordProgressByStudentId(Long studentId) {
    return progressRepository.findLatestWordProgressByStudentId(studentId)
            .stream()
            .map(row -> new WordProgressResponse(
                    ((Number) row[0]).longValue(),
                    (String) row[1],
                    (String) row[2],
                    (String) row[3],
                    ((Number) row[4]).intValue()
            ))
            .toList();
  }

  @Transactional
  public void increaseStudyCount(Long wordId, Long studentId) {
    progressRepository.increaseStudyCount(wordId, studentId);
  }
}
