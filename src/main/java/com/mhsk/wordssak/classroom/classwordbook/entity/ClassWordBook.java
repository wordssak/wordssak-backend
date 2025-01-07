package com.mhsk.wordssak.classroom.classwordbook.entity;

import com.mhsk.wordssak.classroom.entity.Classroom;
import com.mhsk.wordssak.common.entity.BaseEntity;
import com.mhsk.wordssak.wordbook.entity.WordBook;
import jakarta.persistence.*;

@Entity
public class ClassWordBook extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "classroom_id", nullable = false)
  private Classroom classroom;

  @ManyToOne
  @JoinColumn(name = "wordbook_id", nullable = false)
  private WordBook wordBook;

  private String reward;

  private boolean activeStatus;

}
