package com.back.endTest.web.form.jobBoard;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class InquiryForm {

  private Long jobBoardIdPk;

  private String titleJob;

  private String idJob;

  private String closingDate;

  private String numberOfPersons;

  private String genderJob;

  private String academicAbility;

  private String salaryWay;

  private Long salaryAmount;

  private String workPeriod;

  private String workDay;

  private String comeInJob;

  private String comeOutJob;

  private String workTypeJob;

  private String employForm;

  private String benefitJob;

  private String placeName;

  private String placeAddress;

  private String detailContent;

  private String managerName;

  private String managerPhone;

  private MultipartFile attachFile; //일반파일
  private List<MultipartFile> imageFiles; //이미지파일
}
