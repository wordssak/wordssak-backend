package com.mhsk.wordssak.progress.repository;

import com.mhsk.wordssak.progress.entity.Progress;
import com.mhsk.wordssak.word.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgressRepository extends JpaRepository<Word, Long> {

  @Query(value = """
    SELECT w.id AS wordId, w.word AS word, w.meaning AS meaning, w.example AS example, 
           COALESCE(p.study_count, 0) AS studyCount
    FROM Class_Word_Book cwb
    JOIN Word_Book wb ON cwb.wordbook_id = wb.id
    JOIN Word w ON wb.id = w.wordbook_id
    LEFT JOIN progress p ON w.id = p.word_id AND p.student_id = :studentId
    WHERE cwb.classroom_id = (
        SELECT classroom_id 
        FROM Student 
        WHERE id = :studentId
    )
    ORDER BY cwb.created_at DESC
""", nativeQuery = true)
  List<Object[]> findLatestWordProgressByStudentId(@Param("studentId") Long studentId);

  @Modifying
  @Query(value = """
    MERGE INTO progress AS p
    USING (SELECT CAST(:studentId AS BIGINT) AS student_id, CAST(:wordId AS BIGINT) AS word_id) AS vals
    ON p.student_id = vals.student_id AND p.word_id = vals.word_id
    WHEN MATCHED THEN
        UPDATE SET p.study_count = p.study_count + 1
    WHEN NOT MATCHED THEN
        INSERT (student_id, word_id, study_count, created_at)
        VALUES (:studentId, :wordId, 1, CURRENT_TIMESTAMP)
""", nativeQuery = true)
  void increaseStudyCount(@Param("wordId") Long wordId, @Param("studentId") Long studentId);

}