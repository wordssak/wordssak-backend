package com.mhsk.wordssak.studyresult.entity;

import com.mhsk.wordssak.common.entity.BaseEntity;
import com.mhsk.wordssak.student.entity.Student;
import com.mhsk.wordssak.wordbook.entity.WordBook;
import jakarta.persistence.*;

@Entity
public class StudyResult extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "wordbook_id", nullable = false)
  private WordBook wordBook;

  @ManyToOne
  @JoinColumn(name = "student_id", nullable = false)
  private Student student;

  @Enumerated(EnumType.STRING)
  private Satisfaction satisfaction;

  private Boolean isCompleted;

  public enum Satisfaction {
    BEST, GOOD, BED
  }

}
