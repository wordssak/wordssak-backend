package com.mhsk.wordssak.student.repository;

import com.mhsk.wordssak.classroom.entity.Classroom;
import com.mhsk.wordssak.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByClassroomAndNameAndBirth(Classroom classroom, String name, String birth);

    List<Student> findByClassroomId(Long classroomId);
}
