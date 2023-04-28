package com.back.endTest.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rest")
public class CsrController {

  @GetMapping("/schedules/managing/{id}")
  public String scheduleCompanyFrom(
  ) {
    return "schedule/scheduleCompany";
  }
  @GetMapping("/schedules/viewSchedule/{id}")
  public String schedulePersonFrom(
  ) {
    return "schedule/schedulePerson";
  }
}
