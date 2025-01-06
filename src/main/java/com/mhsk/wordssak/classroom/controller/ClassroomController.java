package com.mhsk.wordssak.classroom.controller;

import com.mhsk.wordssak.classroom.dto.GetClassroomResponse;
import com.mhsk.wordssak.classroom.dto.RegisterClassInfoRequest;
import com.mhsk.wordssak.classroom.entity.Classroom;
import com.mhsk.wordssak.classroom.service.ClassroomService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/classrooms")
@RequiredArgsConstructor
public class ClassroomController {
    private final ClassroomService classroomService;

    @PostMapping()
    public ResponseEntity<Void> registerClassInfo(
            @RequestBody RegisterClassInfoRequest registerClassInfoRequest, HttpSession session
    ) {
        classroomService.register(session.getAttribute("email").toString(), registerClassInfoRequest);

        return ResponseEntity.status(CREATED).build();
    }

    @GetMapping()
    public ResponseEntity<GetClassroomResponse> getClassInfo(@RequestParam String classCode, HttpSession session) {
        Classroom classroom = classroomService.getClassroom(classCode);
        session.setAttribute("classCode", classroom.getClassCode());

        return ResponseEntity.status(OK).body(GetClassroomResponse.from(classroom));
    }
}
