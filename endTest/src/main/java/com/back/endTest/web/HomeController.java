package com.back.endTest.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/")
public class HomeController {

  @GetMapping
  public String home(HttpServletRequest request) {

    String view = null;
    HttpSession session = request.getSession(false);
    view = (session == null) ? "mainPageBefore" : "mainPageAfter";
    return view;
  }

  @GetMapping("singUpSelect")
  public String singUpForm() {
    return "singUpSelectPage";
  }


}
