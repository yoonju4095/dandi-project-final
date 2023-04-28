package com.back.endTest.web;

import com.back.endTest.domain.common.file.svc.UploadFileSVC;
import com.back.endTest.domain.entity.JobReview;
import com.back.endTest.domain.entity.UploadFile;
import com.back.endTest.domain.jobReview.svc.JobReviewSVC;
import com.back.endTest.web.common.AttachFileType;
import com.back.endTest.web.form.jobReview.InquiryReviewForm;
import com.back.endTest.web.form.jobReview.SaveReviewForm;
import com.back.endTest.web.form.jobReview.UpdateReviewForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("jobBoards")
@RequiredArgsConstructor
public class JobReviewController {

  private final JobReviewSVC jobReviewSVC;
  private final UploadFileSVC uploadFileSVC;

  //후기글 등록 양식
  @GetMapping("/{id}/inquiry/addJobReview")
  public String saveForm(Model model) {
    SaveReviewForm saveReviewForm = new SaveReviewForm();
    model.addAttribute("saveReviewForm", saveReviewForm);
    return "review/jobReviewAdd";
  }

  //후기글 등록 처리
  @PostMapping("/{id}/inquiry/addJobReview")
  public String save(
      @Valid @ModelAttribute SaveReviewForm saveReviewForm,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes
  ) {
    //어노테이션 기반 데이터 검증
    if (bindingResult.hasErrors()) {
      log.info("bindingResult={}", bindingResult);
      return "review/jobReviewAdd";
    }

    //등록
    JobReview jobReview = new JobReview();
    jobReview.setTitleReview(saveReviewForm.getTitleReview());
    jobReview.setIdReview(saveReviewForm.getIdReview());
    jobReview.setContentReview(saveReviewForm.getContentReview());
//    jobReview.setWorkTypeReview(saveReviewForm.getWorkTypeReview());
//    jobReview.setWorkPeriodReview(saveReviewForm.getWorkPeriodReview());
//    jobReview.setWorkDayReview(saveReviewForm.getWorkDayReview());
//    jobReview.setComeInReview(saveReviewForm.getComeInReview());
//    jobReview.setComeOutReview(saveReviewForm.getComeOutReview());
    jobReview.setAssessReview(saveReviewForm.getAssessReview());
    jobReview.setFocusJobBoardId(saveReviewForm.getFocusJobBoardId());

    //파일첨부에 대한 메타정보 추출과 물리파일 저장
    UploadFile attachFile = uploadFileSVC.convert(saveReviewForm.getAttachFile(), AttachFileType.F0101);
    List<UploadFile> imageFiles = uploadFileSVC.convert(saveReviewForm.getImageFiles(), AttachFileType.F0101);
    if (attachFile != null) {
      imageFiles.add(attachFile);
    }

    //등록
    Long saveJobReviewIdPk = jobReviewSVC.save(jobReview, imageFiles);
    redirectAttributes.addAttribute("reviewId", saveJobReviewIdPk);

    return "redirect:/jobBoards/{id}/inquiry/{reviewId}/detail";
  }

  //후기글 조회
  @GetMapping("/{id}/inquiry/{reviewId}/detail")
  public String inquiryJobReview(
      @PathVariable("reviewId") Long id,
      Model model
  ) {
    Optional<JobReview> intoJobReview = jobReviewSVC.inquiry(id);
    JobReview jobReview = intoJobReview.orElseThrow();

    //조회할 데이터
    InquiryReviewForm inquiryReviewForm = new InquiryReviewForm();
    inquiryReviewForm.setReviewIdPk(jobReview.getReviewIdPk());
    inquiryReviewForm.setTitleReview(jobReview.getTitleReview());
    inquiryReviewForm.setIdReview(jobReview.getIdReview());
    inquiryReviewForm.setContentReview(jobReview.getContentReview());
    inquiryReviewForm.setWorkTypeReview(jobReview.getWorkTypeReview());
    inquiryReviewForm.setWorkPeriodReview(jobReview.getWorkPeriodReview());
    inquiryReviewForm.setWorkDayReview(jobReview.getWorkDayReview());
    inquiryReviewForm.setComeInReview(jobReview.getComeInReview());
    inquiryReviewForm.setComeOutReview(jobReview.getComeOutReview());
    inquiryReviewForm.setAssessReview(jobReview.getAssessReview());

    //첨부파일 조회
    List<UploadFile> imagedFiles = uploadFileSVC.findFilesByCodeWithRid(AttachFileType.F0101, id);
    if(imagedFiles.size() > 0) {
      model.addAttribute("imagedFiles", imagedFiles);
    };

    model.addAttribute("inquiryReviewForm", inquiryReviewForm);
    return "review/jobReviewInquiry";
  }

