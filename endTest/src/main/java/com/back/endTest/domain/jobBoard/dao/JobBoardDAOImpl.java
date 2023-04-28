package com.back.endTest.domain.jobBoard.dao;

import com.back.endTest.domain.entity.JobBoard;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class JobBoardDAOImpl implements JobBoardDAO{

  private final NamedParameterJdbcTemplate template;
  private final JdbcTemplate jdbcTemplate;

  //등록
  @Override
  public Long save(JobBoard jobBoard) {
    StringBuffer sql = new StringBuffer();
    sql.append(" insert into job_board( Job_board_ID_pk, ");
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
    sql.append(" ) ");

    sql.append(" values( Job_board_Job_board_ID_pk_seq.nextval, ");
    sql.append(" :titleJob, ");
    sql.append(" :idJob, ");
    sql.append(" :closingDate, ");
    sql.append(" :numberOfPersons, ");
    sql.append(" :genderJob, ");
    sql.append(" :academicAbility, ");
    sql.append(" :salaryWay, ");
    sql.append(" :salaryAmount, ");
    sql.append(" :workPeriod, ");
    sql.append(" :workDay, ");
    sql.append(" :comeInJob, ");
    sql.append(" :comeOutJob, ");
    sql.append(" :workTypeJob, ");
    sql.append(" :employForm, ");
    sql.append(" :benefitJob, ");
    sql.append(" :placeName, ");
    sql.append(" :placeAddress, ");
    sql.append(" :detailContent, ");
    sql.append(" :managerName, ");
    sql.append(" :managerPhone ");
    sql.append(" ) ");


    SqlParameterSource param = new BeanPropertySqlParameterSource(jobBoard);
    KeyHolder keyHolder = new GeneratedKeyHolder();
    template.update(sql.toString(), param, keyHolder, new String[]{"job_board_id_pk"});
    long jobBoardIdPk = keyHolder.getKey().longValue();
    return jobBoardIdPk;
  }

  //조회
  @Override
  public Optional<JobBoard> inquiry(Long jobBoardIdPk) {
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
    sql.append(" from Job_board ");
    sql.append(" where job_board_id_pk = :id ");

    try {
      Map<String, Long> param = Map.of("id", jobBoardIdPk);
      JobBoard jobBoard = template.queryForObject(
        sql.toString(), param, BeanPropertyRowMapper.newInstance(JobBoard.class)
      );
      return Optional.of(jobBoard);
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  //수정
  @Override
  public int update(Long jobBoardIdPk, JobBoard jobBoard) {
    StringBuffer sql = new StringBuffer();
    sql.append(" update Job_board ");
    sql.append(" set title_job = :titleJob, ");
    sql.append(" closing_date = :closingDate, ");
    sql.append(" number_of_persons = :numberOfPersons, ");
    sql.append(" gender_job = :genderJob, ");
    sql.append(" academic_ability = :academicAbility, ");
    sql.append(" salary_way = :salaryWay, ");
    sql.append(" salary_amount = :salaryAmount, ");
    sql.append(" work_period = :workPeriod, ");
    sql.append(" work_day = :workDay, ");
    sql.append(" come_in_job = :comeInJob, ");
    sql.append(" come_out_job = :comeOutJob, ");
    sql.append(" work_type_job = :workTypeJob, ");
    sql.append(" employ_form = :employForm, ");
    sql.append(" benefit_job = :benefitJob, ");
    sql.append(" place_name = :placeName, ");
    sql.append(" place_address = :placeAddress, ");
    sql.append(" detail_content = :detailContent, ");
    sql.append(" manager_name = :managerName, ");
    sql.append(" manager_phone = :managerPhone ");
    sql.append(" where Job_board_ID_pk = :id ");

    SqlParameterSource param = new MapSqlParameterSource()
      .addValue("titleJob", jobBoard.getTitleJob())
      .addValue("closingDate", jobBoard.getClosingDate())
      .addValue("numberOfPersons", jobBoard.getNumberOfPersons())
      .addValue("genderJob", jobBoard.getGenderJob())
      .addValue("academicAbility", jobBoard.getAcademicAbility())
      .addValue("salaryWay", jobBoard.getSalaryWay())
      .addValue("salaryAmount", jobBoard.getSalaryAmount())
      .addValue("workPeriod", jobBoard.getWorkPeriod())
      .addValue("workDay", jobBoard.getWorkDay())
      .addValue("comeInJob", jobBoard.getComeInJob())
      .addValue("comeOutJob", jobBoard.getComeOutJob())
      .addValue("workTypeJob", jobBoard.getWorkTypeJob())
      .addValue("employForm", jobBoard.getEmployForm())
      .addValue("benefitJob", jobBoard.getBenefitJob())
      .addValue("placeName", jobBoard.getPlaceName())
      .addValue("placeAddress", jobBoard.getPlaceAddress())
      .addValue("detailContent", jobBoard.getDetailContent())
      .addValue("managerName", jobBoard.getManagerName())
      .addValue("managerPhone", jobBoard.getManagerPhone())
      .addValue("id", jobBoardIdPk);
    return template.update(sql.toString(), param);
  }

  //삭제
  @Override
  public int delete(Long jobBoardIdPk) {
    String sql = " delete from job_board where Job_board_ID_pk = :id ";
    return template.update(sql,Map.of("id", jobBoardIdPk));
  }

  //전체 목록
  @Override
  public List<JobBoard> findAll() {
    StringBuffer sql = new StringBuffer();
    sql.append(" select job_board_id_pk, ");
    sql.append(" title_job, ");
    sql.append(" id_job, ");
    sql.append(" salary_way, ");
    sql.append(" salary_amount, ");
    sql.append(" come_in_job, ");
    sql.append(" come_out_job, ");
    sql.append(" place_name, ");
    sql.append(" place_address ");
    sql.append(" from Job_board ");
    sql.append(" order by job_board_ID_PK DESC ");

    List<JobBoard> list = template.query(
      sql.toString(),
      BeanPropertyRowMapper.newInstance(JobBoard.class)
    );

    return list;
  }
  // 목록 페이징
  @Override
  public List<JobBoard> findAllPaging(int startRec, int endRec) {
    StringBuffer sql = new StringBuffer();
    sql.append(" select t2.* ");
    sql.append(" from(select rownum no, t1.job_board_id_pk, t1.title_job, ");
    sql.append(" t1.id_job, t1.salary_way, t1.salary_amount, t1.come_in_job, ");
    sql.append(" t1.come_out_job, t1.place_name, t1.place_address ");
    sql.append(" from job_board t1 order by job_board_ID_PK DESC) t2 ");
    sql.append(" where no between :startRec and :endRec ");

    Map<String, Integer> param = Map.of("startRec", startRec, "endRec", endRec);
    List<JobBoard> listPaging = template.query(
      sql.toString(),
      param,
      BeanPropertyRowMapper.newInstance(JobBoard.class)
    );

    return listPaging;
  }


  @Override
  public boolean isExist(Long jobBoardIdPk) {
    boolean isExist = false;
    String sql = " select count(*) from Job_board where job_board_id_pk = :id ";

    Map<String, Long> param = Map.of("id", jobBoardIdPk);
    Integer integer = template.queryForObject(sql, param, Integer.class);
    isExist = (integer > 0) ? true : false;
    return isExist;
  }

  @Override
  public int totalCount() {
    String sql = "select count(*) from job_board";

    Integer cnt = jdbcTemplate.queryForObject(sql, Integer.class);
    return cnt;
  }
}
