package com.mhsk.wordssak.wordbook.entity;

import com.mhsk.wordssak.common.entity.BaseEntity;
import jakarta.persistence.*;

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

}
