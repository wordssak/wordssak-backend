package com.mhsk.wordssak.wordbook.dto.request;

import com.mhsk.wordssak.word.dto.request.WordRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EnrollWordBookRequest {

  Integer grade;
  Integer semester;
  Integer unit;
  private List<WordRequest> words;

}
