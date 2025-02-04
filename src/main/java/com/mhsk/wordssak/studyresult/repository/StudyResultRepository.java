package com.mhsk.wordssak.studyresult.repository;

import com.mhsk.wordssak.studyresult.entity.StudyResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudyResultRepository extends JpaRepository<StudyResult, Long> {

  @Query("SELECT sr FROM StudyResult sr JOIN sr.student s WHERE s.classroom.id = :classroomId")
  List<StudyResult> findByClassroomId(@Param("classroomId") Long classroomId);

  @Query(value = """
    SELECT CASE WHEN COUNT(sr.id) > 0 THEN true ELSE false END
    FROM study_result sr
    WHERE sr.student_id = :studentId
      AND sr.wordbook_id = :wordBookId
      AND sr.is_completed = true
  """, nativeQuery = true)
  boolean isSatisfactionCompletedForWordBook(@Param("studentId") Long studentId, @Param("wordBookId") Long wordBookId);

  Optional<StudyResult> findFirstByStudentIdOrderByCreatedAtDesc(Long studentId);

  @Query("""
    SELECT sr 
    FROM StudyResult sr 
    WHERE sr.student.id = :studentId 
    AND sr.wordBook.id = :wordBookId
""")
  Optional<StudyResult> findByStudentIdAndWordBookId(@Param("studentId") Long studentId, @Param("wordBookId") Long wordBookId);

  @Query(value = """
    SELECT sr.wordbook_id
    FROM study_result sr
    WHERE sr.student_id = :studentId
    ORDER BY sr.created_at DESC
    LIMIT 1
""", nativeQuery = true)
  Long findLatestWordBookIdByStudentId(@Param("studentId") Long studentId);
}