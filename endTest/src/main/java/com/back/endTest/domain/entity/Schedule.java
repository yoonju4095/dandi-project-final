package com.back.endTest.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
  private Long scheduleIdPk;                  //  schedule_ID_PK NUMBER(10),
  private String myId; //기업 아이디

  private String nameSchedule;                 //    NAME_schedule VARCHAR2(30),
  private String idSchedule;                 //    ID_schedule VARCHAR2(20),
  private String workSchedule;                 //    work_schedule VARCHAR2(30),
  private String daySchedule;                 //    day_schedule VARCHAR2(30),
  private String comeInSchedule;                 //    come_in_schedule VARCHAR2(40),
  private String comeOutSchedule;                 //    come_out_schedule VARCHAR2(40),
  private String periodStart;                 //    period_start VARCHAR2(30),
  private String periodEnd;                 //    period_end VARCHAR2(30),
}
