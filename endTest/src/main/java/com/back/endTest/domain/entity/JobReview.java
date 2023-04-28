package com.back.endTest.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobReview {
  private Long reviewIdPk;             //  review_ID_PK      NUMBER(10),
  private String titleReview;            //  title_review      VARCHAR2(100),
  private String idReview;             //  id_review     VARCHAR2(40),
  private String contentReview;             //  content_review      CLOB,
  private String workTypeReview;             //  work_type_review      VARCHAR2(50),
  private String workPeriodReview;             //  work_period_review      VARCHAR2(10),
  private String workDayReview;             //  work_day_review     VARCHAR2(50),
  private String comeInReview;             //  come_in_review      VARCHAR2(10),
  private String comeOutReview;             //  come_out_review     VARCHAR2(10),
  private double assessReview;               //  assess_review     NUMBER(3,2) ,
  private Long focusJobBoardId;
}
