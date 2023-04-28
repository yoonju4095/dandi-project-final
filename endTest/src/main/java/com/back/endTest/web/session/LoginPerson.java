package com.back.endTest.web.session;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginPerson {
  //회원 세션 정보
  //개인 회원
  private Long personIdPk;
  private String idPerson;
  private String namePerson;

}
