package com.mhsk.wordssak.classroom.classwordbook.controller;

import com.mhsk.wordssak.classroom.classwordbook.dto.ActiveStatusResponse;
import com.mhsk.wordssak.classroom.classwordbook.dto.RegisterClassWordBookRequest;
import com.mhsk.wordssak.classroom.classwordbook.service.ClassWordBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/class-wordbooks")
@RequiredArgsConstructor
public class ClassWordBookController {
    private final ClassWordBookService classWordBookService;

    @PostMapping()
    public ResponseEntity<Void> registerClassWordBook(
            @RequestBody RegisterClassWordBookRequest registerClassWordBookRequest) {
        classWordBookService.registerClassWordBook(registerClassWordBookRequest);

        return ResponseEntity.status(CREATED).build();
    }

    @GetMapping("/status/{classCode}")
    public ActiveStatusResponse getActiveStatus(@PathVariable String classCode) {
        return classWordBookService.getActiveStatusByClassCode(classCode);
    }

    @PostMapping("/toggle/{classCode}")
    public ActiveStatusResponse toggleActiveStatus(@PathVariable String classCode) {
        return classWordBookService.toggleActiveStatus(classCode);
    }
}
