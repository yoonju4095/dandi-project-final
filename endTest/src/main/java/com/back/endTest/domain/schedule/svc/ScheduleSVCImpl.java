package com.back.endTest.domain.schedule.svc;

import com.back.endTest.domain.entity.Schedule;
import com.back.endTest.domain.schedule.dao.ScheduleDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleSVCImpl implements ScheduleSVC{

  private final ScheduleDAO scheduleDAO;

  @Override
  public Long save(Schedule schedule) {
    return scheduleDAO.save(schedule);
  }

  @Override
  public List<Schedule> findAll(String myId) {
    return scheduleDAO.findAll(myId);
  }

  @Override
  public List<Schedule> findAllPaging(String myId, int startRec, int endRec) {
    return scheduleDAO.findAllPaging(myId, startRec,endRec);
  }

  @Override
  public List<Schedule> findAllPerson(String idSchedule) {
    return scheduleDAO.findAllPerson(idSchedule);
  }

  @Override
  public Optional<Schedule> inquiry(Long scheduleIdPk) {
    return scheduleDAO.inquiry(scheduleIdPk);
  }

  @Override
  public int update(Long scheduleIdPk, Schedule schedule) {
    return scheduleDAO.update(scheduleIdPk, schedule);
  }

  @Override
  public int delete(Long scheduleIdPk) {
    return scheduleDAO.delete(scheduleIdPk);
  }

  @Override
  public int totalCount(String myId) {
    return scheduleDAO.totalCount(myId);
  }
}
