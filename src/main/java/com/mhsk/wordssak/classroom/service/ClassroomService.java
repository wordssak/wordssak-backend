package com.mhsk.wordssak.classroom.service;

import com.mhsk.wordssak.classroom.dto.RegisterClassInfoRequest;
import com.mhsk.wordssak.classroom.entity.Classroom;
import org.springframework.stereotype.Service;

@Service
public interface ClassroomService {
    void register(String email, RegisterClassInfoRequest registerClassInfoRequest);

    Classroom getClassroom(String classCode);
}
