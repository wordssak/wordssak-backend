package com.mhsk.wordssak.word.entity;

import com.mhsk.wordssak.common.entity.BaseEntity;
import com.mhsk.wordssak.wordbook.entity.WordBook;
import jakarta.persistence.*;

@Entity
public class Word extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "wordbook_id", nullable = false)
  private WordBook wordBook;

  @Column(nullable = false, length = 50)
  private String word;

  @Column(nullable = false)
  private String meaning;

  private String exampleSentence;

}
