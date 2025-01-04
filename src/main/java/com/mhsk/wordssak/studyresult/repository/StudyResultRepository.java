package com.mhsk.wordssak.studyresult.repository;

import com.mhsk.wordssak.studyresult.entity.StudyResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyResultRepository extends JpaRepository<StudyResult, Long> {

  @Query("SELECT sr FROM StudyResult sr JOIN sr.student s WHERE s.classroom.id = :classroomId")
  List<StudyResult> findByClassroomId(@Param("classroomId") Long classroomId);
}