package com.mhsk.wordssak.teacher.service;

import com.mhsk.wordssak.teacher.dto.SignInForm;
import com.mhsk.wordssak.teacher.dto.SignUpForm;
import com.mhsk.wordssak.teacher.entity.Teacher;
import com.mhsk.wordssak.teacher.repository.TeacherRepository;
import jakarta.persistence.EntityNotFoundException;
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

    @Override
    public String login(SignInForm signInForm) {
        Teacher teacher = teacherRepository.findByEmail(signInForm.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("해당하는 회원을 찾을 수 없습니다."));

        if (teacher.validatePassword(signInForm.getPassword())) {
            return teacher.getName();
        }

        throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
    }
}
