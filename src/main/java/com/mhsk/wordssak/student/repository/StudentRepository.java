package com.mhsk.wordssak.student.repository;

import com.mhsk.wordssak.classroom.entity.Classroom;
import com.mhsk.wordssak.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByClassroomAndNameAndBirth(Classroom classroom, String name, String birth);

    List<Student> findByClassroomId(Long classroomId);

    @Query("SELECT s.classroom.id FROM Student s WHERE s.id = :studentId")
    Long findClassroomIdByStudentId(@Param("studentId") Long studentId);
}
