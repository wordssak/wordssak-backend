package com.mhsk.wordssak.progress.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class WordProgressResponseWithStatus {
  private boolean activeStatus;
  private boolean satisfactionCompleted;
  private List<WordProgressResponse> wordList;
}
