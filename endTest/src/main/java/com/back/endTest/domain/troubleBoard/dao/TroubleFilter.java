package com.back.endTest.domain.troubleBoard.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class TroubleFilter {

  private String category;      //분류코드
  private int startRec;         //시작레코드번호
  private int endRec;           //종료레코드번호

  private String searchType;    //검색유형
  private String keyword;       //검색어

  public TroubleFilter(String category, String searchType, String keyword) {
    this.category = category;
    this.searchType = searchType;
    this.keyword = keyword;
  }
}
