package com.mhsk.wordssak.progress.entity;

import com.mhsk.wordssak.common.entity.BaseEntity;
import com.mhsk.wordssak.student.entity.Student;
import com.mhsk.wordssak.word.entity.Word;
import jakarta.persistence.*;

@Entity
public class Progress extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "student_id", nullable = false)
  private Student student;

  @ManyToOne
  @JoinColumn(name = "word_id")
  private Word word;

  @Column(name = "study_count")
  private Integer studyCount;

}
