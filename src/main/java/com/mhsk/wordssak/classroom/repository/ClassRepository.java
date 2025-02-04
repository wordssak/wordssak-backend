package com.mhsk.wordssak.classroom.repository;

import com.mhsk.wordssak.classroom.entity.Classroom;
import com.mhsk.wordssak.teacher.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClassRepository extends JpaRepository<Classroom, Long> {
    Optional<Classroom> findByClassCode(String classCode);

    List<Classroom> findByTeacherId(Long teacherId);

    List<Classroom> findByTeacherOrderByGradeAscClassNumber(Teacher teacher);


    @Query("""
        SELECT s.classroom.grade, s.classroom.classNumber, s.classroom.school 
        FROM Student s 
        WHERE s.id = :studentId
    """)
    Optional<Object[]> findClassroomInfoByStudentId(@Param("studentId") Long studentId);
}
