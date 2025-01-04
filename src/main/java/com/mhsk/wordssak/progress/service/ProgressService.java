package com.mhsk.wordssak.progress.service;

import com.mhsk.wordssak.progress.repository.ProgressRepository;
import com.mhsk.wordssak.progress.response.WordProgressResponse;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class ProgressService {

  private final ProgressRepository wordProgressRepository;

  public ProgressService(ProgressRepository wordProgressRepository) {
    this.wordProgressRepository = wordProgressRepository;
  }

  public List<WordProgressResponse> getWordProgressByStudentId(Long studentId) {
    List<Object[]> results = wordProgressRepository.findLatestWordProgressByStudentId(studentId);

    return results.stream()
            .map(result -> new WordProgressResponse(
                    ((BigInteger) result[0]).longValue(),
                    (String) result[1],
                    (String) result[2],
                    (String) result[3],
                    ((BigInteger) result[4]).intValue()
            ))
            .toList();
  }
}