package com.back.endTest.web;

import com.back.endTest.domain.entity.MemberCompany;
import com.back.endTest.domain.member.svc.MemberCompanySVC;
import com.back.endTest.web.form.memberCompany.MemberCompanyUpdate;
import com.back.endTest.web.form.memberCompany.MyInfoCompany;
import com.back.endTest.web.form.memberCompany.SingUpCompany;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/memberCompanies")
public class MemberCompanyController {

  private final MemberCompanySVC memberCompanySVC;

  //회원가입 양식-개인
  @GetMapping("/singUp")
  public String singUpForm(Model model) {
    model.addAttribute("singUpCompany", new SingUpCompany());
    return "member/singUpCompanyPage";
  }

  //회원가입 처리-개인
  @PostMapping("/singUp")
  public String singUp(
    @Valid @ModelAttribute SingUpCompany singUpCompany,
    BindingResult bindingResult
  ) {
    log.info("singUpCompany={}", singUpCompany);

    //어노테이션 기반 데이터 검증
    if (bindingResult.hasErrors()) {
      log.info("bindingResult={}", bindingResult);
      return "member/singUpCompanyPage";
    }

    //비밀번호 확인
    if (!singUpCompany.getPwCompany().equals(singUpCompany.getPwChkCompany())) {
      bindingResult.reject("pwCompany", "비밀번호가 동일하지 않습니다");
      log.info("bindingResult={}", bindingResult);
      return "member/singUpCompanyPage";
    }

    //등록
    MemberCompany memberCompany = new MemberCompany();
    memberCompany.setIdCompany(singUpCompany.getIdCompany());
    memberCompany.setPwCompany(singUpCompany.getPwCompany());
    memberCompany.setPwChkCompany(singUpCompany.getPwChkCompany());
    memberCompany.setNameCompany(singUpCompany.getNameCompany());
    memberCompany.setBusinessCompany(singUpCompany.getBusinessCompany());
    memberCompany.setAddressCompany(singUpCompany.getAddressCompany());
    memberCompany.setDetailAddressCompany(singUpCompany.getDetailAddressCompany());
    memberCompany.setEmailCompany(singUpCompany.getEmailCompany());
    memberCompany.setPhoneCompany(singUpCompany.getPhoneCompany());

    memberCompanySVC.singUp(memberCompany);
    return "mainPageBefore";
  }

  //회원 정보 보기-기업
  @GetMapping("/{id}/companyInfo")
  public String myInfo(
    @PathVariable("id") Long companyIdPk, Model model
  ) {
    Optional<MemberCompany> myInfoCompany = memberCompanySVC.memberInfo(companyIdPk);
    MemberCompany memberCompany = myInfoCompany.orElseThrow();

    MyInfoCompany infoCompany = new MyInfoCompany();
    infoCompany.setNameCompany(memberCompany.getNameCompany());
    infoCompany.setBusinessCompany(memberCompany.getBusinessCompany());
    infoCompany.setAddressCompany(memberCompany.getAddressCompany());
    infoCompany.setDetailAddressCompany(memberCompany.getDetailAddressCompany());
    infoCompany.setEmailCompany(memberCompany.getEmailCompany());
    infoCompany.setPhoneCompany(memberCompany.getPhoneCompany());

    model.addAttribute("infoCompany", infoCompany);
    return "member/myInfoCompany";
  }

  //회원 정보 수정 양식-기업
  @GetMapping("/{id}/modify")
  public String updateForm(
    @PathVariable("id") Long id,
    Model model
  ) {
    Optional<MemberCompany> updateMember = memberCompanySVC.memberInfo(id);
    MemberCompany memberCompany = updateMember.orElseThrow();

    MemberCompanyUpdate update = new MemberCompanyUpdate();
    update.setCompanyIdPk(memberCompany.getCompanyIdPk());
    update.setNameCompany(memberCompany.getNameCompany());
    update.setBusinessCompany(memberCompany.getBusinessCompany());
    update.setAddressCompany(memberCompany.getAddressCompany());
    update.setDetailAddressCompany(memberCompany.getDetailAddressCompany());
    update.setEmailCompany(memberCompany.getEmailCompany());
    update.setPhoneCompany(memberCompany.getPhoneCompany());

    model.addAttribute("update", update);
    return "member/companyInfoModifyPage";
  }

  //수정 처리
  @PostMapping("/{id}/modify")
  public String update(
    @PathVariable("id") Long companyIdPk,
    @Valid @ModelAttribute MemberCompanyUpdate memberCompanyUpdate,
    BindingResult bindingResult,
    RedirectAttributes redirectAttributes
  ) {
    //데이터 검증
    if (bindingResult.hasErrors()) {
      log.info("bindingResult={}", bindingResult);
      return "member/companyInfoModifyPage";
    }

    //수정
    MemberCompany memberCompany = new MemberCompany();
    memberCompany.setCompanyIdPk(companyIdPk);
    memberCompany.setNameCompany(memberCompanyUpdate.getNameCompany());
    memberCompany.setBusinessCompany(memberCompanyUpdate.getBusinessCompany());
    memberCompany.setAddressCompany(memberCompanyUpdate.getAddressCompany());
    memberCompany.setDetailAddressCompany(memberCompanyUpdate.getDetailAddressCompany());
    memberCompany.setEmailCompany(memberCompanyUpdate.getEmailCompany());
    memberCompany.setPhoneCompany(memberCompanyUpdate.getPhoneCompany());

    memberCompanySVC.memberUpdate(companyIdPk, memberCompany);
    redirectAttributes.addAttribute("id", companyIdPk);

    return "redirect:/memberCompanies/{id}/companyInfo";
  }

  //회원탈퇴-개인
  @GetMapping("/{id}/memberDelete")
  public String memberDelete(@PathVariable("id") Long companyIdPk) {
    memberCompanySVC.memberDelete(companyIdPk);

    return "redirect:/memberCompanies";
  }
}
