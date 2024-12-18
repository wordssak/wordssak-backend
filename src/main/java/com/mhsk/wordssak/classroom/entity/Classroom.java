package com.mhsk.wordssak.classroom.entity;

import com.mhsk.wordssak.common.entity.BaseEntity;
import com.mhsk.wordssak.teacher.entity.Teacher;
import jakarta.persistence.*;

@Entity
public class Classroom extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "teacher_id", nullable = false)
  private Teacher teacher;

  @Column(nullable = false, length = 10)
  private String classCode;

  @Column(nullable = false, length = 50)
  private String school;

  private Integer grade;
  private Integer classNumber;

}