  //후기글 수정 양식
  @GetMapping("/{id}/inquiry/{reviewId}/modify")
  public String updateForm(
      @PathVariable("reviewId") Long id,
      Model model
  ) {
    Optional<JobReview> intoJobReview = jobReviewSVC.inquiry(id);
    JobReview jobReview = intoJobReview.orElseThrow();

    UpdateReviewForm updateReviewForm = new UpdateReviewForm();
    updateReviewForm.setTitleReview(jobReview.getTitleReview());
    updateReviewForm.setIdReview(jobReview.getIdReview());
    updateReviewForm.setContentReview(jobReview.getContentReview());
    updateReviewForm.setWorkTypeReview(jobReview.getWorkTypeReview());
    updateReviewForm.setWorkPeriodReview(jobReview.getWorkPeriodReview());
    updateReviewForm.setWorkDayReview(jobReview.getWorkDayReview());
    updateReviewForm.setComeInReview(jobReview.getComeInReview());
    updateReviewForm.setComeOutReview(jobReview.getComeOutReview());
    updateReviewForm.setAssessReview(jobReview.getAssessReview());

    //첨부파일 조회
    List<UploadFile> imagedFiles = uploadFileSVC.findFilesByCodeWithRid(AttachFileType.F0101, id);
    if(imagedFiles.size() > 0) {
      model.addAttribute("imagedFiles", imagedFiles);
    };

    model.addAttribute("updateReviewForm", updateReviewForm);
    return "review/jobReviewModify";
  }

  //후기글 수정
  @PostMapping("/{id}/inquiry/{reviewId}/modify")
  public String update(
      @PathVariable("reviewId") Long id,
      @Valid @ModelAttribute UpdateReviewForm updateReviewForm,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes
  ) {
    //어노테이션 기반 데이터 검증
    if (bindingResult.hasErrors()) {
      log.info("bindingResult={}", bindingResult);
      return "review/jobReviewModify";
    }

    //수정 처리
    JobReview jobReview = new JobReview();
    jobReview.setReviewIdPk(id);
    jobReview.setTitleReview(updateReviewForm.getTitleReview());
    jobReview.setIdReview(updateReviewForm.getIdReview());
    jobReview.setContentReview(updateReviewForm.getContentReview());
    jobReview.setWorkTypeReview(updateReviewForm.getWorkTypeReview());
    jobReview.setWorkPeriodReview(updateReviewForm.getWorkPeriodReview());
    jobReview.setWorkDayReview(updateReviewForm.getWorkDayReview());
    jobReview.setComeInReview(updateReviewForm.getComeInReview());
    jobReview.setComeOutReview(updateReviewForm.getComeOutReview());
    jobReview.setAssessReview(updateReviewForm.getAssessReview());

    //파일첨부
    List<UploadFile> imageFile = uploadFileSVC.convert(updateReviewForm.getImageFiles(), AttachFileType.F0101);

    jobReviewSVC.update(id, jobReview, imageFile);
    redirectAttributes.addAttribute("reviewId", id);
    return "redirect:/jobBoards/{id}/inquiry/{reviewId}/detail";
  }

  //후기글 삭제
  @GetMapping("/{id}/inquiry/{reviewId}/deleteJobReview")
  public String delete(@PathVariable("reviewId") Long id) {
    jobReviewSVC.delete(id);
    return "redirect:/jobBoards/{id}/inquiry";
  }
  //후기글 목록은 job board controller 의 조회 부분에 있음.
}
