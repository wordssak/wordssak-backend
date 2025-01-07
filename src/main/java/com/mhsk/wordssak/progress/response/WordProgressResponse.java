package com.mhsk.wordssak.progress.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WordProgressResponse {
  private Long wordId;
  private String word;
  private String meaning;
  private String example;
  private int studyCount;
}
