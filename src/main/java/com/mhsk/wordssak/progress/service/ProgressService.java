package com.mhsk.wordssak.progress.service;

import com.mhsk.wordssak.progress.repository.ProgressRepository;
import com.mhsk.wordssak.progress.response.WordProgressResponse;
import com.mhsk.wordssak.progress.response.WordProgressResponseWithCount;
import com.mhsk.wordssak.progress.response.WordProgressResponseWithStatus;
import com.mhsk.wordssak.studyresult.repository.StudyResultRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProgressService {

  private final ProgressRepository progressRepository;
  private final StudyResultRepository studyResultRepository;

  public ProgressService(ProgressRepository progressRepository, StudyResultRepository studyResultRepository) {
    this.progressRepository = progressRepository;
    this.studyResultRepository = studyResultRepository;
  }

  public WordProgressResponseWithStatus getWordProgressWithStatusByStudentId(Long studentId) {
    boolean activeStatus = progressRepository.isActiveClassWordBookByStudentId(studentId);

    Long activeWordBookId = progressRepository.findActiveWordBookIdByStudentId(studentId);

    Long latestInactiveWordBookId = progressRepository.findLatestInactiveWordBookIdByStudentId(studentId);

    Long lastSubmittedWordBookId = studyResultRepository.findLatestWordBookIdByStudentId(studentId);

    boolean isNewWordBookRegistered = activeWordBookId != null &&
            (lastSubmittedWordBookId == null || !lastSubmittedWordBookId.equals(activeWordBookId));

    boolean satisfactionCompleted = !isNewWordBookRegistered &&
            latestInactiveWordBookId != null &&
            latestInactiveWordBookId.equals(lastSubmittedWordBookId) &&
            studyResultRepository.isSatisfactionCompletedForWordBook(studentId, latestInactiveWordBookId);

    List<WordProgressResponse> wordList = activeStatus
            ? progressRepository.findLatestWordProgressByStudentIdWithActiveStatus(studentId)
            .stream()
            .map(row -> new WordProgressResponse(
                    ((Number) row[0]).longValue(),
                    (String) row[1],
                    (String) row[2],
                    (String) row[3],
                    ((Number) row[4]).intValue()
            ))
            .toList()
            : List.of();

    return new WordProgressResponseWithStatus(activeStatus, satisfactionCompleted, wordList);
  }

  @Transactional
  public void increaseStudyCount(Long wordId, Long studentId) {
    progressRepository.increaseStudyCount(wordId, studentId);
  }

  public WordProgressResponseWithCount getMemorizedCountByStudentId(Long studentId) {
    Long wordBookId = progressRepository.findLatestWordBookIdByStudentId(studentId);

    if (wordBookId == null) {
      return new WordProgressResponseWithCount(0L, 0L, null);
    }

    Long totalWords = progressRepository.countTotalWords(wordBookId);

    Long memorizedCount = progressRepository.countMemorizedWords(studentId, wordBookId);

    Long notMemorizedCount = totalWords - memorizedCount;

    return new WordProgressResponseWithCount(memorizedCount, notMemorizedCount, wordBookId);
  }
}