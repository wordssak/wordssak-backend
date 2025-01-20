package com.mhsk.wordssak.wordbook.dto.request;

import com.mhsk.wordssak.word.dto.request.WordRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EnrollWordBookRequest {

  private Integer grade;
  private Integer semester;
  private Integer unit;
  private String classCode;
  private String reward;
  private List<WordRequest> words;

}
