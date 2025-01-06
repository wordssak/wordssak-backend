package com.mhsk.wordssak.word.entity;

import com.mhsk.wordssak.common.entity.BaseEntity;
import com.mhsk.wordssak.wordbook.entity.WordBook;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

  private String example;

  public Word(String word, String meaning, String example) {
    this.word = word;
    this.meaning = meaning;
    this.example = example;
  }
}
