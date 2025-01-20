package com.mhsk.wordssak.classroom.classwordbook.repository;

import com.mhsk.wordssak.classroom.classwordbook.entity.ClassWordBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassWordBookRepository extends JpaRepository<ClassWordBook, Long> {

  Optional<ClassWordBook> findTopByClassroomClassCodeOrderByCreatedAtDesc(String classCode);

  }
