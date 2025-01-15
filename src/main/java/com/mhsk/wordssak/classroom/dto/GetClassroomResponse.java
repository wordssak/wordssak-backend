package com.mhsk.wordssak.classroom.dto;

import com.mhsk.wordssak.classroom.entity.Classroom;
import lombok.Getter;

@Getter
public class GetClassroomResponse {
    private String id;
    private String schoolName;
    private Integer grade;
    private Integer classNumber;
    private String classCode;

    private GetClassroomResponse(String id, String schoolName, Integer grade, Integer classNumber, String classCode) {
        this.id = id;
        this.schoolName = schoolName;
        this.grade = grade;
        this.classNumber = classNumber;
        this.classCode = classCode;
    }

    public static GetClassroomResponse from(Classroom classroom) {
        return new GetClassroomResponse(
                classroom.getId().toString(), classroom.getSchool(), classroom.getGrade(),
                classroom.getClassNumber(), classroom.getClassCode()
        );
    }
}
