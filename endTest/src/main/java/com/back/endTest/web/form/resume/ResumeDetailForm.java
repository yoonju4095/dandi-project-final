package com.back.endTest.web.form.resume;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ResumeDetailForm {

  private Long resumeId;  //이력서 관리번호
  private String idPerson;  //개인멤버 아이디
  private String resumeTitle; //이력서 제목
  private String selfIntro; //이력서 자기소개서

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime cDate;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime uDate;

  List<ResumeItem> resumeItems; //이력서 아이템

}
