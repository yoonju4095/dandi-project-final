package com.back.endTest.web.rest;

import lombok.Data;

@Data
public class ModifyRest {
  private Long scheduleIdPk;
  private String nameSchedule;
  private String idSchedule;
  private String workSchedule;
  private String daySchedule;
  private String comeInSchedule;
  private String comeOutSchedule;
  private String periodStart;
  private String periodEnd;
}
