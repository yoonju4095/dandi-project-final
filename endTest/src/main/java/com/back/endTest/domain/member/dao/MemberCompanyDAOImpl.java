package com.back.endTest.domain.member.dao;

import com.back.endTest.domain.entity.MemberCompany;
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
public class MemberCompanyDAOImpl implements MemberCompanyDAO {

  public final NamedParameterJdbcTemplate template;

  //회원가입-기업
  @Override
  public MemberCompany singUp(MemberCompany memberCompany) {
    StringBuffer sql = new StringBuffer();
    sql.append(" insert into member_company (company_id_pk, id_company, pw_company, pw_chk_company, name_company, business_company, address_company, detail_address_company, email_company, phone_company ) ");
    sql.append(" values(member_company_company_id_pk_seq.nextval, :idCompany, :pwCompany, :pwChkCompany, :nameCompany, :businessCompany, :addressCompany, :detailAddressCompany, :emailCompany, :phoneCompany ) ");

    SqlParameterSource param = new BeanPropertySqlParameterSource(memberCompany);
    KeyHolder keyHolder = new GeneratedKeyHolder(); //테이블 컬럼 값 추출
    template.update(sql.toString(), param, keyHolder, new String[]{"company_id_pk"});

    long companyIdPk = keyHolder.getKey().longValue();
    memberCompany.setCompanyIdPk(companyIdPk);

    return memberCompany;
  }

  //회원정보 보기-기업
  @Override
  public Optional<MemberCompany> memberInfo(Long companyIdPk) {
    StringBuffer sql = new StringBuffer();
    sql.append(" select id_company, name_company, business_company, address_company, detail_address_company, email_company, phone_company ");
    sql.append(" from member_company ");
    sql.append(" where company_id_pk = :id ");

    Map<String, Long> param = Map.of("id", companyIdPk);
    MemberCompany memberCompany = template.queryForObject(sql.toString(), param, BeanPropertyRowMapper.newInstance(MemberCompany.class));

    return Optional.of(memberCompany);
  }

  //회원정보 수정-기업
  @Override
  public int memberUpdate(Long companyIdPk, MemberCompany memberCompany) {
    StringBuffer sql = new StringBuffer();
    sql.append(" update member_company ");
    sql.append(" set name_company = :nameCompany, business_company = :businessCompany, address_company = :addressCompany, detail_address_company = :detailAddressCompany, email_company = :emailCompany, phone_company = :phoneCompany ");
    sql.append(" where company_id_pk = :id ");

    SqlParameterSource param = new MapSqlParameterSource()
      .addValue("nameCompany", memberCompany.getNameCompany())
      .addValue("businessCompany", memberCompany.getBusinessCompany())
      .addValue("addressCompany", memberCompany.getAddressCompany())
      .addValue("detailAddressCompany", memberCompany.getDetailAddressCompany())
      .addValue("emailCompany", memberCompany.getEmailCompany())
      .addValue("phoneCompany", memberCompany.getPhoneCompany())
      .addValue("id", companyIdPk);

    return template.update(sql.toString(), param);
  }

  //회원 탈퇴-기업
  @Override
  public int memberDelete(Long companyIdPk) {
    StringBuffer sql = new StringBuffer();
    sql.append(" delete from member_company where company_id_pk = :companyIdPk ");

    Map<String, Long> param = Map.of("companyIdPk", companyIdPk);
    return template.update(sql.toString(), param);
  }

  //회원 아이디 찾기-기업
  @Override
  public Optional<MemberCompany> findById(String idCompany) {
    StringBuffer sql = new StringBuffer();
    sql.append("");

    Map<String, String> param = Map.of("idCompany", idCompany);
    MemberCompany memberCompany = template.queryForObject(
      sql.toString(),
      param,
      BeanPropertyRowMapper.newInstance(MemberCompany.class)
    );
    return Optional.of(memberCompany);
  }

  //회원 비번 찾기-기업
  @Override
  public Optional<MemberCompany> findByPw(String pwCompany) {
    StringBuffer sql = new StringBuffer();
    sql.append("");

    Map<String, String> param = Map.of("pwCompany", pwCompany);
    MemberCompany memberCompany = template.queryForObject(
      sql.toString(),
      param,
      BeanPropertyRowMapper.newInstance(MemberCompany.class)
    );
    return Optional.of(memberCompany);
  }

  //회원존재 유무 -기업
  @Override
  public boolean isExist(String idCompany) {
    String sql = " select count(id_company) from member_company where id_company = :idCompany ";

    Map<String, String> param = Map.of("idCompany", idCompany);
    Integer cnt = template.queryForObject(sql, param, Integer.class);

    return cnt == 1 ? true : false;
  }

  //로그인 -기업
  @Override
  public Optional<MemberCompany> login(String idCompany, String pwCompany) {
    StringBuffer sql = new StringBuffer();
    sql.append(" select company_id_pk, id_company, name_company ");
    sql.append(" from member_company ");
    sql.append(" where id_company = :idCompany and pw_company = :pwCompany ");

    Map<String, String> param = Map.of("idCompany", idCompany, "pwCompany", pwCompany);
    List<MemberCompany> list = template.query(
      sql.toString(),
      param,
      BeanPropertyRowMapper.newInstance(MemberCompany.class) //자바객체 <=> 테이블 레코드 자동 매핑
    );
    return list.size() == 1 ? Optional.of(list.get(0)) : Optional.empty();
  }
}
