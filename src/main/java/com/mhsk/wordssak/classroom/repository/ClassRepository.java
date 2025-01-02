package com.mhsk.wordssak.classroom.repository;

import com.mhsk.wordssak.classroom.entity.Classroom;
import com.mhsk.wordssak.teacher.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassRepository extends JpaRepository<Classroom, Long> {
    List<Classroom> findByTeacher(Teacher teacher);
}
