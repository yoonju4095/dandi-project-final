package com.back.endTest.web;

import com.back.endTest.domain.entity.MemberCompany;
import com.back.endTest.domain.entity.MemberPerson;
import com.back.endTest.domain.member.svc.MemberCompanySVC;
import com.back.endTest.domain.member.svc.MemberPersonSVC;
import com.back.endTest.web.form.login.LoginCompanyForm;
import com.back.endTest.web.form.login.LoginPersonForm;
import com.back.endTest.web.session.LoginCompany;
import com.back.endTest.web.session.LoginPerson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
  private final MemberPersonSVC memberPersonSVC;
  private final MemberCompanySVC memberCompanySVC;
  private LoginPerson loginPerson;
  private LoginCompany loginCompany;

  //로그인 화면
  @GetMapping("/login")
  public String loginForm(
    Model model
  ) {
    model.addAttribute("loginPersonForm", new LoginPersonForm());
    model.addAttribute("loginCompanyForm", new LoginCompanyForm());
    return "loginPage";
  }

  //로그인 처리  /login?loginType="company" | /login?loginType="person"
  @PostMapping("/login")
  public String login(
    @Valid @ModelAttribute LoginPersonForm loginPersonForm,
    BindingResult bindingResult1,
    @Valid @ModelAttribute LoginCompanyForm loginCompanyForm,
    BindingResult bindingResult2,
    HttpServletRequest httpServletRequest,
    @RequestParam(value = "redirectURL", required = false, defaultValue = "/") String redirectURL,
    @RequestParam(value = "loginType") String loginType
  ) {
    log.info("loginCompanyForm={}", loginCompanyForm);
    log.info("loginForm={}", loginPersonForm);
    log.info("loginType={}", loginType);
    if (loginType.equals("person")) {

      // 개인 회원
      //1)아이디 존재 유무 확인
      if (!memberPersonSVC.isExist(loginPersonForm.getIdPerson())) {
        bindingResult1.rejectValue("idPerson", "loginPage", "아이디를 확인해주세요");
        return "loginPage";
      }
      //2)로그인
      Optional<MemberPerson> memberPerson = memberPersonSVC.login(loginPersonForm.getIdPerson(), loginPersonForm.getPwPerson());

      if (memberPerson.isEmpty()) {
        bindingResult1.rejectValue("pwPerson", "loginPage", "비밀번호를 확인해주세요");
        return "loginPage";
      }


      //개인 회원 세션 생성
      HttpSession session = httpServletRequest.getSession(true);
      LoginPerson loginPerson = new LoginPerson(
        memberPerson.get().getPersonIdPk(),
        memberPerson.get().getIdPerson(),
        memberPerson.get().getNamePerson()
      );
      session.setAttribute(SessionConst.LOGIN_PERSON, loginPerson);
      log.info("loginPerson={}", loginPerson);

    }
    if (loginType.equals("company")) {

      //로그인 처리-기업 회원
      //1)아이디 존재 유무 확인
      if (!memberCompanySVC.isExist(loginCompanyForm.getIdCompany())) {
        bindingResult2.rejectValue("idCompany", "loginPage", "아이디를 확인해주세요");
        return "loginPage";
      }
      //2)로그인
      Optional<MemberCompany> memberCompany = memberCompanySVC.login(loginCompanyForm.getIdCompany(), loginCompanyForm.getPwCompany());
      if (memberCompany.isEmpty()) {
        bindingResult2.rejectValue("pwCompany", "loginPage", "비밀번호를 확인해주세요");
        return "loginPage";
      }


      //기업 회원 세션 생성
      HttpSession session = httpServletRequest.getSession(true);
      LoginCompany loginCompany = new LoginCompany(
        memberCompany.get().getCompanyIdPk(),
        memberCompany.get().getIdCompany(),
        memberCompany.get().getNameCompany()
      );
      session.setAttribute(SessionConst.LOGIN_COMPANY, loginCompany);
      log.info("loginCompany={}", loginCompany);
    }
    return "redirect:" + redirectURL;
  }

  //로그아웃
  @GetMapping("logout")
  public String logout(HttpServletRequest httpServletRequest) {
    //세션이 있으면 해당 정보를 가져오고 없으면 세션생성 하지 않음
    HttpSession session = httpServletRequest.getSession(false);
    if (session != null) {
      session.invalidate(); //세션 제거
    }
    return "redirect:/";
  }
}
