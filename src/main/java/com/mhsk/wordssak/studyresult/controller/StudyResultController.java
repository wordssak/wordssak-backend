package com.mhsk.wordssak.studyresult.controller;

import com.mhsk.wordssak.studyresult.dto.request.StudyResultRequest;
import com.mhsk.wordssak.studyresult.dto.response.StudyProgressResponse;
import com.mhsk.wordssak.studyresult.dto.response.StudyResultResponse;
import com.mhsk.wordssak.studyresult.service.StudyResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/study")
@RequiredArgsConstructor
public class StudyResultController {

  private final StudyResultService studyResultService;

  @PostMapping("/submit")
  public ResponseEntity<String> submitStudyResult(@RequestBody StudyResultRequest request,@SessionAttribute("studentId") Long studentId) {
    studyResultService.saveStudyResult(studentId, request);
    return ResponseEntity.ok("학습 결과가 저장되었습니다.");
  }

  @GetMapping("/classroom/{classroomId}")
  public ResponseEntity<List<StudyResultResponse>> getProgressByClassroom(
          @PathVariable Long classroomId) {
    List<StudyResultResponse> result = studyResultService.findByClassroomId(classroomId);
    return ResponseEntity.ok(result);
  }

  @GetMapping("/classroom/{classroomCode}/progress")
  public ResponseEntity<List<StudyProgressResponse>> getClassroomProgress(@PathVariable String classroomCode) {
    List<StudyProgressResponse> progress = studyResultService.getClassroomProgress(classroomCode);
    return ResponseEntity.ok(progress);
  }
}