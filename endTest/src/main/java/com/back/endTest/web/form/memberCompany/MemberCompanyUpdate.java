package com.back.endTest.web.form.memberCompany;

import lombok.Data;

@Data
public class MemberCompanyUpdate {

  private Long companyIdPk;

  private String nameCompany;

  private String businessCompany;

  private String addressCompany;

  private String detailAddressCompany;

  private String emailCompany;

  private String phoneCompany;
}
