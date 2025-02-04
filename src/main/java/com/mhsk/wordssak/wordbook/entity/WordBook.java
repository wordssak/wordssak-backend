package com.mhsk.wordssak.wordbook.entity;

import com.mhsk.wordssak.common.entity.BaseEntity;
import com.mhsk.wordssak.word.entity.Word;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class WordBook extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Integer grade;

  @Column(nullable = false)
  private Integer semester;

  @Column(nullable = false)
  private Integer unit;

  @Column
  private String title;

  @OneToMany(mappedBy = "wordBook", cascade = CascadeType.ALL)
  private List<Word> words = new ArrayList<>();

  public WordBook(Integer grade, Integer semester, Integer unit, String title) {
    this.grade = grade;
    this.semester = semester;
    this.unit = unit;
    this.title = title;
  }
}
