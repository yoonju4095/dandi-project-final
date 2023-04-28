package com.back.endTest.domain.resume.svc;


import com.back.endTest.domain.resume.Resume;

import java.util.List;

public interface ResumeSVC {

  //등록
  Resume write(Resume resume);
  //상세
  Resume findByResumeId(Long resumeId);
  //전체조회
  List<Resume> findAll(String idPerson);
  //수정
  Resume modify(Resume resume);
  //삭제
  int remove(Long resumeId);
}
