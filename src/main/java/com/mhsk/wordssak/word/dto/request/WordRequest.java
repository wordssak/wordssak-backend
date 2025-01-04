package com.mhsk.wordssak.word.dto.request;

import lombok.Data;

@Data
public class WordRequest {
  private String word;
  private String meaning;
  private String example;
}
