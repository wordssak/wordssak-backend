package com.mhsk.wordssak.progress.controller;

import com.mhsk.wordssak.progress.entity.Progress;
import com.mhsk.wordssak.progress.response.WordProgressResponse;
import com.mhsk.wordssak.progress.service.ProgressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progress")
public class ProgressController {

  private final ProgressService progressService;

  public ProgressController(ProgressService progressService) {
    this.progressService = progressService;
  }

  @GetMapping("/words/{studentId}")
  public ResponseEntity<List<WordProgressResponse>> getWordsByStudent(@PathVariable Long studentId) {
    List<WordProgressResponse> wordList = progressService.getWordProgressByStudentId(studentId);
    return ResponseEntity.ok(wordList);
  }

  @PostMapping("/study/{wordId}/{studentId}")
  public ResponseEntity<Void> increaseStudyCount(@PathVariable Long wordId, @PathVariable Long studentId) {
    progressService.increaseStudyCount(wordId, studentId);
    return ResponseEntity.ok().build();
  }
}
