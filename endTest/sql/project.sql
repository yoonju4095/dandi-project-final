--테이블 삭제
drop table member_person;
drop table member_company;
drop table Job_board;
drop table schedule;
drop table Job_review;
--시퀸스 삭제
drop sequence member_person_person_id_pk_seq;
drop sequence member_company_company_id_pk_seq;
drop sequence Job_board_Job_board_ID_pk_seq;
drop sequence Job_review_review_ID_PK_seq;
drop sequence schedule_schedule_ID_PK_seq;
--

--회원(member)
--테이블 생성(개인)
create table member_person (
  person_id_pk  number,
  id_person varchar2(20),
  pw_person varchar2(30),
  pw_chk_person varchar2(30),
  name_person varchar2(30),
  birth_person varchar2(10),
  gender_person char(3),
  address_person varchar2(300),
  detail_address_person varchar2(300),
  email_person varchar2(40),
  phone_person varchar2(11),
  profile_person blob,
  cdate_date_person timestamp default systimestamp,
  udate_date_person timestamp default systimestamp
);
--기본키 생성
alter table member_person add constraint member_person_person_id_pk primary key(person_id_pk);

--제약 조건
alter table member_person modify id_person constraint member_person_id_person_uk unique;

alter table member_person modify id_person constraint member_person_id_person_nn not null;
alter table member_person modify pw_person constraint member_person_pw_person_nn not null;
alter table member_person modify pw_chk_person constraint member_person_pw_chk_person_nn not null;
alter table member_person modify name_person constraint member_person_name_person_nn not null;
alter table member_person modify birth_person constraint member_person_birth_person_nn not null;
alter table member_person modify address_person constraint member_person_address_person_nn not null;
alter table member_person modify detail_address_person constraint member_person_detail_address_person_nn not null;
alter table member_person modify email_person constraint member_person_email_person_nn not null;
alter table member_person modify phone_person constraint member_person_phone_person_nn not null;

alter table member_person add constraint member_person_gender_person_ck check (gender_person in ('남','여'));

--시퀀스 생성
create sequence member_person_person_id_pk_seq;
desc member_person;

--회원가입
insert into member_person (person_id_pk, id_person, pw_person, pw_chk_person, name_person, birth_person, gender_person, address_person, detail_address_person, email_person, phone_person  )
  values(member_person_person_id_pk_seq.nextval, :id_person, :pw_person, :pw_chk_person, :name_person, :birth_person, :gender_person, :address_person, :detail_address_person, :email_person, :phone_person);

--

--테이블 생성(기업)
create table member_company (
  company_id_pk  number,
  id_company varchar2(20),
  pw_company varchar2(30),
  pw_chk_company varchar2(30),
  name_company varchar2(30),
  business_company varchar2(20),
  address_company varchar2(300),
  detail_address_company varchar2(300),
  email_company varchar2(40),
  phone_company varchar2(11),
  profile_company blob,
  cdate_date_company timestamp default systimestamp,
  udate_date_company timestamp default systimestamp
);
--기본키 생성
alter table member_company add constraint member_company_company_id_pk primary key(company_id_pk);

--제약 조건
alter table member_company modify id_company constraint member_company_id_company_uk unique;

alter table member_company modify id_company constraint member_company_id_company_nn not null;
alter table member_company modify pw_company constraint member_company_pw_company_nn not null;
alter table member_company modify pw_chk_company constraint member_company_pw_chk_company_nn not null;
alter table member_company modify name_company constraint member_company_name_company_nn not null;
alter table member_company modify business_company constraint member_company_business_company_nn not null;
alter table member_company modify address_company constraint member_company_address_company_nn not null;
alter table member_company modify detail_address_company constraint member_company_detail_address_company_nn not null;
alter table member_company modify email_company constraint member_company_email_company_nn not null;
alter table member_company modify phone_company constraint member_company_phone_company_nn not null;


