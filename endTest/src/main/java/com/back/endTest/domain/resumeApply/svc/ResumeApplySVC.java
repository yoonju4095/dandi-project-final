package com.back.endTest.domain.resumeApply.svc;

import com.back.endTest.domain.resumeApply.ResumeApply;

import java.util.List;
import java.util.Optional;

public interface ResumeApplySVC {

  //이력서 지원하기
  ResumeApply apply(ResumeApply resumeApply);

  //이력서 지원 조회하기
  ResumeApply findApply(Long resumeApplyId);

  //구직자계정으로 접근
  List<ResumeApply> findAll(String idPerson);
  // 구인자계정으로 접근
  List<ResumeApply> findAllCompany(String idJob);
  //멤버 필드 접근
  ResumeApply findByIdPerson(String idPerson);

  //지원 내역 게시판 조회
  Optional<ResumeApply> findByJobBoardIdPk(Long jobBoardIdPk);

  //이력서 지원 삭제하기
  int applyDelete(Long jobBoardIdPk);
}
