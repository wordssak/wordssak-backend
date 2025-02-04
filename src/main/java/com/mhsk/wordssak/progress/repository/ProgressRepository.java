package com.mhsk.wordssak.progress.repository;

import com.mhsk.wordssak.word.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    AND cwb.active_status = true
    ORDER BY cwb.created_at DESC
  """, nativeQuery = true)
  List<Object[]> findLatestWordProgressByStudentIdWithActiveStatus(@Param("studentId") Long studentId);

  @Query(value = """
    SELECT CASE WHEN COUNT(cwb.id) > 0 THEN true ELSE false END
    FROM Class_Word_Book cwb
    JOIN Student s ON cwb.classroom_id = s.classroom_id
    WHERE s.id = :studentId AND cwb.active_status = true
  """, nativeQuery = true)
  boolean isActiveClassWordBookByStudentId(@Param("studentId") Long studentId);

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

  @Query(value = """
    SELECT cwb.wordbook_id
    FROM Class_Word_Book cwb
    JOIN classroom c ON c.id = cwb.classroom_id
    JOIN student s ON s.classroom_id = c.id
    WHERE s.id = :studentId
      AND cwb.active_status = true
    LIMIT 1
  """, nativeQuery = true)
  Long findActiveWordBookIdByStudentId(@Param("studentId") Long studentId);

  @Query(value = """
    SELECT cwb.wordbook_id
    FROM Class_Word_Book cwb
    JOIN Student s ON cwb.classroom_id = s.classroom_id
    WHERE s.id = :studentId
    ORDER BY cwb.created_at DESC
    LIMIT 1
""", nativeQuery = true)
  Long findLatestWordBookIdByStudentId(@Param("studentId") Long studentId);

  @Query(value = """
    SELECT CASE WHEN COUNT(cwb.id) > 0 THEN true ELSE false END
    FROM Class_Word_Book cwb
    WHERE cwb.wordbook_id = :wordBookId AND cwb.active_status = true
""", nativeQuery = true)
  boolean isActiveWordBook(@Param("wordBookId") Long wordBookId);

  @Query(value = """
    SELECT cwb.wordbook_id
    FROM Class_Word_Book cwb
    JOIN Student s ON cwb.classroom_id = s.classroom_id
    WHERE s.id = :studentId AND cwb.active_status = false
    ORDER BY cwb.created_at DESC
    LIMIT 1
""", nativeQuery = true)
  Long findLatestInactiveWordBookIdByStudentId(@Param("studentId") Long studentId);

  @Query(value = """
    SELECT cwb.wordbook_id
    FROM Class_Word_Book cwb
    WHERE cwb.classroom_id = :classroomId AND cwb.active_status = false
    ORDER BY cwb.created_at DESC
    LIMIT 1
""", nativeQuery = true)
  Long findLatestInactiveWordBookIdByClassroomId(@Param("classroomId") Long classroomId);

  @Query(value = """
    SELECT COUNT(*) FROM Word w WHERE w.wordbook_id = :wordBookId
""", nativeQuery = true)
  int countTotalWordsByWordBookId(@Param("wordBookId") Long wordBookId);


  @Query("SELECT wb.grade, wb.semester, wb.title FROM WordBook wb WHERE wb.id = :wordBookId")
  Optional<Object[]> findWordBookInfoById(@Param("wordBookId") Long wordBookId);

  @Query("""
        SELECT COUNT(p.id)
        FROM Progress p
        WHERE p.student.id = :studentId 
        AND p.word.wordBook.id = :wordBookId
        AND p.studyCount >= 5
    """)
  Long countMemorizedWords(@Param("studentId") Long studentId, @Param("wordBookId") Long wordBookId);

  @Query("""
        SELECT COUNT(w.id)
        FROM Word w
        WHERE w.wordBook.id = :wordBookId
    """)
  Long countTotalWords(@Param("wordBookId") Long wordBookId);
}
