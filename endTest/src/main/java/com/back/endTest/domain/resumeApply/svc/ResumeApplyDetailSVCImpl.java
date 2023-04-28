package com.back.endTest.domain.resumeApply.svc;


import com.back.endTest.domain.resumeApply.ResumeApplyDetail;
import com.back.endTest.domain.resumeApply.dao.ResumeApplyDetailDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ResumeApplyDetailSVCImpl implements ResumeApplyDetailSVC{

  private final ResumeApplyDetailDAO resumeApplyDetailDAO;


  @Override
  public void apply(List<ResumeApplyDetail> resumeApplyDetails) {
    resumeApplyDetailDAO.apply(resumeApplyDetails);
  }

  @Override
  public List<ResumeApplyDetail> findApply(Long resumeId) {
    List<ResumeApplyDetail> resumeApplyDetails = resumeApplyDetailDAO.findApply(resumeId);
    return resumeApplyDetails;
  }



  @Override
  public int applyDelete(Long resumeId) {
    return resumeApplyDetailDAO.delete(resumeId);
  }
}