--시퀀스 생성
create sequence member_company_company_id_pk_seq;
desc member_company;

--회원가입
insert into member_company (company_id_pk, id_company, pw_company, pw_chk_company, name_company, business_company, address_company, detail_address_company, email_company, phone_company )
  values(member_company_company_id_pk_seq.nextval, :id_company, :pw_company, :pw_chk_company, :name_company, :business_company, :address_company, :detail_address_company, :email_company, :phone_company);

--

--구인게시판(job_board)
--테이블 생성
create table Job_board (
  Job_board_ID_pk  NUMBER(10),
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
  CDATE_job    TIMESTAMP default systimestamp,
  UDATE_job    TIMESTAMP default systimestamp,
  DDATE_job    TIMESTAMP default systimestamp
);
--기본키 생성
alter table Job_board add constraint Job_board_Job_board_ID_pk primary key(Job_board_ID_pk);
--외래키 생성
alter table Job_board add constraint Job_board_id_job foreign key(id_job) references MEMBER_Company(ID_COMPANY);
--제약 조건
alter table Job_board modify title_job constraint Job_board_title_job_nn not null;
alter table Job_board modify closing_date constraint Job_board_closing_date_nn not null;
alter table Job_board modify number_of_persons constraint Job_board_number_of_persons_nn not null;
alter table Job_board modify gender_job constraint Job_board_gender_job_nn not null;
alter table Job_board modify academic_ability constraint Job_board_academic_ability_nn not null;
alter table Job_board modify salary_way constraint Job_board_salary_way_nn not null;
alter table Job_board modify salary_amount constraint Job_board_salary_amount_nn not null;
alter table Job_board modify work_period constraint Job_board_work_period_nn not null;
alter table Job_board modify work_day constraint Job_board_work_day_nn not null;
alter table Job_board modify come_in_job constraint Job_board_come_in_job_nn not null;
alter table Job_board modify come_out_job constraint Job_board_come_out_job_nn not null;
alter table Job_board modify work_type_job constraint Job_board_work_type_job_nn not null;
alter table Job_board modify employ_form constraint Job_board_employ_form_nn not null;
alter table Job_board modify place_name constraint Job_board_place_name_nn not null;
alter table Job_board modify place_address constraint Job_board_place_address_nn not null;
alter table Job_board modify manager_name constraint Job_board_manager_name_nn not null;
alter table Job_board modify manager_phone constraint Job_board_manager_phone_nn not null;
--시퀀스 생성
create sequence Job_board_Job_board_ID_pk_seq;
desc Job_board;
--


--후기글(구인게시판)
--테이블 생성
create table Job_review(
  review_ID_PK      NUMBER(10),
  focus_Job_board_ID  NUMBER(10),
  title_review      VARCHAR2(100),
  id_review     VARCHAR2(40),
  content_review      CLOB,
--  work_type_review      VARCHAR2(50),
--  work_period_review      VARCHAR2(10),
--  work_day_review     VARCHAR2(50),
--  come_in_review      VARCHAR2(10),
--  come_out_review     VARCHAR2(10),
  assess_review     NUMBER(3,2),
  CDATE_review     TIMESTAMP default systimestamp,
  UDATE_review     TIMESTAMP default systimestamp,
  DDATE_review     TIMESTAMP default systimestamp
);
--기본키 생성
alter table Job_review add constraint Job_review_review_ID_PK primary key(review_ID_PK);
--외래키 생성
alter table Job_review add constraint Job_review_id_review foreign key(id_review) references MEMBER_Person(ID_Person);
alter table Job_review add constraint Job_review_focus_Job_board_ID foreign key(focus_Job_board_ID) references Job_board(Job_board_ID_pk);

