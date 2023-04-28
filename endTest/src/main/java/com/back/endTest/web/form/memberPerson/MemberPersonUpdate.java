package com.back.endTest.web.form.memberPerson;

import lombok.Data;

@Data
public class MemberPersonUpdate {

  private Long personIdPk;

  private String namePerson;

  private String birthPerson;

  private String genderPerson;

  private String addressPerson;

  private String detailAddressPerson;

  private String emailPerson;

  private String phonePerson;
}
