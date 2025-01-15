package com.mhsk.wordssak.classroom.classwordbook.service;

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
                Integer.parseInt(registerClassWordBookRequest.getUnit())
        );
        wordBookRepository.save(wordBook);

        ClassWordBook classWordBook = ClassWordBook.from(classroom, wordBook, registerClassWordBookRequest);
        classWordBookRepository.save(classWordBook);
    }
}
