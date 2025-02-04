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
    public Long login(SignInForm signInForm, String classCode) {
        Classroom classroom = classroomService.getClassroom(classCode);

        Student existingStudent = studentRepository.findByClassroomAndNameAndBirth(
                classroom,
                signInForm.getName(),
                signInForm.getBirth()
        );

        if (existingStudent != null) {
            return existingStudent.getId();
        }

        Student newStudent = studentRepository.save(Student.from(signInForm, classroom));
        return newStudent.getId();
    }
}
