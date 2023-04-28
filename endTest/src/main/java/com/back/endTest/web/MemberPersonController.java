package com.back.endTest.web;

import com.back.endTest.domain.entity.MemberPerson;
import com.back.endTest.domain.member.svc.MemberPersonSVC;
import com.back.endTest.web.form.memberPerson.MemberPersonUpdate;
import com.back.endTest.web.form.memberPerson.MyInfoPerson;
import com.back.endTest.web.form.memberPerson.SingUpPerson;
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
@RequestMapping("/memberPersons")
public class MemberPersonController {

  private final MemberPersonSVC memberPersonSVC;

  //회원가입 양식-개인
  @GetMapping("/singUp")
  public String singUpForm(Model model) {
    model.addAttribute("singUpPerson", new SingUpPerson());
    return "member/singUpPersonPage";
  }

  //회원가입 처리-개인
  @PostMapping("/singUp")
  public String singUp(
    @Valid @ModelAttribute SingUpPerson singUpPerson,
    BindingResult bindingResult
  ) {
    log.info("singUpPerson={}", singUpPerson);

    //어노테이션 기반 데이터 검증
    if (bindingResult.hasErrors()) {
      log.info("bindingResult={}", bindingResult);
      return "member/singUpPersonPage";
    }

    //비밀번호 확인
    if (!singUpPerson.getPwPerson().equals(singUpPerson.getPwChkPerson())) {
      bindingResult.reject("pwPerson", "비밀번호가 동일하지 않습니다");
      log.info("bindingResult={}", bindingResult);
      return "member/singUpPersonPage";
    }

    //등록
    MemberPerson memberPerson = new MemberPerson();
    memberPerson.setIdPerson(singUpPerson.getIdPerson());
    memberPerson.setPwPerson(singUpPerson.getPwPerson());
    memberPerson.setPwChkPerson(singUpPerson.getPwChkPerson());
    memberPerson.setNamePerson(singUpPerson.getNamePerson());
    memberPerson.setBirthPerson(singUpPerson.getBirthPerson());
    memberPerson.setGenderPerson(singUpPerson.getGenderPerson());
    memberPerson.setAddressPerson(singUpPerson.getAddressPerson());
    memberPerson.setDetailAddressPerson(singUpPerson.getDetailAddressPerson());
    memberPerson.setEmailPerson(singUpPerson.getEmailPerson());
    memberPerson.setPhonePerson(singUpPerson.getPhonePerson());

    memberPersonSVC.singUp(memberPerson);
    return "mainPageBefore";
  }

  //회원 정보 보기-개인
  @GetMapping("/{id}/personInfo")
  public String myInfo(
    @PathVariable("id") Long id, Model model
  ) {
    Optional<MemberPerson> myInfoPerson = memberPersonSVC.memberInfo(id);
    MemberPerson memberPerson = myInfoPerson.orElseThrow();

    MyInfoPerson infoPerson = new MyInfoPerson();
    infoPerson.setPersonIdPk(memberPerson.getPersonIdPk());
    infoPerson.setNamePerson(memberPerson.getNamePerson());
    infoPerson.setBirthPerson(memberPerson.getBirthPerson());
    infoPerson.setGenderPerson(memberPerson.getGenderPerson());
    infoPerson.setAddressPerson(memberPerson.getAddressPerson());
    infoPerson.setDetailAddressPerson(memberPerson.getDetailAddressPerson());
    infoPerson.setEmailPerson(memberPerson.getEmailPerson());
    infoPerson.setPhonePerson(memberPerson.getPhonePerson());

    model.addAttribute("infoPerson", infoPerson);
    return "member/myInfoPerson";
  }

  //수정 양식 가져오는 것과 post 가 뒤섞여있음 수정필요
  //회원 정보 수정 양식-개인
  @GetMapping("/{id}/modify")
  public String updateForm(
    @PathVariable("id") Long id,
    Model model
  ) {
    Optional<MemberPerson> updateMember = memberPersonSVC.memberInfo(id);
    MemberPerson memberPerson = updateMember.orElseThrow();

    MemberPersonUpdate update = new MemberPersonUpdate();
    update.setPersonIdPk(memberPerson.getPersonIdPk());
    update.setNamePerson(memberPerson.getNamePerson());
    update.setBirthPerson(memberPerson.getBirthPerson());
    update.setGenderPerson(memberPerson.getGenderPerson());
    update.setAddressPerson(memberPerson.getAddressPerson());
    update.setDetailAddressPerson(memberPerson.getDetailAddressPerson());
    update.setEmailPerson(memberPerson.getEmailPerson());
    update.setPhonePerson(memberPerson.getPhonePerson());

    model.addAttribute("update", update);
    return "member/personInfoModifyPage";
  }

  //수정 처리
  @PostMapping("/{id}/modify")
  public String update(
    @PathVariable("id") Long personIdPk,
    @Valid @ModelAttribute MemberPersonUpdate memberPersonUpdate,
    BindingResult bindingResult,
    RedirectAttributes redirectAttributes
  ) {
    //데이터 검증
    if (bindingResult.hasErrors()) {
      log.info("bindingResult={}", bindingResult);
      return "member/personInfoModifyPage";
    }

    //수정
    MemberPerson memberPerson = new MemberPerson();
    memberPerson.setPersonIdPk(personIdPk);
    memberPerson.setNamePerson(memberPersonUpdate.getNamePerson());
    memberPerson.setBirthPerson(memberPersonUpdate.getBirthPerson());
    memberPerson.setGenderPerson(memberPersonUpdate.getGenderPerson());
    memberPerson.setAddressPerson(memberPersonUpdate.getAddressPerson());
    memberPerson.setDetailAddressPerson(memberPersonUpdate.getDetailAddressPerson());
    memberPerson.setEmailPerson(memberPersonUpdate.getEmailPerson());
    memberPerson.setPhonePerson(memberPersonUpdate.getPhonePerson());

    memberPersonSVC.memberUpdate(personIdPk, memberPerson);
    redirectAttributes.addAttribute("id", personIdPk);

    return "redirect:/memberPersons/{id}/personInfo";
  }

  //회원탈퇴-개인
  @GetMapping("/{id}/memberDelete")
  public String memberDelete(@PathVariable("id") Long personIdPk) {
    memberPersonSVC.memberDelete(personIdPk);

    return "mainPageBefore";
  }
}
