package com.mhsk.wordssak.student.service;

import com.mhsk.wordssak.classroom.entity.Classroom;
import com.mhsk.wordssak.classroom.service.ClassroomService;
import com.mhsk.wordssak.student.dto.SignInForm;
import com.mhsk.wordssak.student.entity.Student;
import com.mhsk.wordssak.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Isolation.READ_UNCOMMITTED;

@Service
@RequiredArgsConstructor
@Transactional(isolation = READ_UNCOMMITTED, timeout = 20)
public class StudentServiceImpl implements StudentService {
    private final ClassroomService classroomService;
    private final StudentRepository studentRepository;

    @Override
    public void login(SignInForm signInForm, String classCode) {
        Classroom classroom = classroomService.getClassroom(classCode);
        if (studentRepository.existsByClassroomAndNameAndBirth(classroom, signInForm.getName(), signInForm.getBirth())) {
            return;
        }

        studentRepository.save(Student.from(signInForm, classroom));
    }
}
