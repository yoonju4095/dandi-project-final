package com.back.endTest.domain.resume;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Resume {
  private Long resumeId;  //이력서 관리번호
  private String idPerson;  //개인멤버 아이디
  private String resumeTitle; //이려서 제목
  private String selfIntro; //이력서 자기소개서
  private LocalDateTime cDate; //작성일
  private LocalDateTime uDate; //수정일


}
