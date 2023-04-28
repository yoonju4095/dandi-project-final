package com.back.endTest.web;

import com.back.endTest.domain.common.file.svc.UploadFileSVC;
import com.back.endTest.domain.common.paging.FindCriteria;
import com.back.endTest.domain.entity.JobBoard;
import com.back.endTest.domain.entity.JobReview;
import com.back.endTest.domain.entity.UploadFile;
import com.back.endTest.domain.jobBoard.svc.JobBoardSVC;
import com.back.endTest.domain.jobReview.svc.JobReviewSVC;
import com.back.endTest.domain.resume.Resume;
import com.back.endTest.domain.resume.svc.ResumeSVC;
import com.back.endTest.web.common.AttachFileType;
import com.back.endTest.web.form.jobBoard.InquiryForm;
import com.back.endTest.web.form.jobBoard.ListForm;
import com.back.endTest.web.form.jobBoard.SaveForm;
import com.back.endTest.web.form.jobBoard.UpdateForm;
import com.back.endTest.web.form.resumeApply.ResumeApplyList;
import com.back.endTest.web.session.LoginPerson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("jobBoards")
@RequiredArgsConstructor
public class JobBoardController {

  private final JobBoardSVC jobBoardSVC;
  private final JobReviewSVC jobReviewSVC;
  private final UploadFileSVC uploadFileSVC;
  private final ResumeSVC resumeSVC;

  @Autowired
  @Qualifier("fc10")
  private FindCriteria fc;

  //구인글 등록 양식
  @GetMapping("/createBoard")
  public String saveForm(Model model) {
    SaveForm saveForm = new SaveForm();
    model.addAttribute("saveForm", saveForm);
    return "jobBoard/jobBoardAdd";
  }

  //구인글 등록 처리
  @PostMapping("/createBoard")
  public String save(
      @Valid @ModelAttribute SaveForm saveForm,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes
  ) {
    //어노테이션 기반 데이터 검증
    if (bindingResult.hasErrors()) {
      log.info("bindingResult={}", bindingResult);
      return "jobBoard/jobBoardAdd";
    }

    //등록
    JobBoard jobBoard = new JobBoard();
    jobBoard.setTitleJob(saveForm.getTitleJob());
    jobBoard.setIdJob(saveForm.getIdJob());
    jobBoard.setClosingDate(saveForm.getClosingDate());
    jobBoard.setNumberOfPersons(saveForm.getNumberOfPersons());
    jobBoard.setGenderJob(saveForm.getGenderJob());
    jobBoard.setAcademicAbility(saveForm.getAcademicAbility());
    jobBoard.setSalaryWay(saveForm.getSalaryWay());
    jobBoard.setSalaryAmount(saveForm.getSalaryAmount());
    jobBoard.setWorkPeriod(saveForm.getWorkPeriod());
    jobBoard.setWorkDay(saveForm.getWorkDay());
    jobBoard.setComeInJob(saveForm.getComeInJob());
    jobBoard.setComeOutJob(saveForm.getComeOutJob());
    jobBoard.setWorkTypeJob(saveForm.getWorkTypeJob());
    jobBoard.setEmployForm(saveForm.getEmployForm());
    jobBoard.setBenefitJob(saveForm.getBenefitJob());
    jobBoard.setPlaceName(saveForm.getPlaceName());
    jobBoard.setPlaceAddress(saveForm.getPlaceAddress());
    jobBoard.setDetailContent(saveForm.getDetailContent());
    jobBoard.setManagerName(saveForm.getManagerName());
    jobBoard.setManagerPhone(saveForm.getManagerPhone());

    //파일첨부에 대한 메타정보 추출과 물리파일 저장
    UploadFile attachFile = uploadFileSVC.convert(saveForm.getAttachFile(), AttachFileType.F0101);
    List<UploadFile> imageFiles = uploadFileSVC.convert(saveForm.getImageFiles(), AttachFileType.F0101);
    if (attachFile != null) {
      imageFiles.add(attachFile);
    }
    //등록

    Long saveJobBoardIdPk = jobBoardSVC.save(jobBoard, imageFiles);
    redirectAttributes.addAttribute("id", saveJobBoardIdPk);

    return "redirect:/jobBoards/{id}/inquiry";
  }

