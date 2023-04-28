package com.back.endTest.web.form.memberCompany;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SingUpCompany {

  @NotBlank
  private String idCompany;

  @NotNull
  private String pwCompany;

  @NotNull
  private String pwChkCompany;

  @NotNull
  private String nameCompany;

  @NotNull
  private String businessCompany;

  @NotNull
  private String addressCompany;

  @NotNull
  private String detailAddressCompany;

  @NotNull
  private String emailCompany;

  @NotNull
  private String phoneCompany;
}
