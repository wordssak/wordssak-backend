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

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping()
    public ResponseEntity<Void> signIn(@RequestBody SignInForm signInForm, HttpSession session) {
        String classCode = (String) session.getAttribute("classCode");
        studentService.login(signInForm, classCode);

        session.setAttribute("name", signInForm.getName());
        session.setAttribute("birth", signInForm.getBirth());

        return ResponseEntity.status(OK).build();
    }
}
