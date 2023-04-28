package com.back.endTest.domain.jobReview.svc;

import com.back.endTest.domain.entity.JobReview;
import com.back.endTest.domain.entity.UploadFile;

import java.util.List;
import java.util.Optional;

public interface JobReviewSVC {
  //등록
  Long save(JobReview jobReview);
  Long save(JobReview jobReview, List<UploadFile> uploadFiles);

  //조회
  Optional<JobReview> inquiry(Long reviewIdPk);

  //수정
  int update(Long reviewIdPk, JobReview jobReview);
  int update(Long reviewIdPk, JobReview jobReview, List<UploadFile> uploadFiles);

  //삭제
  int delete(Long reviewIdPk);

  //목록
  List<JobReview> findAll(Long focusJobBoardId);

  //구인글 존재 유무
  boolean isExist(Long reviewIdPk);
}
