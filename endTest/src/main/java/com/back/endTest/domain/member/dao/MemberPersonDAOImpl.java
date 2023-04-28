package com.back.endTest.domain.member.dao;

import com.back.endTest.domain.entity.MemberPerson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class MemberPersonDAOImpl implements MemberPersonDAO {

  public final NamedParameterJdbcTemplate template;

  //회원가입
  @Override
  public MemberPerson singUp(MemberPerson memberPerson) {

    StringBuffer sql = new StringBuffer();
    sql.append(" insert into member_person (person_id_pk, id_person, pw_person, pw_chk_person, name_person, birth_person, gender_person, address_person, detail_address_person, email_person, phone_person  ) ");
    sql.append(" values(member_person_person_id_pk_seq.nextval, :idPerson, :pwPerson, :pwChkPerson, :namePerson, :birthPerson, :genderPerson, :addressPerson, :detailAddressPerson, :emailPerson, :phonePerson) ");

    SqlParameterSource param = new BeanPropertySqlParameterSource(memberPerson);
    KeyHolder keyHolder = new GeneratedKeyHolder(); //테이블 컬럼 값 추출
    template.update(sql.toString(), param, keyHolder, new String[]{"person_id_pk"});

    long personIdPk = keyHolder.getKey().longValue();
    memberPerson.setPersonIdPk(personIdPk);

    return memberPerson;
  }

  //회원정보 보기
  @Override
  public Optional<MemberPerson> memberInfo(Long personIdPk) {
    StringBuffer sql = new StringBuffer();
    sql.append(" select id_person, name_person, birth_person, gender_person, address_person, detail_address_person, email_person, phone_person ");
    sql.append(" from member_person ");
    sql.append(" where person_id_pk = :id ");

    Map<String, Long> param = Map.of("id", personIdPk);
    MemberPerson memberPerson = template.queryForObject(sql.toString(), param, BeanPropertyRowMapper.newInstance(MemberPerson.class));

    return Optional.of(memberPerson);
  }


  //회원정보 수정
  @Override
  public int memberUpdate(Long personIdPk, MemberPerson memberPerson) {
    StringBuffer sql = new StringBuffer();
    sql.append(" update member_person ");
    sql.append(" set name_person = :namePerson,  address_person = :addressPerson, detail_address_person = :detailAddressPerson, email_person = :emailPerson, phone_person = :phonePerson ");
    sql.append(" where person_id_pk = :id ");

    SqlParameterSource param = new MapSqlParameterSource()
      .addValue("namePerson", memberPerson.getNamePerson())
      .addValue("addressPerson", memberPerson.getAddressPerson())
      .addValue("detailAddressPerson", memberPerson.getDetailAddressPerson())
      .addValue("emailPerson", memberPerson.getEmailPerson())
      .addValue("phonePerson", memberPerson.getPhonePerson())
      .addValue("id", personIdPk);

    return template.update(sql.toString(), param);
  }

  //회원 탈퇴
  @Override
  public int memberDelete(Long personIdPk) {
    StringBuffer sql = new StringBuffer();
    sql.append(" delete from member_person where person_id_pk = :personIdPk ");

    Map<String, Long> param = Map.of("personIdPk", personIdPk);
    return template.update(sql.toString(), param);
  }


  //회원 아이디 찾기
  @Override
  public MemberPerson findById(String idPerson) {
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
    MemberPerson memberPerson = template.queryForObject(
      sql.toString(),
      param,
      BeanPropertyRowMapper.newInstance(MemberPerson.class)
    );
    return memberPerson;
  }


  //회원 비번 찾기
  @Override
  public Optional<MemberPerson> findByPw(String pwPerson) {
    StringBuffer sql = new StringBuffer();
    sql.append("");

    Map<String, String> param = Map.of("pwPerson", pwPerson);
    MemberPerson memberPerson = template.queryForObject(
      sql.toString(),
      param,
      BeanPropertyRowMapper.newInstance(MemberPerson.class)
    );
    return Optional.of(memberPerson);
  }

  //회원 존재 유무
  @Override
  public boolean isExist(String idPerson) {
    String sql = " select count(id_person) from member_person where id_person = :idPerson ";

    Map<String, String> param = Map.of("idPerson", idPerson);
    Integer cnt = template.queryForObject(sql, param, Integer.class);

    return cnt == 1 ? true : false;
  }

  //로그인-개인
  @Override
  public Optional<MemberPerson> login(String idPerson, String pwPerson) {
    StringBuffer sql = new StringBuffer();
    sql.append(" select person_id_pk, id_person, name_person  ");
    sql.append(" from member_person ");
    sql.append(" where id_person = :idPerson and pw_person = :pwPerson ");

    Map<String, String> param = Map.of("idPerson", idPerson, "pwPerson", pwPerson);
    List<MemberPerson> list = template.query(
      sql.toString(),
      param,
      BeanPropertyRowMapper.newInstance(MemberPerson.class) //자바객체 <=> 테이블 레코드 자동 매핑
    );
    return list.size() == 1 ? Optional.of(list.get(0)) : Optional.empty();
  }
}
