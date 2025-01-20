package com.mhsk.wordssak.classroom.classwordbook.entity;

import com.mhsk.wordssak.classroom.classwordbook.dto.RegisterClassWordBookRequest;
import com.mhsk.wordssak.classroom.entity.Classroom;
import com.mhsk.wordssak.common.entity.BaseEntity;
import com.mhsk.wordssak.wordbook.entity.WordBook;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class ClassWordBook extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "classroom_id", nullable = false)
    private Classroom classroom;

    @ManyToOne
    @JoinColumn(name = "wordbook_id", nullable = false)
    private WordBook wordBook;

    private String reward;

    private boolean activeStatus;


    public ClassWordBook(Classroom classroom, WordBook wordBook, String reward, boolean activeStatus) {
        this.classroom = classroom;
        this.wordBook = wordBook;
        this.reward = reward;
        this.activeStatus = activeStatus;
    }

    public static ClassWordBook from(
            Classroom classroom, WordBook wordBook, RegisterClassWordBookRequest registerClassWordBookRequest
    ) {
        return new ClassWordBook(classroom, wordBook, registerClassWordBookRequest.getReward(), true);
    }
}
