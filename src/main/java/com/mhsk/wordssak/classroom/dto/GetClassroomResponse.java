package com.mhsk.wordssak.classroom.dto;

import com.mhsk.wordssak.classroom.entity.Classroom;
import lombok.Getter;

@Getter
public class GetClassroomResponse {
    private String schoolName;
    private Integer grade;
    private Integer classNumber;

    private GetClassroomResponse(String schoolName, Integer grade, Integer classNumber) {
        this.schoolName = schoolName;
        this.grade = grade;
        this.classNumber = classNumber;
    }

    public static GetClassroomResponse from(Classroom classroom) {
        return new GetClassroomResponse(classroom.getSchool(), classroom.getGrade(), classroom.getClassNumber());
    }
}
