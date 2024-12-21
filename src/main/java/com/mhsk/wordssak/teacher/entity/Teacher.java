package com.mhsk.wordssak.teacher.entity;

import com.mhsk.wordssak.common.entity.BaseEntity;
import com.mhsk.wordssak.teacher.dto.SignUpForm;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
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

    private Teacher(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public static Teacher from(SignUpForm signUpForm) {
        return new Teacher(signUpForm.getEmail(), signUpForm.getPassword(), signUpForm.getName());
    }

    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }
}
