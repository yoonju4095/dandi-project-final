package com.back.endTest.domain.resumeApply.dao;


import com.back.endTest.domain.resumeApply.ResumeApplyDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Slf4j
@Repository
@RequiredArgsConstructor
public class ResumeApplyDetailDAOImpl implements ResumeApplyDetailDAO {

  private final JdbcTemplate jdbcTemplate;

  @Override
  public void apply(List<ResumeApplyDetail> resumeApplyDetails) {

    //SQL작성
    StringBuffer sql = new StringBuffer();
    sql.append("insert into apply_detail(resume_detail_id, resume_id, resume_item_id,id_person, business_name, business_date, business_work) ");
    sql.append("values(resume_detail_resume_detail_id_seq.nextval, ?, ?, ?, ?, ?, ?)  ");

    //SQL실행
    jdbcTemplate.batchUpdate(
        sql.toString(),
        resumeApplyDetails,
        10,
        (ps, resumeApplyDetail)-> {
          ps.setLong(1, resumeApplyDetail.getResumeId());
          ps.setLong(2, resumeApplyDetail.getResumeItemId());
          ps.setString(3, resumeApplyDetail.getIdPerson());
          ps.setString(4, resumeApplyDetail.getBusinessName());
          ps.setLong(5, resumeApplyDetail.getBusinessDate());
          ps.setString(6, resumeApplyDetail.getBusinessWork());
        });
  }


  @Override
  public List<ResumeApplyDetail> findApply(Long resumeDetailId) {

    StringBuffer sql = new StringBuffer();
    sql.append("select resume_detail_id, resume_id, resume_item_id, id_person, business_name, business_date, business_work ");
    sql.append("  from apply_detail ");
    sql.append(" where resume_id = ? ");

    List<ResumeApplyDetail> applyDetailList = jdbcTemplate.query(
        sql.toString(), new BeanPropertyRowMapper<>(ResumeApplyDetail.class), resumeDetailId);

    return applyDetailList;
  }


  @Override
  public int delete(Long resumeId) {

    StringBuffer sql = new StringBuffer();
    sql.append("delete from apply_detail ");
    sql.append(" where resume_id = ? ");

    int cnt = jdbcTemplate.update(sql.toString(), resumeId);


    return cnt;
  }
}