  //구인글 조회
  @GetMapping("/{id}/inquiry")
  public String inquiryJobBoard(
      @PathVariable("id") Long id,
      HttpServletRequest request,
      Model model
  ) {
    Optional<JobBoard> intoJobBoard = jobBoardSVC.inquiry(id);
    JobBoard jobBoard = intoJobBoard.orElseThrow();

    //조회할 데이터
    //구인글 정보
    InquiryForm inquiryForm = new InquiryForm();
    inquiryForm.setJobBoardIdPk(jobBoard.getJobBoardIdPk());
    inquiryForm.setTitleJob(jobBoard.getTitleJob());
    inquiryForm.setIdJob(jobBoard.getIdJob());
    inquiryForm.setClosingDate(jobBoard.getClosingDate());
    inquiryForm.setNumberOfPersons(jobBoard.getNumberOfPersons());
    inquiryForm.setGenderJob(jobBoard.getGenderJob());
    inquiryForm.setAcademicAbility(jobBoard.getAcademicAbility());
    inquiryForm.setSalaryWay(jobBoard.getSalaryWay());
    inquiryForm.setSalaryAmount(jobBoard.getSalaryAmount());
    inquiryForm.setWorkPeriod(jobBoard.getWorkPeriod());
    inquiryForm.setWorkDay(jobBoard.getWorkDay());
    inquiryForm.setComeInJob(jobBoard.getComeInJob());
    inquiryForm.setComeOutJob(jobBoard.getComeOutJob());
    inquiryForm.setWorkTypeJob(jobBoard.getWorkTypeJob());
    inquiryForm.setEmployForm(jobBoard.getEmployForm());
    inquiryForm.setBenefitJob(jobBoard.getBenefitJob());
    inquiryForm.setPlaceName(jobBoard.getPlaceName());
    inquiryForm.setPlaceAddress(jobBoard.getPlaceAddress());
    inquiryForm.setDetailContent(jobBoard.getDetailContent());
    inquiryForm.setManagerName(jobBoard.getManagerName());
    inquiryForm.setManagerPhone(jobBoard.getManagerPhone());

    //첨부파일 조회
    List<UploadFile> imagedFiles = uploadFileSVC.findFilesByCodeWithRid(AttachFileType.F0101, id);
    if(imagedFiles.size() > 0) {
      model.addAttribute("imagedFiles", imagedFiles);
    };
    model.addAttribute("inquiryForm", inquiryForm);

    List<JobReview> jobReviews = jobReviewSVC.findAll(id);
    model.addAttribute("jobReviews", jobReviews);


//    이력서 선택 조회
    try {
      HttpSession session = request.getSession(false);
      LoginPerson loginPerson = (LoginPerson) session.getAttribute(SessionConst.LOGIN_PERSON);
      log.info("idPerson={}", loginPerson.getIdPerson());
      List<Resume> list = resumeSVC.findAll(loginPerson.getIdPerson());

      List<ResumeApplyList> resumeApplyLists = new ArrayList<>();
      for (Resume resume : list) {
        ResumeApplyList resumeApplyList = new ResumeApplyList();
        resumeApplyList.setJobBoardIdPk(jobBoard.getJobBoardIdPk());
        resumeApplyList.setResumeId(resume.getResumeId());
        resumeApplyList.setResumeTitle(resume.getResumeTitle());
        resumeApplyLists.add(resumeApplyList);
      }
      log.info("resumeApplyList={}", resumeApplyLists);
      model.addAttribute("resumeApplyLists", resumeApplyLists);
    }catch (NullPointerException e){

    }





    return "jobBoard/jobBoardInquiry";
  }
  //구인글 수정 양식
  @GetMapping("/{id}/modify")
  public String updateForm(
      @PathVariable("id") Long id,
      Model model
  ) {
    Optional<JobBoard> finedJobBoard = jobBoardSVC.inquiry(id);
    JobBoard jobBoard = finedJobBoard.orElseThrow();

    UpdateForm updateForm = new UpdateForm();
    updateForm.setTitleJob(jobBoard.getTitleJob());
    updateForm.setClosingDate(jobBoard.getClosingDate());
    updateForm.setNumberOfPersons(jobBoard.getNumberOfPersons());
    updateForm.setGenderJob(jobBoard.getGenderJob());
    updateForm.setAcademicAbility(jobBoard.getAcademicAbility());
    updateForm.setSalaryWay(jobBoard.getSalaryWay());
    updateForm.setSalaryAmount(jobBoard.getSalaryAmount());
    updateForm.setWorkPeriod(jobBoard.getWorkPeriod());
    updateForm.setWorkDay(jobBoard.getWorkDay());
    updateForm.setComeInJob(jobBoard.getComeInJob());
    updateForm.setComeOutJob(jobBoard.getComeOutJob());
    updateForm.setWorkTypeJob(jobBoard.getWorkTypeJob());
    updateForm.setEmployForm(jobBoard.getEmployForm());
    updateForm.setBenefitJob(jobBoard.getBenefitJob());
    updateForm.setPlaceName(jobBoard.getPlaceName());
    updateForm.setPlaceAddress(jobBoard.getPlaceAddress());
    updateForm.setDetailContent(jobBoard.getDetailContent());
    updateForm.setManagerName(jobBoard.getManagerName());
    updateForm.setManagerPhone(jobBoard.getManagerPhone());

    //첨부파일 조회
    List<UploadFile> imagedFiles = uploadFileSVC.findFilesByCodeWithRid(AttachFileType.F0101, id);
    if(imagedFiles.size() > 0) {
      model.addAttribute("imagedFiles", imagedFiles);
    };

    model.addAttribute("updateForm", updateForm);
    return "jobBoard/jobBoardModify";
  }

