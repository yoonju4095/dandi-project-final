package com.back.endTest.domain.resume.dao;


import com.back.endTest.domain.resume.Resume;

import java.util.List;

public interface ResumeDAO {

  //이력서 등록
  Resume create(Resume resume);

  //이력서 조회
  Resume selectOne(Long resumeId);


  //이력서 수정
  Resume update(Resume resume);

  //이력서 삭제
  int delete(Long resumeId);

  //이력서 전체조회
  List<Resume> selectAll(String idPerson);

}
