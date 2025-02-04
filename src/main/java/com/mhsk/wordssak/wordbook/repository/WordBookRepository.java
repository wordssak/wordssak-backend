package com.mhsk.wordssak.wordbook.repository;

import com.mhsk.wordssak.wordbook.entity.WordBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WordBookRepository extends JpaRepository<WordBook, Long> {

  @Query("""
        SELECT w.grade, w.semester, w.title 
        FROM WordBook w 
        WHERE w.id = :wordBookId
    """)
  Optional<Object[]> findWordBookInfoById(@Param("wordBookId") Long wordBookId);
}
