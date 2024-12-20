package com.mhsk.wordssak.teacher.controller;

import com.mhsk.wordssak.teacher.dto.SignUpForm;
import com.mhsk.wordssak.teacher.service.TeacherService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping()
    public ResponseEntity<Void> signUp(@RequestBody SignUpForm signUpForm, HttpSession session) {
        teacherService.register(signUpForm);
        session.setAttribute("email", signUpForm.getEmail());

        return ResponseEntity.status(CREATED).build();
    }
}
