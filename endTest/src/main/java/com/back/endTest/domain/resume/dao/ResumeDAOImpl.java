package com.back.endTest.domain.resume.dao;


import com.back.endTest.domain.resume.Resume;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@Repository
@RequiredArgsConstructor
public class ResumeDAOImpl implements ResumeDAO {

  private final JdbcTemplate jdbcTemplate;

  /**
   * 등록
   *
   * @param resume
   * @return
   */
  @Override
  public Resume create(Resume resume) {
    //SQL작성
    StringBuffer sql = new StringBuffer();
    sql.append("insert into member_resume (resume_id, id_person, resume_title, self_intro) ");
    sql.append("values(member_resume_resume_id_seq.nextval, ?, ?, ?) ");

    //SQL 실행
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(new PreparedStatementCreator() {
      @Override
      public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
        PreparedStatement pstms = con.prepareStatement(
            sql.toString(),
            new String[]{"resume_id"} // insert 후 insert 레코드중 반환할 컬럼명, KeyHolder에 저장됨.
        );

        pstms.setString(1, resume.getIdPerson());
        pstms.setString(2,resume.getResumeTitle());
        pstms.setString(3, resume.getSelfIntro());
//        pstms.setString(4,resume.getCDate());
//        pstms.setString(5, resume.getUDate());

        return pstms;
      }
    }, keyHolder);

    long resume_id = Long.valueOf(keyHolder.getKeys().get("resume_id").toString());
    return selectOne(resume_id);
  }


  /**
   * 상세조회
   *
   * @param resumeId
   * @return
   */
  @Override
  public Resume selectOne(Long resumeId) {
    StringBuffer sql = new StringBuffer();
    sql.append("select resume_id, id_person, resume_title, self_intro , cdate ");
    sql.append(" from member_resume ");
    sql.append("where resume_id = ? ");

    List<Resume> query = jdbcTemplate.query(
        sql.toString(), new BeanPropertyRowMapper<>(Resume.class), resumeId);


    return (query.size() == 1) ? query.get(0) : null;
  }

  /**
   * 수정
   *
   * @param resume
   * @return
   */
  @Override
  public Resume update(Resume resume) {

    StringBuffer sql = new StringBuffer();
    sql.append("update member_resume ");
    sql.append("set self_intro = ?, ");
    sql.append("    resume_title = ?, ");
    sql.append("    udate = ? ");
    sql.append(" where resume_id = ? ");

    KeyHolder keyHolder = new GeneratedKeyHolder();

    jdbcTemplate.update(new PreparedStatementCreator() {
      @Override
      public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(
            sql.toString(),
            new String[]{"resume_id"} // update 후 update 레코드중 반환할 컬럼명, keyHolder에 저장됨.
        );

        pstmt.setString(1, resume.getSelfIntro());
        pstmt.setString(2, resume.getResumeTitle());
        pstmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
        pstmt.setLong(4, resume.getResumeId());

        return pstmt;
      }
    }, keyHolder);

    long resume_id = Long.valueOf(keyHolder.getKeys().get("resume_id").toString());
    return selectOne(resume_id);
  }

  @Override
  public int delete(Long resumeId) {

    StringBuffer sql = new StringBuffer();
    sql.append("delete from member_resume ");
    sql.append(" where resume_id = ? ");

    int cnt = jdbcTemplate.update(sql.toString(), resumeId);

    return cnt;
  };


  @Override
  public List<Resume> selectAll(String idPerson) {

    StringBuffer sql = new StringBuffer();
    sql.append("select resume_id, id_person, resume_title, self_intro, cdate, udate ");
    sql.append("from member_resume ");
    sql.append("where id_person = ? ");
    sql.append("order by resume_id asc ");


    List<Resume> list = jdbcTemplate.query(
      sql.toString(),
      new BeanPropertyRowMapper<>(Resume.class),
      idPerson);

    return list;
  }
}
