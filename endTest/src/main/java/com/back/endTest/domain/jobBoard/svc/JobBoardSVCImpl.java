package com.back.endTest.domain.jobBoard.svc;

import com.back.endTest.domain.common.file.svc.UploadFileSVC;
import com.back.endTest.domain.entity.JobBoard;
import com.back.endTest.domain.entity.UploadFile;
import com.back.endTest.domain.jobBoard.dao.JobBoardDAO;
import com.back.endTest.web.common.AttachFileType;
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
public class JobBoardSVCImpl implements JobBoardSVC{

  private final JobBoardDAO jobBoardDAO;
  private final UploadFileSVC uploadFileSVC;

  @Override
  public Long save(JobBoard jobBoard) {
    return jobBoardDAO.save(jobBoard);
  }

  @Override
  public Long save(JobBoard jobBoard, List<UploadFile> uploadFiles) {
    Long jobBoardIdPk = save(jobBoard);
    if (uploadFiles.size() > 0) {
      uploadFiles.stream().forEach(file->file.setRid(jobBoardIdPk));
      uploadFileSVC.addFiles(uploadFiles);
    }

    return jobBoardIdPk;
  }

  @Override
  public Optional<JobBoard> inquiry(Long jobBoardIdPk) {
    return jobBoardDAO.inquiry(jobBoardIdPk);
  }

  @Override
  public int update(Long jobBoardIdPk, JobBoard jobBoard) {
    return jobBoardDAO.update(jobBoardIdPk, jobBoard);
  }

  @Override
  public int update(Long jobBoardIdPk, JobBoard jobBoard, List<UploadFile> uploadFiles) {
    jobBoardDAO.update(jobBoardIdPk, jobBoard);
    if (uploadFiles.size() > 0) {
      uploadFiles.stream().forEach(file->file.setRid(jobBoardIdPk));
      uploadFileSVC.addFiles(uploadFiles);
    }

    return 0;
  }

  @Override
  public int delete(Long jobBoardIdPk) {
    return jobBoardDAO.delete(jobBoardIdPk);
  }

  @Override
  public int delete(Long jobBoardIdPk, AttachFileType attachFileType) {
    int cnt = jobBoardDAO.delete(jobBoardIdPk);
    List<UploadFile> uploadFiles = uploadFileSVC.findFilesByCodeWithRid(attachFileType, jobBoardIdPk);


    return 0;
  }

  @Override
  public List<JobBoard> findAll() {
    return jobBoardDAO.findAll();
  }

  @Override
  public List<JobBoard> findAllPaging(int startRec, int endRec) {
    return jobBoardDAO.findAllPaging(startRec,endRec);
  }

  @Override
  public boolean isExist(Long jobBoardIdPk) {
    return jobBoardDAO.isExist(jobBoardIdPk);
  }

  @Override
  public int totalCount() {
    return jobBoardDAO.totalCount();
  }
}
