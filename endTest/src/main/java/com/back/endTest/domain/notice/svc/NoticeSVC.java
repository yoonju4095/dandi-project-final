package com.back.endTest.domain.notice.svc;


import com.back.endTest.domain.entity.Notice;
import com.back.endTest.domain.entity.UploadFile;

import java.util.List;
import java.util.Optional;

public interface NoticeSVC {

  // 등록
  Long save(Notice notice);

  Long save(Notice notice, List<UploadFile> uploadFiles);

  // 조회
  Optional<Notice> findById(Long id);

  // 수정
  int update(Long id, Notice notice);

  // 삭제
  int delete(Long id);

  // 목록
  List<Notice> findAll();

  List<Notice> findAllPaging(int startRec, int endRec);

  //조회수증가
  int increaseHit(Long id);

  int totalCount();

}
