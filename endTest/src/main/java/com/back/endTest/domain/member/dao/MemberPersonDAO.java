package com.back.endTest.domain.member.dao;


import com.back.endTest.domain.entity.MemberPerson;

import java.util.Optional;

public interface MemberPersonDAO {

  //회원가입(등록)
  MemberPerson singUp(MemberPerson memberPerson);

  //회원정보 보기(조회)
  Optional<MemberPerson> memberInfo(Long personIdPk);

  //회원수정(수정)
  int memberUpdate(Long personIdPk, MemberPerson memberPerson);

  //회원탈퇴(삭제)
  int memberDelete(Long personIdPk);

  //회원아이디 찾기(조회)
  MemberPerson findById(String idPerson);

  //회원비번 찾기(조회)
  Optional<MemberPerson> findByPw(String pwPerson);

  //회원유무
  boolean isExist(String idPerson);

  //로그인
  Optional<MemberPerson> login(String idPerson, String pwPerson);
}
