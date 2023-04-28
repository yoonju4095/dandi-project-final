package com.back.endTest.domain.resume.svc;


import com.back.endTest.domain.resume.Resume;
import com.back.endTest.domain.resume.dao.ResumeDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ResumeSVCImpl implements ResumeSVC{

  private final ResumeDAO resumeDAO;

  @Override
  public Resume write(Resume resume) {

    return resumeDAO.create(resume);
  }

  @Override
  public Resume findByResumeId(Long resumeId) {
    Resume resume = resumeDAO.selectOne(resumeId);
    return resume;
  }

  @Override
  public Resume modify(Resume resume) {
    return resumeDAO.update(resume);
  }

  @Override
  public int remove(Long resumeId) {
    return resumeDAO.delete(resumeId);
  }

  @Override
  public List<Resume> findAll(String idPerson) {
    return resumeDAO.selectAll(idPerson);
  }
}
