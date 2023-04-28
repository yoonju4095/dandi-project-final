package com.back.endTest.domain.jobReview.dao;

import com.back.endTest.domain.entity.JobReview;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
public class JobReviewDAOImpl implements JobReviewDAO{

  private final NamedParameterJdbcTemplate template;

  //후기글 등록
  @Override
  public Long save(JobReview jobReview) {
    StringBuffer sql = new StringBuffer();
    sql.append(" insert into job_review( ");
    sql.append(" review_ID_PK, ");
    sql.append(" title_review, ");
    sql.append(" id_review, ");
    sql.append(" content_review, ");
//    sql.append(" work_type_review, ");
//    sql.append(" work_period_review, ");
//    sql.append(" work_day_review, ");
//    sql.append(" come_in_review, ");
//    sql.append(" come_out_review, ");
    sql.append(" assess_review, ");
    sql.append(" focus_job_board_id ");
    sql.append(" ) ");
    sql.append(" values(Job_review_review_ID_PK_seq.nextval, ");
    sql.append(" :titleReview, ");
    sql.append(" :idReview, ");
    sql.append(" :contentReview, ");
//    sql.append(" :workTypeReview, ");
//    sql.append(" :workPeriodReview, ");
//    sql.append(" :workDayReview, ");
//    sql.append(" :comeInReview, ");
//    sql.append(" :comeOutReview, ");
    sql.append(" :assessReview,  ");
    sql.append(" :focusJobBoardId ) ");

    SqlParameterSource param = new BeanPropertySqlParameterSource(jobReview);
    KeyHolder keyHolder = new GeneratedKeyHolder();
    template.update(sql.toString(), param, keyHolder, new String[]{"review_ID_PK"});
    long reviewIdPk = keyHolder.getKey().longValue();

    return reviewIdPk;
  }

  //후기글 조회
  @Override
  public Optional<JobReview> inquiry(Long reviewIdPk) {
    StringBuffer sql = new StringBuffer();
    sql.append(" select review_ID_PK, ");
    sql.append(" title_review, ");
    sql.append(" id_review, ");
    sql.append(" content_review, ");
    sql.append(" assess_review ");
    sql.append(" from job_review ");
    sql.append(" where review_ID_PK = :id ");

    try {
      Map<String, Long> param = Map.of("id", reviewIdPk);
      JobReview jobReview = template.queryForObject(
        sql.toString(), param, BeanPropertyRowMapper.newInstance(JobReview.class)
      );
      return Optional.of(jobReview);
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  //후기글 수정
  @Override
  public int update(Long reviewIdPk, JobReview jobReview) {
    StringBuffer sql = new StringBuffer();
    sql.append(" update job_review ");
    sql.append(" set title_review = :titleReview, ");
    sql.append(" content_review = :contentReview, ");
    sql.append(" assess_review = :assessReview ");
    sql.append(" where review_ID_PK = :id ");

    SqlParameterSource param = new MapSqlParameterSource()
      .addValue("titleReview", jobReview.getTitleReview())
      .addValue("contentReview", jobReview.getContentReview())
      .addValue("assessReview", jobReview.getAssessReview())
      .addValue("id", reviewIdPk);

    return template.update(sql.toString(), param);
  }

  //후기글 삭제
  @Override
  public int delete(Long reviewIdPk) {
    String sql = " delete from job_review where review_ID_PK = :id ";
    return template.update(sql,Map.of("id", reviewIdPk));
  }

  //후기글 목록
  @Override
  public List<JobReview> findAll(Long focusJobBoardId) {
    StringBuffer sql = new StringBuffer();
    sql.append(" select review_ID_PK, ");
    sql.append(" title_review, ");
    sql.append(" id_review, ");
    sql.append(" content_review, ");
    sql.append(" assess_review, ");
    sql.append(" FOCUS_JOB_BOARD_ID ");
    sql.append(" from job_review ");
    sql.append(" where FOCUS_JOB_BOARD_ID = :id ");

    Map<String, Long> param = Map.of("id", focusJobBoardId);

    List<JobReview> list = template.query(
      sql.toString(),
      param,
      BeanPropertyRowMapper.newInstance(JobReview.class)
    );

    return list;
  }

  @Override
  public boolean isExist(Long reviewIdPk) {
    boolean isExist = false;
    String sql = " select count(*) from job_review where review_ID_PK = :id ";

    Map<String, Long> param = Map.of("id", reviewIdPk);
    Integer integer = template.queryForObject(sql, param, Integer.class);
    isExist = (integer > 0) ? true : false;
    return isExist;
  }
}
