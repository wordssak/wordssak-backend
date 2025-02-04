package com.mhsk.wordssak.student.service;

import com.mhsk.wordssak.student.dto.SignInForm;

public interface StudentService {
    Long login(SignInForm signInForm, String classCode);
}
