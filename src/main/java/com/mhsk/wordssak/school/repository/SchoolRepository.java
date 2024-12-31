package com.mhsk.wordssak.school.repository;

import com.mhsk.wordssak.school.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchoolRepository extends JpaRepository<School, Long> {
    List<School> findTop3ByNameStartingWithOrderByNameAsc(String keyword);
}
