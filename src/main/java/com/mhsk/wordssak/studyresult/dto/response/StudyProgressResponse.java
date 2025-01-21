package com.mhsk.wordssak.studyresult.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudyProgressResponse {
  private String studentName;
  private int memorizedCount;
  private int totalWords;
  private String satisfaction;
}