--alter table Job_review add constraint Job_review_review_ID_PK_FK foreign key(review_ID_PK) references Job_board(Job_board_ID_pk);
--제약 조건
alter table Job_review modify title_review constraint Job_review_title_review_nn not null;
alter table Job_review modify id_review constraint Job_review_id_review_nn not null;
alter table Job_review modify content_review constraint Job_review_content_review_nn not null;
alter table Job_review modify work_type_review constraint Job_review_work_type_review_nn not null;
alter table Job_review modify work_period_review constraint Job_review_work_period_review_nn not null;
alter table Job_review modify work_day_review constraint Job_review_work_day_review_nn not null;
alter table Job_review modify come_in_review constraint Job_review_come_in_review_nn not null;
alter table Job_review modify come_out_review constraint Job_review_come_out_review_nn not null;
alter table Job_review modify assess_review constraint Job_review_assess_review_nn not null;
--시퀀스 생성
create sequence Job_review_review_ID_PK_seq;
desc Job_review;
--

--스케줄(schedule)
--테이블 생성
create table schedule(
    schedule_ID_PK NUMBER(10),
    my_id varchar2(20),
    NAME_schedule VARCHAR2(30),
    ID_schedule VARCHAR2(20),
    work_schedule VARCHAR2(30),
    day_schedule VARCHAR2(30),
    come_in_schedule VARCHAR2(40),
    come_out_schedule VARCHAR2(40),
    period_start VARCHAR2(30),
    period_end VARCHAR2(30),
    cdate_schedule TIMESTAMP default systimestamp,
    udate_schedule TIMESTAMP default systimestamp,
    ddate_schedule TIMESTAMP default systimestamp
);

--기본키
alter table schedule add constraint schedule_schedule_ID_PK primary key(schedule_ID_PK);
--왜래키
alter table schedule add constraint schedule_ID_schedule foreign key(ID_schedule) references MEMBER_Person(id_person);
alter table schedule add constraint schedule_my_id foreign key(my_id) references MEMBER_Company(id_Company);
--제약조건
alter table schedule modify ID_schedule constraint schedule_ID_schedule_nn not null;
alter table schedule modify NAME_schedule constraint schedule_NAME_schedule_nn not null;
alter table schedule modify work_schedule constraint schedule_work_schedule_nn not null;
alter table schedule modify day_schedule constraint schedule_day_schedule_nn not null;
alter table schedule modify come_in_schedule  constraint schedule_come_in_schedule_nn not null;
alter table schedule modify come_out_schedule  constraint schedule_come_out_schedule_nn not null;
alter table schedule modify period_start constraint schedule_period_start_nn not null;
alter table schedule modify period_end constraint schedule_period_end_nn not null;

--시퀀스
create sequence schedule_schedule_ID_PK_seq;
desc schedule;
--
--공지사항(notice)


--고민게시판(trouble_board)

----첨부 파일
--create table upload_file(
--    upload_file_id_pk   number(10),
--    user_id            varchar2(11),
--    rid             varchar2(10),
--    store_filename  varchar2(100),
--    upload_filename varchar2(100),
--    file_size           varchar2(45),
--    file_type           varchar2(100),
--    cdate           timestamp default systimestamp,
--    udate           timestamp default systimestamp
--);
----기본키
--alter table upload_file add constraint upload_file_upload_file_id_pk primary key(upload_file_id_pk);

--외래키
--alter table uploadfile add constraint uploadfile_uploadfile_id_fk
--    foreign key(code) references code(code_id);
--
----제약조건
--alter table uploadfile modify code constraint uploadfile_code_nn not null;
--alter table uploadfile modify rid constraint uploadfile_rid_nn not null;
--alter table uploadfile modify store_filename constraint uploadfile_store_filename_nn not null;
--alter table uploadfile modify upload_filename constraint uploadfile_upload_filename_nn not null;
--alter table uploadfile modify fsize constraint uploadfile_fsize_nn not null;
--alter table uploadfile modify ftype constraint uploadfile_ftype_nn not null;

--시퀀스
--create sequence upload_file_upload_file_id_pk_seq;