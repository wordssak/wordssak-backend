package com.mhsk.wordssak.student.entity;

import com.mhsk.wordssak.classroom.entity.Classroom;
import com.mhsk.wordssak.common.entity.BaseEntity;
import com.mhsk.wordssak.student.dto.SignInForm;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class Student extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 6)
    private String birth;

    @ManyToOne
    @JoinColumn(name = "classroom_id", nullable = false)
    private Classroom classroom;

    public Student(String name, String birth, Classroom classroom) {
        this.name = name;
        this.birth = birth;
        this.classroom = classroom;
    }

    public static Student from(SignInForm signInForm, Classroom classroom) {
        return new Student(signInForm.getName(), signInForm.getBirth(), classroom);
    }
}
