package com.mhsk.wordssak.teacher.service;

import com.mhsk.wordssak.teacher.dto.SignUpForm;
import com.mhsk.wordssak.teacher.entity.Teacher;
import com.mhsk.wordssak.teacher.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    @Override
    public void register(SignUpForm signUpForm) {
        if (teacherRepository.existsByEmail(signUpForm.getEmail())) {
            throw new DataIntegrityViolationException("이미 가입된 이메일 주소입니다.");
        }

        teacherRepository.save(Teacher.from(signUpForm));
    }
}
