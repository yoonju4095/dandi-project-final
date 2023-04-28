package com.back.endTest.domain.resumeApply.dao;


import com.back.endTest.domain.resumeApply.ResumeApplyDetail;

import java.util.List;

public interface ResumeApplyDetailDAO {
  //이력서 상세 지원하기
  void apply(List<ResumeApplyDetail> resumeApplyDetails);

  //이력서상세 지원하기 조회
  List<ResumeApplyDetail> findApply(Long resumeId);

  //이력서상세 지원하기 삭제
  int delete(Long resumeId);

}
