package com.mhsk.wordssak.classroom.classwordbook.dto;

import lombok.Getter;

@Getter
public class RegisterClassWordBookRequest {
    private String classroomId;
    private String semester;
    private String unit;
    private String reward;
}
