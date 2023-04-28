package com.back.endTest.web;

import com.back.endTest.domain.common.paging.FindCriteria;
import com.back.endTest.domain.entity.Schedule;
import com.back.endTest.domain.schedule.svc.ScheduleSVC;
import com.back.endTest.web.rest.ModifyRest;
import com.back.endTest.web.rest.SetRest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class RestScheduleController {

  private final ScheduleSVC scheduleSVC;

  @Autowired
  @Qualifier("fc5")
  private FindCriteria fc;

  //스케줄 등록
  @PostMapping
  public RestResponse<Object> set(
    @RequestBody SetRest setRest,
    BindingResult bindingResult
  ) {
    RestResponse<Object> res = null;
    log.info("setRest={}", setRest);
    //검증
//    if (bindingResult.) {
//
//    }
    //등록
    Schedule schedule = new Schedule();
    schedule.setMyId(setRest.getMyId());
    schedule.setNameSchedule(setRest.getNameSchedule());
    schedule.setIdSchedule(setRest.getIdSchedule());
    schedule.setWorkSchedule(setRest.getWorkSchedule());
    schedule.setDaySchedule(setRest.getDaySchedule());
    schedule.setComeInSchedule(setRest.getComeInSchedule());
    schedule.setComeOutSchedule(setRest.getComeOutSchedule());
    schedule.setPeriodStart(setRest.getPeriodStart());
    schedule.setPeriodEnd(setRest.getPeriodEnd());

    Long scheduleIdPk = scheduleSVC.save(schedule);
    schedule.setScheduleIdPk(scheduleIdPk);


    if (scheduleIdPk > 0) {
      res = RestResponse.createRestResponse("00", "성공", schedule);
    } else {
      res = RestResponse.createRestResponse("99", "실패", "서버오류");
    }
    return res;
  }

  //스케줄 목록-구인자
  @GetMapping("/managing/{id}")
  public RestResponse<Object> findAll(
    @PathVariable("id") String myID
  ) {
    RestResponse<Object> res = null;
    List<Schedule> list = scheduleSVC.findAll(myID);
    log.info("myId={}", myID);
    if (list.size() > 0) {
      res = RestResponse.createRestResponse("00", "성공", list);
    } else {
      res = RestResponse.createRestResponse("01", "스케줄이 존재하지 않습니다.", null);
    }
    return res;
  }

  //스케줄 페이징
  @GetMapping("/managing/{id}/{reqPage}")
  public RestResponse<Object> findAllPaging(
    @PathVariable("id") String myID,
    @PathVariable("reqPage") Optional<Integer> reqPage,
    Model model
  ) {
    RestResponse<Object> res = null;
    log.info("list={}", reqPage);
    //요청페이지(없으면 1페이지로)
    fc.getRc().setReqPage(reqPage.orElse(1));

    //총 레코드 수
    fc.setTotalRec(scheduleSVC.totalCount(myID));
    List<Schedule> listPaging = scheduleSVC.findAllPaging(myID, fc.getRc().getStartRec(), fc.getRc().getEndRec());

    if (listPaging.size() > 0) {
      res = RestResponse.createRestResponse("00", "성공", listPaging);
    } else {
      res = RestResponse.createRestResponse("01", "스케줄이 존재하지 않습니다.", null);
    }
    return res;
  }

  //스케줄 목록-구직자
  @GetMapping("/viewSchedule/{id}")
  public RestResponse<Object> findAllPerson(
    @PathVariable("id") String idSchedule
  ) {
    RestResponse<Object> res = null;
    List<Schedule> list = scheduleSVC.findAllPerson(idSchedule);
    log.info("idSchedule={}", idSchedule);
    if (list.size() > 0) {
      res = RestResponse.createRestResponse("00", "성공", list);
    } else {
      res = RestResponse.createRestResponse("01", "스케줄이 존재하지 않습니다.", null);
    }
    return res;
  }

  //조회 처리
  @GetMapping("/{id}")
  public RestResponse<Object> inquiry(
    @PathVariable("id") Long scheduleIdPk
  ) {
    RestResponse<Object> res = null;
    Optional<Schedule> inquirySchedule = scheduleSVC.inquiry(scheduleIdPk);
    res = RestResponse.createRestResponse("00", "성공", inquirySchedule);
    return res;
  }

  //스케줄 수정
  @PatchMapping("/{id}")
  public RestResponse<Object> modify(
    @PathVariable("id") Long scheduleIdPk,
    @RequestBody ModifyRest modifyRest
  ) {
    RestResponse<Object> res = null;
    //검증

    //수정
    Schedule schedule = new Schedule();
    schedule.setNameSchedule(modifyRest.getNameSchedule());
    schedule.setIdSchedule(modifyRest.getIdSchedule());
    schedule.setWorkSchedule(modifyRest.getWorkSchedule());
    schedule.setDaySchedule(modifyRest.getDaySchedule());
    schedule.setComeInSchedule(modifyRest.getComeInSchedule());
    schedule.setComeOutSchedule(modifyRest.getComeOutSchedule());
    schedule.setPeriodStart(modifyRest.getPeriodStart());
    schedule.setPeriodEnd(modifyRest.getPeriodEnd());

    int updateRowCnt = scheduleSVC.update(scheduleIdPk, schedule);
    modifyRest.setScheduleIdPk(scheduleIdPk);

    if (updateRowCnt == 1) {
      res = RestResponse.createRestResponse("00", "성공", modifyRest);
    } else {
      res = RestResponse.createRestResponse("99", "실패", "서버오류");
    }
    return res;
  }

  //스케줄 삭제
  @DeleteMapping("/{id}")
  public RestResponse<Object> delete(
    @PathVariable("id") Long scheduleIdPk
  ) {
    RestResponse<Object> res = null;

    //삭제
    int delRowCnt = scheduleSVC.delete(scheduleIdPk);
    if (delRowCnt == 1) {
      res = RestResponse.createRestResponse("00", "성공", null);
    } else {
      res = RestResponse.createRestResponse("99", "실패", "서버오류");
    }
    return res;
  }
}
