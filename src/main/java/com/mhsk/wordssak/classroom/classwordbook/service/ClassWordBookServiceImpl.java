package com.mhsk.wordssak.classroom.classwordbook.service;

import com.mhsk.wordssak.classroom.classwordbook.dto.ActiveStatusResponse;
import com.mhsk.wordssak.classroom.classwordbook.dto.RegisterClassWordBookRequest;
import com.mhsk.wordssak.classroom.classwordbook.entity.ClassWordBook;
import com.mhsk.wordssak.classroom.classwordbook.repository.ClassWordBookRepository;
import com.mhsk.wordssak.classroom.entity.Classroom;
import com.mhsk.wordssak.classroom.service.ClassroomService;
import com.mhsk.wordssak.wordbook.entity.WordBook;
import com.mhsk.wordssak.wordbook.repository.WordBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Isolation.READ_UNCOMMITTED;

@Service
@RequiredArgsConstructor
@Transactional(isolation = READ_UNCOMMITTED, timeout = 20)
public class ClassWordBookServiceImpl implements ClassWordBookService {
    private final ClassroomService classroomService;
    private final ClassWordBookRepository classWordBookRepository;
    private final WordBookRepository wordBookRepository;

    @Override
    public void registerClassWordBook(RegisterClassWordBookRequest registerClassWordBookRequest) {
        Classroom classroom = classroomService.getClassroom(
                Long.parseLong(registerClassWordBookRequest.getClassroomId())
        );

        WordBook wordBook = new WordBook(
                classroom.getGrade(),
                Integer.parseInt(registerClassWordBookRequest.getSemester()),
                Integer.parseInt(registerClassWordBookRequest.getUnit()),
                null
        );
        wordBookRepository.save(wordBook);

        ClassWordBook classWordBook = ClassWordBook.from(classroom, wordBook, registerClassWordBookRequest);
        classWordBookRepository.save(classWordBook);
    }

    @Transactional(readOnly = true)
    public ActiveStatusResponse getActiveStatusByClassCode(String classCode) {
        return classWordBookRepository
                .findTopByClassroomClassCodeOrderByCreatedAtDesc(classCode)
                .map(classWordBook -> new ActiveStatusResponse(classWordBook.isActiveStatus()))
                .orElse(new ActiveStatusResponse(false));
    }

    @Transactional
    public ActiveStatusResponse toggleActiveStatus(String classCode) {
        ClassWordBook classWordBook = classWordBookRepository.findTopByClassroomClassCodeOrderByCreatedAtDesc(classCode)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 클래스 코드입니다."));

        boolean newStatus = !classWordBook.isActiveStatus();
        classWordBook.setActiveStatus(newStatus);
        classWordBookRepository.save(classWordBook);

        return new ActiveStatusResponse(newStatus);
    }

}