  //구인글 수정
  @PostMapping("/{id}/modify")
  public String update(
      @PathVariable("id") Long id,
      @Valid @ModelAttribute UpdateForm updateForm,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes
  ) {
    //어노테이션 기반 데이터 검증
    if (bindingResult.hasErrors()) {
      log.info("bindingResult={}", bindingResult);
      return "jobBoard/jobBoardModify";
    }

    //수정 처리
    JobBoard jobBoard = new JobBoard();
    jobBoard.setJobBoardIdPk(id);
    jobBoard.setTitleJob(updateForm.getTitleJob());
    jobBoard.setClosingDate(updateForm.getClosingDate());
    jobBoard.setNumberOfPersons(updateForm.getNumberOfPersons());
    jobBoard.setGenderJob(updateForm.getGenderJob());
    jobBoard.setAcademicAbility(updateForm.getAcademicAbility());
    jobBoard.setSalaryWay(updateForm.getSalaryWay());
    jobBoard.setSalaryAmount(updateForm.getSalaryAmount());
    jobBoard.setWorkPeriod(updateForm.getWorkPeriod());
    jobBoard.setWorkDay(updateForm.getWorkDay());
    jobBoard.setComeInJob(updateForm.getComeInJob());
    jobBoard.setComeOutJob(updateForm.getComeOutJob());
    jobBoard.setWorkTypeJob(updateForm.getWorkTypeJob());
    jobBoard.setEmployForm(updateForm.getEmployForm());
    jobBoard.setBenefitJob(updateForm.getBenefitJob());
    jobBoard.setPlaceName(updateForm.getPlaceName());
    jobBoard.setPlaceAddress(updateForm.getPlaceAddress());
    jobBoard.setDetailContent(updateForm.getDetailContent());
    jobBoard.setManagerName(updateForm.getManagerName());
    jobBoard.setManagerPhone(updateForm.getManagerPhone());

    //파일첨부
    List<UploadFile> imageFile = uploadFileSVC.convert(updateForm.getImageFiles(), AttachFileType.F0101);

    jobBoardSVC.update(id, jobBoard, imageFile);
    redirectAttributes.addAttribute("id", id);
    return "redirect:/jobBoards/{id}/inquiry";
  }

  //구인글 삭제
  @GetMapping("/{id}/deleteJobBoard")
  public String delete(@PathVariable("id") Long id) {
    jobBoardSVC.delete(id);
    return "redirect:/jobBoards";
  }

  //구인글 목록
//  @GetMapping
//  public String list(Model model) {
//    List<JobBoard> jobLists = jobBoardSVC.findAll();
//    model.addAttribute("jobLists", jobLists);
//    return "jobBoard/jobBoardList";
//  }

  //구인글 목록 페이징
  @GetMapping({"", "/{reqPage}", "/{reqPage}//"})
  public String listPaging(
      @PathVariable(required = false) Optional<Integer> reqPage,
      Model model
  ) {

    fc.getRc().setReqPage(reqPage.orElse(1));
    fc.setTotalRec(jobBoardSVC.totalCount());
    List<JobBoard> jobListsPaging = jobBoardSVC.findAllPaging(fc.getRc().getStartRec(), fc.getRc().getEndRec());

    List<ListForm> partOfList = new ArrayList<>();
    for (JobBoard jobBoard : jobListsPaging) {
      ListForm listForm = new ListForm();
      BeanUtils.copyProperties(jobBoard, listForm);
      partOfList.add(listForm);
    }
    model.addAttribute("jobLists", partOfList);
    model.addAttribute("fc", fc);

    return "jobBoard/jobBoardList";
  }
}
