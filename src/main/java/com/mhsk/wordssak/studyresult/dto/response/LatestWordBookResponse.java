package com.mhsk.wordssak.studyresult.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LatestWordBookResponse {
  private Integer grade;
  private Integer unit;
  private String title;
  private String reward;
}
