package com.mhsk.wordssak.studyresult.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudyResultRequest {
  private String satisfaction;
  private int memorizedCount;
  private int notMemorizedCount;
}
