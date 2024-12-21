package com.mhsk.wordssak.teacher.service;

import com.mhsk.wordssak.teacher.dto.SignInForm;
import com.mhsk.wordssak.teacher.dto.SignUpForm;

public interface TeacherService {
    void register(SignUpForm signUpForm);

    String login(SignInForm signInForm);
}
