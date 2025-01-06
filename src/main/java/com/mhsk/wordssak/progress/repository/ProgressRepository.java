package com.mhsk.wordssak.progress.repository;

import com.mhsk.wordssak.word.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgressRepository extends JpaRepository<Word, Long> {

  @Query(value = """
        SELECT w.id AS wordId, w.word AS word, w.meaning AS meaning, w.example AS example, 
               COALESCE(p.study_count, 0) AS studyCount
        FROM ClassWordBook cwb
        JOIN WordBook wb ON cwb.wordbook_id = wb.id
        JOIN Word w ON wb.id = w.wordbook_id
        LEFT JOIN progress p ON w.id = p.word_id AND p.student_id = :studentId
        WHERE cwb.classroom_id = (
            SELECT classroom_id 
            FROM Student 
            WHERE id = :studentId
        )
        ORDER BY cwb.created_at DESC
        LIMIT 1
    """, nativeQuery = true)
  List<Object[]> findLatestWordProgressByStudentId(@Param("studentId") Long studentId);
}
