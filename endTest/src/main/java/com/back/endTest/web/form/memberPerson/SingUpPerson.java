package com.back.endTest.web.form.memberPerson;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SingUpPerson {

  @NotBlank
  private String idPerson;

  @NotNull
  private String pwPerson;

  @NotNull
  private String pwChkPerson;

  @NotNull
  private String namePerson;

  @NotNull
  private String birthPerson;

  @NotNull
  private String genderPerson;
  @NotNull
  private String addressPerson;

  @NotNull
  private String detailAddressPerson;

  @NotNull
  private String emailPerson;

  @NotNull
  private String phonePerson;
}
