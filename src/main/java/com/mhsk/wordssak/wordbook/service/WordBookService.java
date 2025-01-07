package com.mhsk.wordssak.wordbook.service;

import com.mhsk.wordssak.common.config.DefaultWordConfig;
import com.mhsk.wordssak.wordbook.dto.request.EnrollWordBookRequest;
import com.mhsk.wordssak.word.entity.Word;
import com.mhsk.wordssak.wordbook.entity.WordBook;
import com.mhsk.wordssak.wordbook.repository.WordBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WordBookService {

  private final WordBookRepository wordBookRepository;
  private final DefaultWordConfig defaultWordConfig;

  @Transactional
  public WordBook enrollWordBook(EnrollWordBookRequest request) {
    WordBook wordBook = new WordBook(
            request.getGrade(),
            request.getSemester(),
            request.getUnit()
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

    return wordBookRepository.save(wordBook);
  }
}
