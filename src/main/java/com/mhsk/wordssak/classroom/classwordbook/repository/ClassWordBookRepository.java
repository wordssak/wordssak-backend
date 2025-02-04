package com.mhsk.wordssak.classroom.classwordbook.repository;

import com.mhsk.wordssak.classroom.classwordbook.entity.ClassWordBook;
import com.mhsk.wordssak.studyresult.dto.response.LatestWordBookResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClassWordBookRepository extends JpaRepository<ClassWordBook, Long> {

  Optional<ClassWordBook> findTopByClassroomClassCodeOrderByCreatedAtDesc(String classCode);

  @Query("SELECT new com.mhsk.wordssak.studyresult.dto.response.LatestWordBookResponse(wb.grade, wb.unit, wb.title, cwb.reward) " +
          "FROM ClassWordBook cwb " +
          "JOIN WordBook wb ON cwb.wordBook.id = wb.id " +
          "WHERE cwb.classroom.id = :classroomId " +
          "ORDER BY cwb.createdAt DESC " +
          "LIMIT 1")
  Optional<LatestWordBookResponse> findLatestWordBookByClassroomId(@Param("classroomId") Long classroomId);


}