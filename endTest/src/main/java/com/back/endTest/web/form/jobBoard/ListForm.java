package com.back.endTest.web.form.jobBoard;

import lombok.Data;

@Data
public class ListForm {
  private Long jobBoardIdPk;

  private String titleJob;

  private String idJob;

  private String salaryWay;

  private Long salaryAmount;

  private String comeInJob;

  private String comeOutJob;

  private String placeName;

  private String placeAddress;

}
