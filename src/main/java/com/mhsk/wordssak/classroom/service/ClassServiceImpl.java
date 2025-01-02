package com.mhsk.wordssak.classroom.service;

import com.mhsk.wordssak.classroom.dto.RegisterClassInfoRequest;
import com.mhsk.wordssak.classroom.entity.Classroom;
import com.mhsk.wordssak.classroom.repository.ClassRepository;
import com.mhsk.wordssak.classroom.util.CodeGenerator;
import com.mhsk.wordssak.teacher.entity.Teacher;
import com.mhsk.wordssak.teacher.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Isolation.READ_UNCOMMITTED;

@Service
@RequiredArgsConstructor
@Transactional(isolation = READ_UNCOMMITTED, timeout = 60)
public class ClassServiceImpl implements ClassService {
    private final ClassRepository classroomRepository;
    private final TeacherService teacherService;

    @Override
    public void register(String email, RegisterClassInfoRequest registerClassInfoRequest) {
        Teacher teacher = teacherService.getTeacher(email);
        List<Classroom> existentClassrooms = classroomRepository.findByTeacher(teacher);
        for (Classroom classroom : existentClassrooms) {
            validateDuplication(classroom, registerClassInfoRequest);
        }

        String classCode = CodeGenerator.generate();
        classroomRepository.save(Classroom.from(teacher, registerClassInfoRequest, classCode));
    }

    private void validateDuplication(Classroom classroom, RegisterClassInfoRequest registerClassInfoRequest) {
        if (
                !classroom.getClassNumber().toString().equals(registerClassInfoRequest.getClassNumber())
                        || !classroom.getGrade().toString().equals(registerClassInfoRequest.getGrade())
                        || !classroom.getSchool().equals(registerClassInfoRequest.getSchoolName())
        ) {
            return;
        }

        throw new DataIntegrityViolationException("이미 등록된 클래스입니다. 기존 단어장을 수정해 주세요.");
    }
}
