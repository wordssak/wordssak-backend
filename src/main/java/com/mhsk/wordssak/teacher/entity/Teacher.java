package com.mhsk.wordssak.teacher.entity;

import com.mhsk.wordssak.common.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
public class Teacher extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100)
  private String email;

  @Column(nullable = false, length = 255)
  private String password;

  @Column(nullable = false, length = 50)
  private String name;

}