package com.mhsk.wordssak.wordbook.service;

import com.mhsk.wordssak.classroom.classwordbook.entity.ClassWordBook;
import com.mhsk.wordssak.classroom.classwordbook.repository.ClassWordBookRepository;
import com.mhsk.wordssak.classroom.entity.Classroom;
import com.mhsk.wordssak.classroom.repository.ClassRepository;
import com.mhsk.wordssak.common.config.DefaultWordConfig;
import com.mhsk.wordssak.studyresult.dto.response.LatestWordBookResponse;
import com.mhsk.wordssak.wordbook.dto.request.EnrollWordBookRequest;
import com.mhsk.wordssak.word.entity.Word;
import com.mhsk.wordssak.wordbook.entity.WordBook;
import com.mhsk.wordssak.wordbook.repository.WordBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WordBookService {

  private final WordBookRepository wordBookRepository;
  private final ClassWordBookRepository classWordBookRepository;
  private final DefaultWordConfig defaultWordConfig;
  private final ClassRepository classroomRepository;

  @Transactional
  public WordBook enrollWordBook(EnrollWordBookRequest request) {
    Classroom classroom = classroomRepository.findByClassCode(request.getClassCode())
            .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 클래스 코드입니다."));

    String title = defaultWordConfig.getDefaultTitle(request.getGrade(), request.getUnit()); // 🔥 자동 제목 설정

    WordBook wordBook = new WordBook(
            request.getGrade(),
            request.getSemester(),
            request.getUnit(),
            title
    );

    List<Word> defaultWords = defaultWordConfig.getDefaultWords(
            request.getGrade(), request.getSemester(), request.getUnit()
    );
    defaultWords.forEach(word -> {
      Word copy = new Word(word.getWord(), word.getMeaning(), word.getExample());
      copy.setWordBook(wordBook);
      wordBook.getWords().add(copy);
    });

    request.getWords().forEach(wordRequest -> {
      Word word = new Word(
              wordRequest.getWord(),
              wordRequest.getMeaning(),
              wordRequest.getExample()
      );
      word.setWordBook(wordBook);
      wordBook.getWords().add(word);
    });

    WordBook savedWordBook = wordBookRepository.save(wordBook);

    ClassWordBook classWordBook = new ClassWordBook(
            classroom,
            savedWordBook,
            request.getReward(),
            true
    );
    classWordBookRepository.save(classWordBook);

    return savedWordBook;
  }

  public Optional<LatestWordBookResponse> getLatestWordBookByClassroomId(Long classroomId) {
    return classWordBookRepository.findLatestWordBookByClassroomId(classroomId);
  }

}