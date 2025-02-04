package com.mhsk.wordssak.student.controller;

import com.mhsk.wordssak.student.dto.SignInForm;
import com.mhsk.wordssak.student.service.StudentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping()
    public ResponseEntity<Map<String, Object>> signIn(@RequestBody SignInForm signInForm, HttpSession session) {
        String classCode = (String) session.getAttribute("classCode");
        Long studentId = studentService.login(signInForm, classCode);

        session.setAttribute("name", signInForm.getName());
        session.setAttribute("birth", signInForm.getBirth());
        session.setAttribute("studentId", studentId);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("studentId", studentId);

        return ResponseEntity.ok(response);
    }
}