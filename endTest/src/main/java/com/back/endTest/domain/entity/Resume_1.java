package com.back.endTest.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resume_1 {
  private Long resumeId;//  resume_id     number(8),    --이력서 관리번호
  private String idPersonResume;//  id_person_resume   varchar2(20), -- 이력서 아이디
  private String resumeTitle;//  resume_title  varchar2(20),
  private String selfIntro;//  self_intro    clob,           -- 자기소개서
  private String businessName;//  business_name    varchar2(20),  -- 회사명
  private String businessDate;//  business_date    varchar2(8),    -- 근무일수
  private String businessWork;//  business_work     varchar2(30)
}
