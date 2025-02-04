package com.mhsk.wordssak.teacher.service;

import com.mhsk.wordssak.teacher.dto.SignInForm;
import com.mhsk.wordssak.teacher.dto.SignUpForm;
import com.mhsk.wordssak.teacher.entity.Teacher;

public interface TeacherService {
    void register(SignUpForm signUpForm);

    Long login(SignInForm signInForm);

    Teacher getTeacher(String email);
}
