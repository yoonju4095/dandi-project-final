package com.back.endTest.domain.jobBoard.svc;

import com.back.endTest.domain.entity.JobBoard;
import com.back.endTest.domain.entity.UploadFile;
import com.back.endTest.web.common.AttachFileType;

import java.util.List;
import java.util.Optional;

public interface JobBoardSVC {

  //등록
  Long save(JobBoard jobBoard);
  //등록 업로드도
  Long save(JobBoard jobBoard, List<UploadFile> uploadFiles);


  //조회
  Optional<JobBoard> inquiry(Long jobBoardIdPk);

  //수정
  int update(Long jobBoardIdPk, JobBoard jobBoard);
  int update(Long jobBoardIdPk, JobBoard jobBoard, List<UploadFile> uploadFiles);


  //삭제
  int delete(Long jobBoardIdPk);
  int delete(Long jobBoardIdPk, AttachFileType attachFileType);

  //목록
  List<JobBoard> findAll();
  //목록 페이징
  List<JobBoard> findAllPaging(int startRec, int endRec);

  //구인글 존재 유무
  boolean isExist(Long jobBoardIdPk);

  //전체 건수
  int totalCount();

}
