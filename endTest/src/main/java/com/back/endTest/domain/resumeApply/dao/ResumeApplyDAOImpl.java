package com.back.endTest.domain.resumeApply.dao;

import com.back.endTest.domain.resumeApply.ResumeApply;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Slf4j
@Repository
@RequiredArgsConstructor
public class ResumeApplyDAOImpl implements ResumeApplyDAO{

  private final NamedParameterJdbcTemplate template;
  private final JdbcTemplate jdbcTemplate;

  @Override
  public ResumeApply apply(ResumeApply resumeApply) {

    StringBuffer sql = new StringBuffer();
    sql.append("insert into resume_apply (resume_apply_id, resume_id, id_person, resume_title, self_intro, job_board_id_pk, title_job, id_job, closing_date, number_of_persons, gender_job, academic_ability, salary_way, salary_amount, work_period, work_day, come_in_job, come_out_job, work_type_job, employ_form, benefit_job, place_name, place_address, detail_content, manager_name, manager_phone, name_person, birth_person, gender_person, address_person, detail_address_person, email_person, phone_person  ) ");
    sql.append("values(resume_apply_resume_apply_id_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ");

    //SQL 실행
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(new PreparedStatementCreator() {
      @Override
      public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
        PreparedStatement pstms = con.prepareStatement(
            sql.toString(),
            new String[]{"resume_apply_id"} // insert 후 insert 레코드중 반환할 컬럼명, KeyHolder에 저장됨.
        );

        pstms.setLong(1, resumeApply.getResumeId());
        pstms.setString(2,resumeApply.getIdPerson());
        pstms.setString(3, resumeApply.getResumeTitle());
        pstms.setString(4, resumeApply.getSelfIntro());
        pstms.setLong(5, resumeApply.getJobBoardIdPk());
        pstms.setString(6, resumeApply.getTitleJob());
        pstms.setString(7, resumeApply.getIdJob());
        pstms.setString(8,resumeApply.getClosingDate());
        pstms.setString(9,resumeApply.getNumberOfPersons());
        pstms.setString(10, resumeApply.getGenderJob());
        pstms.setString(11, resumeApply.getAcademicAbility());
        pstms.setString(12,resumeApply.getSalaryWay());
        pstms.setLong(13, resumeApply.getSalaryAmount());
        pstms.setString(14, resumeApply.getWorkPeriod());
        pstms.setString(15, resumeApply.getWorkDay());
        pstms.setString(16, resumeApply.getComeInJob());
        pstms.setString(17, resumeApply.getComeOutJob());
        pstms.setString(18, resumeApply.getWorkTypeJob());
        pstms.setString(19, resumeApply.getEmployForm());
        pstms.setString(20, resumeApply.getBenefitJob());
        pstms.setString(21, resumeApply.getPlaceName());
        pstms.setString(22, resumeApply.getPlaceAddress());
        pstms.setString(23, resumeApply.getDetailContent());
        pstms.setString(24, resumeApply.getManagerName());
        pstms.setString(25, resumeApply.getManagerPhone());
        pstms.setString(26, resumeApply.getNamePerson());
        pstms.setString(27, resumeApply.getBirthPerson());
        pstms.setString(28, resumeApply.getGenderPerson());
        pstms.setString(29, resumeApply.getAddressPerson());
        pstms.setString(30, resumeApply.getDetailAddressPerson());
        pstms.setString(31, resumeApply.getEmailPerson());
        pstms.setString(32, resumeApply.getPhonePerson());


        return pstms;
      }
    }, keyHolder);

