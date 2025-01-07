package com.mhsk.wordssak.classroom.service;

import com.mhsk.wordssak.classroom.dto.RegisterClassInfoRequest;
import com.mhsk.wordssak.classroom.entity.Classroom;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClassroomService {
    void register(String email, RegisterClassInfoRequest registerClassInfoRequest);

    Classroom getClassroom(String classCode);

    List<Classroom> getClassroomsByTeacherId(Long teacherId);
}
