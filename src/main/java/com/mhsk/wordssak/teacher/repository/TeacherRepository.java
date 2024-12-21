package com.mhsk.wordssak.teacher.repository;

import com.mhsk.wordssak.teacher.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    boolean existsByEmail(String email);

    Optional<Teacher> findByEmail(String email);
}