    long resume_apply_id = Long.valueOf(keyHolder.getKeys().get("resume_apply_id").toString());
    return findApply(resume_apply_id);
  }

  @Override
  public ResumeApply findApply(Long resumeApplyId) {

    StringBuffer sql = new StringBuffer();
    sql.append("select resume_apply_id, resume_id, id_person, resume_title, self_intro, job_board_id_pk, title_job, id_job, closing_date, number_of_persons, gender_job, academic_ability, salary_way, salary_amount, work_period, work_day, come_in_job, come_out_job, work_type_job, employ_form, benefit_job, place_name, place_address, detail_content, manager_name, manager_phone, name_person, birth_person, gender_person, address_person, detail_address_person, email_person, phone_person ");
    sql.append("  from resume_apply ");
    sql.append("where resume_apply_id = ? ");

    List<ResumeApply> query = jdbcTemplate.query(
        sql.toString(), new BeanPropertyRowMapper<>(ResumeApply.class), resumeApplyId);


    return (query.size() == 1) ? query.get(0) : null;
  }


  @Override
  public List<ResumeApply> findAll(String idPerson) {

    StringBuffer sql = new StringBuffer();
    sql.append("select resume_apply_id, resume_id, id_person, resume_title, self_intro, job_board_id_pk, title_job, id_job, closing_date, number_of_persons, gender_job, academic_ability, salary_way, salary_amount, work_period, work_day, come_in_job, come_out_job, work_type_job, employ_form, benefit_job, place_name, place_address, detail_content, manager_name, manager_phone, name_person, birth_person, gender_person, address_person, detail_address_person, email_person, phone_person, cdate ");
    sql.append("from resume_apply ");
    sql.append("where id_person = ? ");
//    sql.append("order by resume_id asc ");


    List<ResumeApply> list1 = jdbcTemplate.query(
        sql.toString(), new BeanPropertyRowMapper<>(ResumeApply.class),idPerson);

    return list1;
  }

  @Override
  public List<ResumeApply> findAllCompany(String idJob) {

    StringBuffer sql = new StringBuffer();
    sql.append("select resume_apply_id, resume_id, id_person, resume_title, self_intro, job_board_id_pk, title_job, id_job, closing_date, number_of_persons, gender_job, academic_ability, salary_way, salary_amount, work_period, work_day, come_in_job, come_out_job, work_type_job, employ_form, benefit_job, place_name, place_address, detail_content, manager_name, manager_phone, name_person, birth_person, gender_person, address_person, detail_address_person, email_person, phone_person, cdate ");
    sql.append("from resume_apply ");
    sql.append("where id_job = ? ");
//    sql.append("order by resume_id asc ");


    List<ResumeApply> list2 = jdbcTemplate.query(
        sql.toString(), new BeanPropertyRowMapper<>(ResumeApply.class),idJob);

    return list2;
  }




  @Override
  public Optional<ResumeApply> findByJobBoardIdPk(Long jobBoardIdPk) {
    StringBuffer sql = new StringBuffer();
    sql.append(" select job_board_id_pk, ");
    sql.append(" title_job, ");
    sql.append(" id_job, ");
    sql.append(" closing_date, ");
    sql.append(" number_of_persons, ");
    sql.append(" gender_job, ");
    sql.append(" academic_ability, ");
    sql.append(" salary_way, ");
    sql.append(" salary_amount, ");
    sql.append(" work_period, ");
    sql.append(" work_day, ");
    sql.append(" come_in_job, ");
    sql.append(" come_out_job, ");
    sql.append(" work_type_job, ");
    sql.append(" employ_form, ");
    sql.append(" benefit_job, ");
    sql.append(" place_name, ");
    sql.append(" place_address, ");
    sql.append(" detail_content, ");
    sql.append(" manager_name, ");
    sql.append(" manager_phone ");
    sql.append(" from resume_apply ");
    sql.append(" where job_board_id_pk = :jobBoardIdPk ");

    try {
      Map<String, Long> param = Map.of("jobBoardIdPk", jobBoardIdPk);
      ResumeApply resumeApply = template.queryForObject(
          sql.toString(), param, BeanPropertyRowMapper.newInstance(ResumeApply.class)
      );
      return Optional.of(resumeApply);
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  //회원 아이디 찾기
  @Override
  public ResumeApply findByIdPerson(String idPerson) {
    StringBuffer sql = new StringBuffer();
    sql.append("select  PERSON_ID_PK,  ");
    sql.append("         ID_PERSON, ");
    sql.append("        PW_PERSON, ");
    sql.append("        NAME_PERSON, ");
    sql.append("        BIRTH_PERSON, ");
    sql.append("        GENDER_PERSON, ");
    sql.append("        ADDRESS_PERSON, ");
    sql.append("        DETAIL_ADDRESS_PERSON, ");
    sql.append("        EMAIL_PERSON, ");
    sql.append("        PHONE_PERSON ");
    sql.append("  from MEMBER_PERSON ");
    sql.append(" where ID_PERSON = :idPerson ");

    Map<String, String> param = Map.of("idPerson", idPerson);
    ResumeApply resumeApply = template.queryForObject(
        sql.toString(),
        param,
        BeanPropertyRowMapper.newInstance(ResumeApply.class)
    );
    return resumeApply;
  }



  @Override
  public int delete(Long jobBoardIdPk) {

    StringBuffer sql = new StringBuffer();
    sql.append("delete from resume_apply ");
    sql.append(" where job_board_id_pk = ? ");

    int cnt = jdbcTemplate.update(sql.toString(), jobBoardIdPk);

    return cnt;
  }
}
