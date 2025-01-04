package com.mhsk.wordssak.wordbook.repository;

import com.mhsk.wordssak.wordbook.entity.WordBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordBookRepository extends JpaRepository<WordBook, Long> {
}
