package com.back.endTest.domain.resumeApply.svc;

import com.back.endTest.domain.resumeApply.ResumeApply;
import com.back.endTest.domain.resumeApply.dao.ResumeApplyDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ResumeApplySVCImpl implements ResumeApplySVC{

  private final ResumeApplyDAO resumeApplyDAO;

  @Override
  public ResumeApply apply(ResumeApply resumeApply) {
    return resumeApplyDAO.apply(resumeApply);
  }

  @Override
  public ResumeApply findApply(Long resumeApplyId) {
    return resumeApplyDAO.findApply(resumeApplyId);
  }

  @Override
  public List<ResumeApply> findAll(String idPerson) {
    return resumeApplyDAO.findAll(idPerson);
  }


  @Override
  public List<ResumeApply> findAllCompany(String idJob) {
    return resumeApplyDAO.findAllCompany(idJob);
  }

  @Override
  public Optional<ResumeApply> findByJobBoardIdPk(Long jobBoardIdPk) {
    return resumeApplyDAO.findByJobBoardIdPk(jobBoardIdPk);
  }

  @Override
  public ResumeApply findByIdPerson(String idPerson) {
    return resumeApplyDAO.findByIdPerson(idPerson);
  }

  @Override
  public int applyDelete(Long jobBoardIdPk) {
    return resumeApplyDAO.delete(jobBoardIdPk);
  }
}
