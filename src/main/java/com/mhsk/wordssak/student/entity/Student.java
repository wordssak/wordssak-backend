package com.mhsk.wordssak.student.entity;

import com.mhsk.wordssak.classroom.entity.Classroom;
import com.mhsk.wordssak.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Student extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "classroom_id", nullable = false)
  private Classroom classroom;

  @Column(nullable = false, length = 50)
  private String name;

  @Column(length = 100)
  private String birth;

}
