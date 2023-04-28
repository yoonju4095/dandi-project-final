package com.back.endTest.domain.jobReview.dao;

import com.back.endTest.domain.entity.JobReview;

import java.util.List;
import java.util.Optional;

public interface JobReviewDAO {
  //등록
  Long save(JobReview jobReview);

  //조회
  Optional<JobReview> inquiry(Long reviewIdPk);

  //수정
  int update(Long reviewIdPk, JobReview jobReview);

  //삭제
  int delete(Long reviewIdPk);

  //목록
  List<JobReview> findAll(Long focusJobBoardId);

  //구인글 존재 유무
  boolean isExist(Long reviewIdPk);
}
