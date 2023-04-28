package com.back.endTest.domain.member.dao;


import com.back.endTest.domain.entity.MemberCompany;

import java.util.Optional;

public interface MemberCompanyDAO {

  //회원가입(등록) 기업
  MemberCompany singUp(MemberCompany memberCompany);

  //회원정보 조회 기업
  Optional<MemberCompany> memberInfo(Long companyIdPk);

  //회원수정(수정)
  int memberUpdate(Long companyIdPk, MemberCompany memberCompany);

  //회원탈퇴(삭제)
  int memberDelete(Long companyIdPk);

  //회원아이디 찾기(조회)
  Optional<MemberCompany> findById(String idCompany);

  //회원비번 찾기(조회)
  Optional<MemberCompany> findByPw(String pwCompany);

  //회원유무
  boolean isExist(String idCompany);

  //로그인
  Optional<MemberCompany> login(String idCompany, String pwCompany);
}
