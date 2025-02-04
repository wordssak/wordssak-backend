package com.mhsk.wordssak.classroom.entity;

import com.mhsk.wordssak.classroom.classwordbook.entity.ClassWordBook;
import com.mhsk.wordssak.classroom.dto.RegisterClassInfoRequest;
import com.mhsk.wordssak.common.entity.BaseEntity;
import com.mhsk.wordssak.student.entity.Student;
import com.mhsk.wordssak.teacher.entity.Teacher;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Classroom extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @Column(name = "class_code",nullable = false, length = 100)
    private String classCode;

    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL)
    private List<ClassWordBook> classWordBooks;

    @Column(nullable = false, length = 50)
    private String school;

    private Integer grade;
    private Integer classNumber;

    @Builder
    private Classroom(Teacher teacher, String classCode, String school, Integer grade, Integer classNumber) {
        this.teacher = teacher;
        this.classCode = classCode;
        this.school = school;
        this.grade = grade;
        this.classNumber = classNumber;
    }

    public static Classroom from(Teacher teacher, RegisterClassInfoRequest registerClassInfoRequest, String classCode) {
        return Classroom.builder()
                .teacher(teacher)
                .classCode(classCode)
                .school(registerClassInfoRequest.getSchoolName())
                .grade(Integer.parseInt(registerClassInfoRequest.getGrade()))
                .classNumber(Integer.parseInt(registerClassInfoRequest.getClassNumber()))
                .build();
    }
}
