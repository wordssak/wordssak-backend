package com.mhsk.wordssak.studyresult.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudyProgressResponse {
  private String name;
  private int memorizedCount;
  private int totalWords;
  private String satisfaction;
}
