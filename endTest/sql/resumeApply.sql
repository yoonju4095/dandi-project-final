--테이블 삭제
drop table apply_detail;
drop table resume_apply;

--시퀀스 삭제
drop sequence resume_apply_resume_apply_id_seq;


--이력서 지원 회원테이블
create table resume_apply (
  resume_apply_id number(8), --이력서 지원하기 관리번호
  resume_id     number(8),    --이력서 관리번호
  id_person     varchar2(20), -- 이력서 아이디
  resume_title  varchar2(20),
  self_intro    clob,           -- 자기소개서
  Job_board_ID_pk  NUMBER(10),  -- 구인게시판 정보
  title_job     VARCHAR2(100),
  id_job            VARCHAR2(40),
  closing_date    VARCHAR2(20),
  number_of_persons    VARCHAR2(10),
  gender_job    VARCHAR2(10),
  academic_ability    VARCHAR2(10),
  salary_way    VARCHAR2(10),
  salary_amount    NUMBER(20),
  work_period    VARCHAR2(30),
  work_day    VARCHAR2(50),
  come_in_job    VARCHAR2(10),
  come_out_job    VARCHAR2(10),
  work_type_job    VARCHAR2(50),
  employ_form    VARCHAR2(10),
  benefit_job    VARCHAR2(100),
  place_name    VARCHAR2(100),
  place_address    VARCHAR2(300),
  detail_content    CLOB,
  manager_name    VARCHAR2(30),
  manager_phone    VARCHAR2(13),
  person_id_pk  number,
  name_person varchar2(30), -- 개인정보
  birth_person varchar2(10),
  gender_person char(3),
  address_person varchar2(300),
  detail_address_person varchar2(300),
  email_person varchar2(40),
  phone_person varchar2(13),
  cdate         timestamp default systimestamp,
  udate         timestamp default systimestamp
  );

--기본키 생성
alter table resume_apply add constraint resume_apply_resume_apply_id_pk primary key (resume_apply_id);
--alter table resume_apply add constraint resume_apply_resume_id_uk unique (resume_id);

--제약조건
--alter table resume_apply add constraint resume_apply_resume_id_fk
--foreign key (resume_id) references member_resume(resume_id);
--alter table resume_apply add constraint resume_apply_id_person_fk
--foreign key (id_person) references MEMBER_PERSON(id_person);
--alter table resume_apply add constraint resume_apply_Job_board_ID_fk
--foreign key (Job_board_ID_pk) references Job_board(Job_board_ID_pk);


--시퀀스

create sequence resume_apply_resume_apply_id_seq;
desc resume_apply;

commit;


--지원하기 상세보기

drop table apply_detail;

--시퀀스 삭제
drop sequence apply_detail_resume_detail_id_seq;

create table apply_detail (
  resume_detail_id number(8), --이력서 상세보기 관리번호
  resume_id        number(8), --이력서 관리번호 fk
  resume_item_id   number(3), --이력서 아이템
  id_person        varchar(20), --이력서 아이디 fk
  business_name    varchar(20),  -- 회사명
  business_date    number(8),    -- 근무일수
  business_work     varchar(30),  -- 근무명
  cdate            timestamp default systimestamp,
  udate            timestamp default systimestamp
  );


--기본키 생성
alter table apply_detail add constraint apply_detail_resume_detail_id_pk primary key(resume_detail_id);

--제약조건
--alter table apply_detail add constraint apply_detail_resume_id_fk
--foreign key (resume_id) REFERENCES resume_apply(resume_id);

--시퀀스
create sequence apply_detail_resume_detail_id_seq;
desc apply_detail;

commit;
