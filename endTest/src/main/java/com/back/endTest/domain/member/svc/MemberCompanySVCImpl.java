package com.back.endTest.domain.member.svc;

import com.back.endTest.domain.entity.MemberCompany;
import com.back.endTest.domain.member.dao.MemberCompanyDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberCompanySVCImpl implements MemberCompanySVC{

  private final MemberCompanyDAO memberCompanyDAO;


  @Override
  public MemberCompany singUp(MemberCompany memberCompany) {
    return memberCompanyDAO.singUp(memberCompany);
  }

  @Override
  public Optional<MemberCompany> memberInfo(Long companyIdPk) {
    return memberCompanyDAO.memberInfo(companyIdPk);
  }

  @Override
  public int memberUpdate(Long companyIdPk, MemberCompany memberCompany) {
    return memberCompanyDAO.memberUpdate(companyIdPk,memberCompany);
  }

  @Override
  public int memberDelete(Long companyIdPk) {
    return memberCompanyDAO.memberDelete(companyIdPk);
  }

  @Override
  public Optional<MemberCompany> findById(String idCompany) {
    return memberCompanyDAO.findById(idCompany);
  }

  @Override
  public Optional<MemberCompany> findByPw(String pwCompany) {
    return memberCompanyDAO.findByPw(pwCompany);
  }

  @Override
  public boolean isExist(String idCompany) {
    return memberCompanyDAO.isExist(idCompany);
  }

  @Override
  public Optional<MemberCompany> login(String idCompany, String pwCompany) {
    return memberCompanyDAO.login(idCompany, pwCompany);
  }
}
