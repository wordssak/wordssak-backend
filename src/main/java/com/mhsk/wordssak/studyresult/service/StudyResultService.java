package com.mhsk.wordssak.studyresult.service;

import com.mhsk.wordssak.classroom.entity.Classroom;
import com.mhsk.wordssak.classroom.repository.ClassRepository;
import com.mhsk.wordssak.progress.repository.ProgressRepository;
import com.mhsk.wordssak.student.entity.Student;
import com.mhsk.wordssak.student.repository.StudentRepository;
import com.mhsk.wordssak.studyresult.dto.request.StudyResultRequest;
import com.mhsk.wordssak.studyresult.dto.response.StudyProgressResponse;
import com.mhsk.wordssak.studyresult.dto.response.StudyResultResponse;
import com.mhsk.wordssak.studyresult.entity.StudyResult;
import com.mhsk.wordssak.studyresult.repository.StudyResultRepository;
import com.mhsk.wordssak.wordbook.repository.WordBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudyResultService {

  private final StudyResultRepository studyResultRepository;
  private final StudentRepository studentRepository;
  private final WordBookRepository wordBookRepository;
  private final ClassRepository classRepository;
  private final ProgressRepository progressRepository;

  @Transactional
  public void saveStudyResult(Long studentId, StudyResultRequest request) {
    Long latestWordBookId = progressRepository.findLatestWordBookIdByStudentId(studentId);
    boolean isActive = progressRepository.isActiveWordBook(latestWordBookId);
    if (isActive) {
      throw new IllegalArgumentException("가장 최근 단어장이 활성 상태이므로 만족도 조사를 진행할 수 없습니다.");
    }

    Optional<StudyResult> existingResult = studyResultRepository.findByStudentIdAndWordBookId(studentId, latestWordBookId);
    if (existingResult.isPresent()) {
      throw new IllegalArgumentException("이미 만족도를 제출한 단어장입니다.");
    }

    StudyResult studyResult = new StudyResult();
    studyResult.setWordBook(wordBookRepository.getReferenceById(latestWordBookId));
    studyResult.setStudent(studentRepository.getReferenceById(studentId));
    studyResult.setMemorizedCount(request.getMemorizedCount());
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

  public List<StudyProgressResponse> getClassroomProgress(String classroomCode) {
    Long classroomId = classRepository.findByClassCode(classroomCode).orElseThrow().getId();
    if (classroomId == null) {
      throw new IllegalArgumentException("존재하지 않는 클래스룸 코드입니다.");
    }

    List<Student> students = studentRepository.findByClassroomId(classroomId);

    Long latestInactiveWordBookId = progressRepository.findLatestInactiveWordBookIdByClassroomId(classroomId);
    if (latestInactiveWordBookId == null) {
      throw new IllegalArgumentException("최근 비활성화된 단어장이 없습니다.");
    }

    List<StudyProgressResponse> progressResponses = new ArrayList<>();
    for (Student student : students) {
      Optional<StudyResult> studyResult = studyResultRepository.findByStudentIdAndWordBookId(student.getId(), latestInactiveWordBookId);

      String satisfaction = studyResult.map(sr -> sr.getSatisfaction().name()).orElse("transparent");
      int memorizedCount = studyResult.map(StudyResult::getMemorizedCount).orElse(0);
      int totalWords = progressRepository.countTotalWordsByWordBookId(latestInactiveWordBookId);

      progressResponses.add(new StudyProgressResponse(student.getName(), memorizedCount, totalWords, satisfaction));
    }

    return progressResponses;
  }
}

