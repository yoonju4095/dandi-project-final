package com.back.endTest.domain.schedule.svc;

import com.back.endTest.domain.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleSVC {
  //등록
  Long save(Schedule schedule);

  //목록
  List<Schedule> findAll(String myId);

  //목록 페이징
  List<Schedule> findAllPaging(String myId, int startRec, int endRec);

  //목록-구직자
  List<Schedule> findAllPerson(String idSchedule);

  //조회
  Optional<Schedule> inquiry(Long scheduleIdPk);

  //수정
  int update(Long scheduleIdPk, Schedule schedule);

  //삭제
  int delete(Long scheduleIdPk);

  //전체조회
  int totalCount(String myId);
}
