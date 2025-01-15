package com.mhsk.wordssak.classroom.service;

import com.mhsk.wordssak.classroom.dto.RegisterClassInfoRequest;
import com.mhsk.wordssak.classroom.entity.Classroom;
import com.mhsk.wordssak.classroom.repository.ClassRepository;
import com.mhsk.wordssak.classroom.util.CodeGenerator;
import com.mhsk.wordssak.teacher.entity.Teacher;
import com.mhsk.wordssak.teacher.service.TeacherService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Isolation.READ_UNCOMMITTED;

@Service
@RequiredArgsConstructor
@Transactional(isolation = READ_UNCOMMITTED, timeout = 60)
public class ClassroomServiceImpl implements ClassroomService {
    private final TeacherService teacherService;
    private final ClassRepository classroomRepository;

    @Override
    public void register(String email, RegisterClassInfoRequest registerClassInfoRequest) {
        Teacher teacher = teacherService.getTeacher(email);
        List<Classroom> existentClassrooms = getClassroomsByTeacherId(teacher.getId());
        existentClassrooms.stream()
                .forEach(classroom -> validateDuplication(classroom, registerClassInfoRequest));

        String classCode = CodeGenerator.generate();
        classroomRepository.save(Classroom.from(teacher, registerClassInfoRequest, classCode));
    }

    @Override
    public Classroom getClassroom(Long id) {
        return classroomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(
                        "해당하는 클래스룸을 찾을 수 없습니다. 전송된 ID: %s", id))
                );
    }

    @Override
    @Transactional(isolation = READ_UNCOMMITTED, readOnly = true, timeout = 30)
    public Classroom getClassroom(String classCode) {
        return classroomRepository.findByClassCode(classCode)
                .orElseThrow(() -> new EntityNotFoundException("해당하는 클래스룸을 찾을 수 없습니다."));
    }

    private void validateDuplication(Classroom classroom, RegisterClassInfoRequest registerClassInfoRequest) {
        if (
                !classroom.getClassNumber().toString().equals(registerClassInfoRequest.getClassNumber())
                        || !classroom.getGrade().toString().equals(registerClassInfoRequest.getGrade())
        ) {
            return;
        }

        throw new DataIntegrityViolationException("이미 등록된 클래스입니다. 기존 단어장을 수정해 주세요.");
    }

    @Override
    @Transactional(isolation = READ_UNCOMMITTED, readOnly = true, timeout = 20)
    public List<Classroom> getClassroomsByEmail(String email) {
        return classroomRepository.findByTeacherOrderByGradeAscClassNumber(teacherService.getTeacher(email));
    }

    @Override
    public void delete(String id) {
        Long parsedId = Long.parseLong(id);
        if (classroomRepository.existsById(parsedId)) {
            classroomRepository.deleteById(parsedId);
            return;
        }

        throw new EntityNotFoundException(String.format("해당하는 클래스를 찾을 수 없습니다. 전송된 ID: %s", id));
    }

    public List<Classroom> getClassroomsByTeacherId(Long teacherId) {
        return classroomRepository.findByTeacherId(teacherId);
    }
}
