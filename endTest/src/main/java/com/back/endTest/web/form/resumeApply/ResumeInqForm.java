package com.back.endTest.web.form.resumeApply;

import lombok.Data;

import java.util.List;

@Data
public class ResumeInqForm {
  private String idPerson;  //개인멤버 아이디
  private String resumeTitle; //이력서 제목
  private String selfIntro; //이력서 자기소개서

  List<ResumeApDeForm> resumeApDeForms; //이력서 아이템
}
