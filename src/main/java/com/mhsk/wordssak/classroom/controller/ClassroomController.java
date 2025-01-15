package com.mhsk.wordssak.classroom.controller;

import com.mhsk.wordssak.classroom.dto.GetClassroomResponse;
import com.mhsk.wordssak.classroom.dto.RegisterClassInfoRequest;
import com.mhsk.wordssak.classroom.entity.Classroom;
import com.mhsk.wordssak.classroom.service.ClassroomService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

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

    @GetMapping("/info")
    public ResponseEntity<GetClassroomResponse> getClassInfo(@RequestParam String classCode, HttpSession session) {
        Classroom classroom = classroomService.getClassroom(classCode);
        session.setAttribute("classCode", classroom.getClassCode());

        return ResponseEntity.status(OK).body(GetClassroomResponse.from(classroom));
    }

    @GetMapping("/list/{teacherId}")
    public ResponseEntity<List<Classroom>> getClassroomsByTeacher(@PathVariable Long teacherId) {
        List<Classroom> classrooms = classroomService.getClassroomsByTeacherId(teacherId);
        return ResponseEntity.ok(classrooms);
    }

    @GetMapping()
    public ResponseEntity<List<GetClassroomResponse>> getClassrooms(HttpSession session) {
        String email = (String) session.getAttribute("email");
        List<Classroom> classrooms = classroomService.getClassroomsByEmail(email);

        return ResponseEntity.status(OK).body(classrooms.stream().map(GetClassroomResponse::from).toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassroom(@PathVariable String id) {
        classroomService.delete(id);
        return ResponseEntity.status(NO_CONTENT).build();
    }
}
