package com.back.endTest.web.form.resumeApply;

import lombok.Data;

@Data
public class ResumeApplyList {

  private Long resumeId;  //이력서 관리번호
  private String idPerson;  //개인멤버 아이디
  private String resumeTitle; //이력서 제목
  private String selfIntro; //이력서 자기소개서
  private Long jobBoardIdPk;  //게시판 게시번호
}
