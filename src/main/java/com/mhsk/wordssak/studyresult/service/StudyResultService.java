package com.mhsk.wordssak.studyresult.service;

import com.mhsk.wordssak.student.repository.StudentRepository;
import com.mhsk.wordssak.studyresult.dto.request.StudyResultRequest;
import com.mhsk.wordssak.studyresult.dto.response.StudyResultResponse;
import com.mhsk.wordssak.studyresult.entity.StudyResult;
import com.mhsk.wordssak.studyresult.repository.StudyResultRepository;
import com.mhsk.wordssak.wordbook.repository.WordBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudyResultService {

  private final StudyResultRepository studyResultRepository;
  private final StudentRepository studentRepository;
  private final WordBookRepository wordBookRepository;

  @Transactional
  public void saveStudyResult(StudyResultRequest request) {
    StudyResult studyResult = new StudyResult();
    studyResult.setWordBook(wordBookRepository.getReferenceById(1L));
    studyResult.setStudent(studentRepository.getReferenceById(1L));
    studyResult.setMemorizedCount(5);
    studyResult.setNotMemorizedCount(request.getNotMemorizedCount());
    studyResult.setSatisfaction(StudyResult.Satisfaction.valueOf(request.getSatisfaction()));
    studyResult.setIsCompleted(true);

    studyResultRepository.save(studyResult);
  }

  public List<StudyResultResponse> findByClassroomId(Long classroomId) {
    List<StudyResult> results = studyResultRepository.findByClassroomId(classroomId);

    return results.stream()
            .map(result -> new StudyResultResponse(
                    result.getStudent().getName(),
                    result.getMemorizedCount(),
                    result.getWordBook().getWords().size(),
                    result.getSatisfaction() != null ? result.getSatisfaction().toString() : "NONE"))
            .collect(Collectors.toList());
  }
}
