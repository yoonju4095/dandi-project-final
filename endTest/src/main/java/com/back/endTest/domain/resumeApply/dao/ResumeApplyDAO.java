package com.back.endTest.domain.resumeApply.dao;

import com.back.endTest.domain.resumeApply.ResumeApply;

import java.util.List;
import java.util.Optional;

public interface ResumeApplyDAO {


  //이력서 지원하기
  ResumeApply apply(ResumeApply resumeApply);

  //이력서 지원 조회하기
  ResumeApply findApply(Long resumeApplyId);

  //이력서 지원 삭제하기
  int delete(Long jobBoardIdPk);

  ResumeApply findByIdPerson(String idPerson);

// 구직아이디로 모든 이력서 조회하기
  List<ResumeApply> findAll(String idPerson);
// 구인아이디로 데이터베이스 접근
  List<ResumeApply> findAllCompany(String idJob);
  //지원 내역 게시판 조회
  Optional<ResumeApply> findByJobBoardIdPk(Long jobBoardIdPk);
}
