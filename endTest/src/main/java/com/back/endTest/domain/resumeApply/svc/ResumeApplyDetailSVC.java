package com.back.endTest.domain.resumeApply.svc;


import com.back.endTest.domain.resumeApply.ResumeApplyDetail;

import java.util.List;

public interface ResumeApplyDetailSVC {
  //등록
  void apply(List<ResumeApplyDetail> resumeDetails);
  //상세
  List<ResumeApplyDetail> findApply(Long resumeId);



  //삭제
  int applyDelete(Long resumeId);
}
