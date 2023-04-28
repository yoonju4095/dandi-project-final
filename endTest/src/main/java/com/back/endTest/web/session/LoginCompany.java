package com.back.endTest.web.session;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginCompany {
  //회원 세션 정보
  //기업 회원
  private Long companyIdPk;
  private String idCompany;
  private String nameCompany;
}
