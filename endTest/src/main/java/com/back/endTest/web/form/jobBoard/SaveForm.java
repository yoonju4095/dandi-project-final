package com.back.endTest.web.form.jobBoard;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class SaveForm {

  @NotBlank
  private String titleJob;

  private String idJob;

  @NotNull
  private String closingDate;

  @NotNull
  private String numberOfPersons;

  @NotNull
  private String genderJob;

  @NotNull
  private String academicAbility;

  @NotNull
  private String salaryWay;

  @NotNull
  private Long salaryAmount;

  @NotNull
  private String workPeriod;

  @NotNull
  private String workDay;

  @NotNull
  private String comeInJob;

  @NotNull
  private String comeOutJob;

  @NotNull
  private String workTypeJob;

  @NotNull
  private String employForm;

  private String benefitJob;

  @NotNull
  private String placeName;

  @NotNull
  private String placeAddress;

  private String detailContent;

  @NotNull
  private String managerName;

  @NotNull
  private String managerPhone;

  private MultipartFile attachFile; //일반파일
  private List<MultipartFile> imageFiles; //이미지파일
}
