package com.mhsk.wordssak.teacher.controller;

import com.mhsk.wordssak.teacher.dto.SignInForm;
import com.mhsk.wordssak.teacher.dto.SignUpForm;
import com.mhsk.wordssak.teacher.service.TeacherService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody SignUpForm signUpForm, HttpSession session) {
        teacherService.register(signUpForm);
        session.setAttribute("email", signUpForm.getEmail());

        return ResponseEntity.status(CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Long>> signIn(@RequestBody SignInForm signInForm, HttpSession session) {
        Long teacherId = teacherService.login(signInForm);
        session.setAttribute("email", signInForm.getEmail());

        Map<String, Long> response = new HashMap<>();
        response.put("teacherId", teacherId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
