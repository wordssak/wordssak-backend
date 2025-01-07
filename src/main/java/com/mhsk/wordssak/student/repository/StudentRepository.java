package com.mhsk.wordssak.student.repository;

import com.mhsk.wordssak.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository  extends JpaRepository<Student, Long> {
}
