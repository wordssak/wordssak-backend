package com.mhsk.wordssak.studyresult.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudyResultResponse {
  private String studentName;
  private int memorizedCount;
  private int totalCount;
  private String satisfaction;
}
