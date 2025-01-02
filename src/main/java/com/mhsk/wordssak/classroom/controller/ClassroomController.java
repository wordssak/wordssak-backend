package com.mhsk.wordssak.classroom.controller;

import com.mhsk.wordssak.classroom.dto.RegisterClassInfoRequest;
import com.mhsk.wordssak.classroom.service.ClassService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/classrooms")
@RequiredArgsConstructor
public class ClassroomController {
    private final ClassService classService;

    @PostMapping()
    public ResponseEntity<Void> registerClassInfo(
            @RequestBody RegisterClassInfoRequest registerClassInfoRequest, HttpSession session
    ) {
        classService.register(session.getAttribute("email").toString(), registerClassInfoRequest);

        return ResponseEntity.status(CREATED).build();
    }
}
