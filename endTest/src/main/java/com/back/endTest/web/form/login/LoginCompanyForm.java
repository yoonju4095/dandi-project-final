package com.back.endTest.web.form.login;

import lombok.Data;

@Data
public class LoginCompanyForm {
//  @NotBlank
//  @Size(min = 4, max = 15)
  private String idCompany;
//  @NotBlank
//  @Size(min = 8, max = 20)
  private String pwCompany;
}
