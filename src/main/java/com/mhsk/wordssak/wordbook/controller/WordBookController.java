package com.mhsk.wordssak.wordbook.controller;

import com.mhsk.wordssak.classroom.repository.ClassRepository;
import com.mhsk.wordssak.student.repository.StudentRepository;
import com.mhsk.wordssak.wordbook.dto.request.EnrollWordBookRequest;
import com.mhsk.wordssak.wordbook.entity.WordBook;
import com.mhsk.wordssak.wordbook.service.WordBookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wordbook")
public class WordBookController {

  private final WordBookService wordBookService;
  private final StudentRepository studentRepository;

  public WordBookController(WordBookService wordBookService, StudentRepository studentRepository) {
    this.wordBookService = wordBookService;
    this.studentRepository = studentRepository;
  }

  @PostMapping("/enroll")
  public ResponseEntity<String> uploadWords(@RequestBody EnrollWordBookRequest req) {
    WordBook savedWordBook = wordBookService.enrollWordBook(req);
    return ResponseEntity.ok("단어장 등록이 성공적으로 처리되었습니다. ID: " + savedWordBook.getId());
  }

  @GetMapping("/latest")
  public ResponseEntity<?> getLatestWordBook(@SessionAttribute("studentId") Long studentId) {
    return wordBookService.getLatestWordBookByClassroomId(studentRepository.findClassroomIdByStudentId(studentId))
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
  }
}
