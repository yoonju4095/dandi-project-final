package com.back.endTest.domain.common.paging;

import lombok.ToString;

@ToString
public class PageCriteria {
  private RecordCriteria rc;
  private final int PAGE_COUNT_PER_PAGE;

  private int startPage; //요청페이지의 시작페이지
  private int endPage;  //요청페이지의 종료페이지
  private long totalRec; //전체 레코드 수
  private int finalEndPage; //최종 페이지
  private boolean prev; //이전페이지 보여줄지 여부 판단
  private boolean next; //다음페이지 보여줄지 여부 판단

  public PageCriteria(RecordCriteria rc, int pageCnt) {
    this.rc = rc;
    this.PAGE_COUNT_PER_PAGE = pageCnt;
  }

  private void calculatePaging() {
    //요청페이지의 시작페이지     Math.ceil -> 반올림
    //올림 -> (요청페이지 / 요청페이지에 노출될 페이지 수)-1) * 요청페이지에 보여줄 페이지 수+1
    startPage = (int) (Math.ceil(rc.getReqPage() / (double) PAGE_COUNT_PER_PAGE) - 1) * PAGE_COUNT_PER_PAGE + 1;

    //요청페이지의 종료페이지    Math.ceil -> 반올림
    //올림 -> (요청페이지 / 요청페이지에 보여줄 페이지 수) * 요청페이지에 보여줄 페이지 수
    endPage = (int)Math.ceil(rc.getReqPage()/(double)PAGE_COUNT_PER_PAGE) * PAGE_COUNT_PER_PAGE;

    //최종페이지
    //올림 -> (전체레코드 수 / 요청페이지에 보여줄 레코드 수)
    finalEndPage = (int) Math.ceil(totalRec / (double) rc.getREC_COUNT_PER_PAGE());

    //최종페이지가 요청페이지의 종료페이지보다 작으면 최종페이지가 종료페이지가 됨
    if (finalEndPage < endPage) {
      endPage = finalEndPage;
    }
  }

  public RecordCriteria getRc() {
    return rc;
  }

  public long getTotalRec() {
    return totalRec;
  }

  //테이블의 전체레코드 수
  public void setTotalRec(long totalRec) {
    this.totalRec = totalRec;
    this.calculatePaging();
  }

  //시작페이지
  public int getStartPage() {
    return startPage;
  }

  //종료페이지
  public int getEndPage() {
    return endPage;
  }

  //최종페이지
  public int getFinalEndPage() {
    return finalEndPage;
  }

  //이전페이지 -> 요청페이지의 시작페이지 이전에 보여줄 레코드가 있으면 보이게
  public boolean isPrev() {
    return startPage != 1 ? true : false;
  }

  //다음페이지 -> 요청페이지의 종료페이지 다음에 보여줄 레코드가 있으면 보이게
  public boolean isNext() {
    return endPage * rc.getREC_COUNT_PER_PAGE() < totalRec ? true : false;
  }
}
