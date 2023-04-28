package com.back.endTest.domain.member.svc;

import com.back.endTest.domain.entity.MemberPerson;
import com.back.endTest.domain.member.dao.MemberPersonDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberPersonSVCImpl implements MemberPersonSVC{

  private final MemberPersonDAO memberPersonDAO;

  @Override
  public MemberPerson singUp(MemberPerson memberPerson) {
    return memberPersonDAO.singUp(memberPerson);
  }

  @Override
  public Optional<MemberPerson> memberInfo(Long personIdPk) {
    return memberPersonDAO.memberInfo(personIdPk);
  }


  @Override
  public int memberUpdate(Long personIdPk, MemberPerson memberPerson) {
    return memberPersonDAO.memberUpdate(personIdPk, memberPerson);
  }

  @Override
  public int memberDelete(Long personIdPk) {
    return memberPersonDAO.memberDelete(personIdPk);
  }

  @Override
  public MemberPerson findById(String idPerson) {
    return memberPersonDAO.findById(idPerson);
  }

  @Override
  public Optional<MemberPerson> findByPw(String pwPerson) {
    return memberPersonDAO.findByPw(pwPerson);
  }

  //회원 존재 유무
  @Override
  public boolean isExist(String idPerson) {
    return memberPersonDAO.isExist(idPerson);
  }

  //로그인-개인
  @Override
  public Optional<MemberPerson> login(String idPerson, String pwPerson) {
    return memberPersonDAO.login(idPerson, pwPerson);
  }
}
