package com.back.endTest.web.form.resumeApply;

import lombok.Data;

@Data
public class ResumeJobBoard {
  private Long resumeApplyId; //이력서 지원하기 관리번호

  private Long resumeId;  //이력서 관리번호
  private String idPerson;  //개인멤버 아이디
  private String resumeTitle; //이려서 제목
  private String selfIntro; //이력서 자기소개서

  private Long jobBoardIdPk;                      //  Job_board_ID_pk  NUMBER(10),
  private String titleJob;                      //  title_job     VARCHAR2(100),
  private String idJob;                      //  id_job            VARCHAR2(40),
  private String closingDate;                     //  closing_date    VARCHAR2(20),
  private String numberOfPersons;                     //  number_of_persons    VARCHAR2(10),
  private String genderJob;                    //  gender_job    VARCHAR2(10),
  private String academicAbility;                    //  academic_ability    VARCHAR2(10),
  private String salaryWay;                    //  salary_way    VARCHAR2(10),
  private Long salaryAmount;                    //  salary_amount    NUMBER(20),
  private String workPeriod;                    //  work_period    VARCHAR2(10),
  private String workDay;                    //  work_day    VARCHAR2(50),
  private String comeInJob;                    //  come_in_job    VARCHAR2(10),
  private String comeOutJob;                    //  come_out_job    VARCHAR2(10),
  private String workTypeJob;                    //  work_type_job    VARCHAR2(50),
  private String employForm;                    //  employ_form    VARCHAR2(10),
  private String benefitJob;                    //  benefit_job    VARCHAR2(100),
  private String placeName;                    //  place_name    VARCHAR2(100),
  private String placeAddress;                    //  place_address    VARCHAR2(300),
  private String detailContent;                    //  detail_content    CLOB,
  private String managerName;                    //  manager_name    VARCHAR2(30),
  private String managerPhone;

  private String namePerson;
  private String birthPerson;
  private String genderPerson;
  private String addressPerson;
  private String detailAddressPerson;
  private String emailPerson;
  private String phonePerson;
}
