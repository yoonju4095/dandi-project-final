package com.back.endTest.domain.common.paging;

import lombok.ToString;

@ToString
public class RecordCriteria {
  private int reqPage = 1; //요청페이지
  private final int REC_COUNT_PER_PAGE; //한페이지에 보여줄 레코드 수
  private int startRec; //한페이지에 보여줄 시작 레코드
  private int endRec; //한페이지에 보여줄 종료 레코드

  public RecordCriteria(int REC_COUNT_PER_PAGE) {
    this.REC_COUNT_PER_PAGE = REC_COUNT_PER_PAGE;
  }

  //시작 레코드 -> (요청페이지-1) * 한페이지에 보여줄 레코드+1
  public int getStartRec() {
    return this.startRec;
  }

  //종료 레코드 -> 요청페이지 * 한페이지에 보여줄 레코드 수
  public int getEndRec() {
    return this.endRec;
  }

  public int getReqPage() {
    return reqPage;
  }

  //요청페이지를 입력-> 시작 레코드와 종료 레코드를 산출
  public void setReqPage(int reqPage) {
    this.reqPage = reqPage;
    this.startRec = (reqPage - 1) * REC_COUNT_PER_PAGE + 1;
    this.endRec = REC_COUNT_PER_PAGE * reqPage;
  }

  public int getREC_COUNT_PER_PAGE() {
    return REC_COUNT_PER_PAGE;
  }
}
