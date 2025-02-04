package com.mhsk.wordssak.progress.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WordProgressResponseWithCount {
  private Long memorizedCount;
  private Long notMemorizedCount;
  private Long wordBookId;
}