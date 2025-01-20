package com.mhsk.wordssak.progress.controller;

import com.mhsk.wordssak.progress.response.WordProgressResponseWithStatus;
import com.mhsk.wordssak.progress.service.ProgressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/progress")
public class ProgressController {

  private final ProgressService progressService;

  public ProgressController(ProgressService progressService) {
    this.progressService = progressService;
  }

  @GetMapping("/words/{studentId}")
  public ResponseEntity<WordProgressResponseWithStatus> getWordsByStudent(@PathVariable Long studentId) {
    WordProgressResponseWithStatus response = progressService.getWordProgressWithStatusByStudentId(studentId);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/study/{wordId}/{studentId}")
  public ResponseEntity<Void> increaseStudyCount(@PathVariable Long wordId, @PathVariable Long studentId) {
    progressService.increaseStudyCount(wordId, studentId);
    return ResponseEntity.ok().build();
  }
}
