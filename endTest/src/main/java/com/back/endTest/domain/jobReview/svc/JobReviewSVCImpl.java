package com.back.endTest.domain.jobReview.svc;

import com.back.endTest.domain.common.file.svc.UploadFileSVC;
import com.back.endTest.domain.entity.JobReview;
import com.back.endTest.domain.entity.UploadFile;
import com.back.endTest.domain.jobReview.dao.JobReviewDAO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class JobReviewSVCImpl implements JobReviewSVC {

  private final JobReviewDAO jobReviewDAO;
  private final UploadFileSVC uploadFileSVC;

  @Override
  public Long save(JobReview jobReview) {
    return jobReviewDAO.save(jobReview);
  }

  @Override
  public Long save(JobReview jobReview, List<UploadFile> uploadFiles) {
    Long reviewIdPk = save(jobReview);
    if (uploadFiles.size() > 0) {
      uploadFiles.stream().forEach(file -> file.setRid(reviewIdPk));
      uploadFileSVC.addFiles(uploadFiles);
    }
    return reviewIdPk;
  }

  @Override
  public Optional<JobReview> inquiry(Long reviewIdPk) {
    return jobReviewDAO.inquiry(reviewIdPk);
  }

  @Override
  public int update(Long reviewIdPk, JobReview jobReview) {
    return jobReviewDAO.update(reviewIdPk, jobReview);
  }

  @Override
  public int update(Long reviewIdPk, JobReview jobReview, List<UploadFile> uploadFiles) {
    jobReviewDAO.update(reviewIdPk, jobReview);
    if (uploadFiles.size() > 0) {
      uploadFiles.stream().forEach(file->file.setRid(reviewIdPk));
      uploadFileSVC.addFiles(uploadFiles);
    }
    return 0;
  }

  @Override
  public int delete(Long reviewIdPk) {
    return jobReviewDAO.delete(reviewIdPk);
  }

  @Override
  public List<JobReview> findAll(Long focusJobBoardId) {
    return jobReviewDAO.findAll(focusJobBoardId);
  }

  @Override
  public boolean isExist(Long reviewIdPk) {
    return jobReviewDAO.isExist(reviewIdPk);
  }
}
