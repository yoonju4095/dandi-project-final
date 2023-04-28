package com.back.endTest.web;


import com.back.endTest.domain.entity.JobBoard;
import com.back.endTest.domain.entity.MemberPerson;
import com.back.endTest.domain.jobBoard.svc.JobBoardSVC;
import com.back.endTest.domain.member.svc.MemberPersonSVC;
import com.back.endTest.domain.resume.Resume;
import com.back.endTest.domain.resume.ResumeDetail;
import com.back.endTest.domain.resume.svc.ResumeDetailSVC;
import com.back.endTest.domain.resume.svc.ResumeSVC;
import com.back.endTest.domain.resumeApply.ResumeApply;
import com.back.endTest.domain.resumeApply.ResumeApplyDetail;
import com.back.endTest.domain.resumeApply.svc.ResumeApplyDetailSVC;
import com.back.endTest.domain.resumeApply.svc.ResumeApplySVC;
import com.back.endTest.web.form.resumeApply.*;
import com.back.endTest.web.session.LoginCompany;
import com.back.endTest.web.session.LoginPerson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/apply")
public class ResumeApplyController {

  private final JobBoardSVC jobBoardSVC;
  private final ResumeSVC resumeSVC;
  private final MemberPersonSVC memberPersonSVC;

  private final ResumeApplySVC resumeApplySVC;
  private final ResumeApplyDetailSVC resumeApplyDetailSVC;

  private final ResumeDetailSVC resumeDetailSVC;




 @GetMapping("/{bid}/{rid}")   // 구인글번호/이력서번호
 public String apply(
     @PathVariable Long bid, @PathVariable Long rid, HttpServletRequest request,
     Model model
 ){
   HttpSession session = request.getSession(false);
   LoginPerson loginPerson = (LoginPerson)session.getAttribute(SessionConst.LOGIN_PERSON);
   log.info("{},{},{}", bid, rid,loginPerson.getIdPerson());

   Optional<JobBoard> intoJobBoard = jobBoardSVC.inquiry(bid);
   JobBoard jobBoard = intoJobBoard.orElseThrow();

   //조회할 데이터
   //구인글 정보
   ResumeApplyForm resumeApplyForm = new ResumeApplyForm();
   resumeApplyForm.setJobBoardIdPk(jobBoard.getJobBoardIdPk());
   resumeApplyForm.setTitleJob(jobBoard.getTitleJob());
   resumeApplyForm.setIdJob(jobBoard.getIdJob());
   resumeApplyForm.setClosingDate(jobBoard.getClosingDate());
   resumeApplyForm.setNumberOfPersons(jobBoard.getNumberOfPersons());
   resumeApplyForm.setGenderJob(jobBoard.getGenderJob());
   resumeApplyForm.setAcademicAbility(jobBoard.getAcademicAbility());
   resumeApplyForm.setSalaryWay(jobBoard.getSalaryWay());
   resumeApplyForm.setSalaryAmount(jobBoard.getSalaryAmount());
   resumeApplyForm.setWorkPeriod(jobBoard.getWorkPeriod());
   resumeApplyForm.setWorkDay(jobBoard.getWorkDay());
   resumeApplyForm.setComeInJob(jobBoard.getComeInJob());
   resumeApplyForm.setComeOutJob(jobBoard.getComeOutJob());
   resumeApplyForm.setWorkTypeJob(jobBoard.getWorkTypeJob());
   resumeApplyForm.setEmployForm(jobBoard.getEmployForm());
   resumeApplyForm.setBenefitJob(jobBoard.getBenefitJob());
   resumeApplyForm.setPlaceName(jobBoard.getPlaceName());
   resumeApplyForm.setPlaceAddress(jobBoard.getPlaceAddress());
   resumeApplyForm.setDetailContent(jobBoard.getDetailContent());
   resumeApplyForm.setManagerName(jobBoard.getManagerName());
   resumeApplyForm.setManagerPhone(jobBoard.getManagerPhone());

   Resume byResumeId = resumeSVC.findByResumeId(rid);
   resumeApplyForm.setResumeId(byResumeId.getResumeId());
   resumeApplyForm.setIdPerson(byResumeId.getIdPerson());
   resumeApplyForm.setResumeTitle(byResumeId.getResumeTitle());
   resumeApplyForm.setSelfIntro(byResumeId.getSelfIntro());


   MemberPerson byId = memberPersonSVC.findById(loginPerson.getIdPerson());
   resumeApplyForm.setNamePerson(byId.getNamePerson());
   resumeApplyForm.setBirthPerson(byId.getBirthPerson());
   resumeApplyForm.setGenderPerson(byId.getGenderPerson());
   resumeApplyForm.setAddressPerson(byId.getAddressPerson());
   resumeApplyForm.setDetailAddressPerson(byId.getDetailAddressPerson());
   resumeApplyForm.setEmailPerson(byId.getEmailPerson());
   resumeApplyForm.setPhonePerson(byId.getPhonePerson());

   log.info("resumeApplyForm={}",resumeApplyForm);

   ResumeApply resumeApply = new ResumeApply();
   resumeApply.setResumeId(resumeApplyForm.getResumeId());
   resumeApply.setIdPerson(resumeApplyForm.getIdPerson());
   resumeApply.setResumeTitle(resumeApplyForm.getResumeTitle());
   resumeApply.setSelfIntro(resumeApplyForm.getSelfIntro());
   resumeApply.setJobBoardIdPk(resumeApplyForm.getJobBoardIdPk());
   resumeApply.setTitleJob(resumeApplyForm.getTitleJob());
   resumeApply.setIdJob(resumeApplyForm.getIdJob());
   resumeApply.setClosingDate(resumeApplyForm.getClosingDate());
   resumeApply.setNumberOfPersons(resumeApplyForm.getNumberOfPersons());
   resumeApply.setGenderJob(resumeApplyForm.getGenderJob());
   resumeApply.setAcademicAbility(resumeApplyForm.getAcademicAbility());
   resumeApply.setSalaryWay(resumeApplyForm.getSalaryWay());
   resumeApply.setSalaryAmount(resumeApplyForm.getSalaryAmount());
   resumeApply.setWorkPeriod(resumeApplyForm.getWorkPeriod());
   resumeApply.setWorkDay(resumeApplyForm.getWorkDay());
   resumeApply.setComeInJob(resumeApplyForm.getComeInJob());
   resumeApply.setComeOutJob(resumeApplyForm.getComeOutJob());
   resumeApply.setWorkTypeJob(resumeApplyForm.getWorkTypeJob());
   resumeApply.setEmployForm(resumeApplyForm.getEmployForm());
   resumeApply.setBenefitJob(resumeApplyForm.getBenefitJob());
   resumeApply.setPlaceName(resumeApplyForm.getPlaceName());
   resumeApply.setPlaceAddress(resumeApplyForm.getPlaceAddress());
   resumeApply.setDetailContent(resumeApplyForm.getDetailContent());
   resumeApply.setManagerName(resumeApplyForm.getManagerName());
   resumeApply.setManagerPhone(resumeApplyForm.getManagerPhone());
   resumeApply.setNamePerson(resumeApplyForm.getNamePerson());
   resumeApply.setBirthPerson(resumeApplyForm.getBirthPerson());
   resumeApply.setGenderPerson(resumeApplyForm.getGenderPerson());
   resumeApply.setAddressPerson(resumeApplyForm.getAddressPerson());
   resumeApply.setDetailAddressPerson(resumeApplyForm.getDetailAddressPerson());
   resumeApply.setEmailPerson(resumeApplyForm.getEmailPerson());
   resumeApply.setPhonePerson(resumeApplyForm.getPhonePerson());

   log.info("resumeApply={}",resumeApply);

   ResumeApply apply = resumeApplySVC.apply(resumeApply);


   //이력서상세 지원하기
   List<ResumeDetail> byResumeDetailId = resumeDetailSVC.findByResumeDetailId(rid);

   List<ResumeApplyDetail> resumeApplyDetails = new ArrayList<>();
   for(ResumeDetail resumeDetail : byResumeDetailId) {
     ResumeApplyDetail resumeApplyDetail = new ResumeApplyDetail();

     resumeApplyDetail.setResumeDetailId(resumeDetail.getResumeDetailId());
     resumeApplyDetail.setResumeId(resumeDetail.getResumeId());
     resumeApplyDetail.setIdPerson(loginPerson.getIdPerson());
     resumeApplyDetail.setResumeItemId(resumeDetail.getResumeItemId());
     resumeApplyDetail.setBusinessName(resumeDetail.getBusinessName());
     resumeApplyDetail.setBusinessDate(resumeDetail.getBusinessDate());
     resumeApplyDetail.setBusinessWork(resumeDetail.getBusinessWork());

     resumeApplyDetails.add(resumeApplyDetail);
   }


    log.info("resumeApplyDetails={}",resumeApplyDetails);
    log.info("resumeApplyDetails={}",resumeApplyDetails);

   resumeApplyDetailSVC.apply(resumeApplyDetails);


   // 이력서 선택 조회
   List<Resume> list = resumeSVC.findAll(loginPerson.getIdPerson());

   List<ResumeApplyList> resumeApplyLists = new ArrayList<>();
   for(Resume resume : list) {
     ResumeApplyList resumeApplyList = new ResumeApplyList();
     resumeApplyList.setJobBoardIdPk(jobBoard.getJobBoardIdPk());
     resumeApplyList.setResumeId(resume.getResumeId());
     resumeApplyList.setResumeTitle(resume.getResumeTitle());
     resumeApplyLists.add(resumeApplyList);
   }

   log.info("resumeApplyList={}", resumeApplyLists);
   model.addAttribute("resumeApplyLists",resumeApplyLists);



    model.addAttribute("inquiryForm",resumeApply);


   return "jobBoard/jobBoardInquiry";
 }


  @GetMapping("/{idPerson}")
  public String applyList(@PathVariable("idPerson")String idPerson,
                          Model model){

    log.info("applyListIdPerson={}",idPerson);
    List<ResumeApply> all = resumeApplySVC.findAll(idPerson);

    log.info("all={}",all);
    List<ResumeApplyL> resumeApplyLS = new ArrayList<>();
    for(ResumeApply resumeApply : all) {
      ResumeApplyL resumeApplyL = new ResumeApplyL();
      resumeApplyL.setTitleJob(resumeApply.getTitleJob());
      resumeApplyL.setResumeId(resumeApply.getResumeId());
      resumeApplyL.setJobBoardIdPk(resumeApply.getJobBoardIdPk());
      resumeApplyL.setIdPerson(resumeApply.getIdPerson());
      resumeApplyL.setCDate(resumeApply.getCDate());
      resumeApplyLS.add(resumeApplyL);
    }

    log.info("resumeApplyLS={}",resumeApplyLS);
    model.addAttribute("resumeApplyLS",resumeApplyLS);


   return "resumeApply/applyListPerson";
  }

  @GetMapping("/{jobBoardIdPk}/{resumeId}/del")
  public String applyDelete(@PathVariable Long jobBoardIdPk,
                            @PathVariable Long resumeId, RedirectAttributes redirectAttributes){

   resumeApplyDetailSVC.applyDelete(resumeId);
   resumeApplySVC.applyDelete(jobBoardIdPk);


   return "resumeApply/applyListPerson";
  }

  //새로 만들 게시판 게시글
//  @GetMapping("/{idPerson}/{jobBoardIdPk}/JobBoard")
//  public String applyJobBoard(@PathVariable("jobBoardIdPk") Long jobBoardIdPk,
//                              Model model
//                              ){
//
//    Optional<ResumeApply> byJobBoardIdPk = resumeApplySVC.findByJobBoardIdPk(jobBoardIdPk);
//    log.info("byJobBoardIdPk={}",byJobBoardIdPk);
//
//    ResumeApply resumeApply = byJobBoardIdPk.orElseThrow();
//
//    InquiryForm inquiryForm = new InquiryForm();
//    inquiryForm.setJobBoardIdPk(byJobBoardIdPk.get().getJobBoardIdPk());
//    inquiryForm.setTitleJob(byJobBoardIdPk.get().getTitleJob());
//    inquiryForm.setIdJob(byJobBoardIdPk.get().getIdJob());
//    inquiryForm.setClosingDate(byJobBoardIdPk.get().getClosingDate());
//    inquiryForm.setNumberOfPersons(byJobBoardIdPk.get().getNumberOfPersons());
//    inquiryForm.setGenderJob(byJobBoardIdPk.get().getGenderJob());
//    inquiryForm.setAcademicAbility(byJobBoardIdPk.get().getAcademicAbility());
//    inquiryForm.setSalaryWay(byJobBoardIdPk.get().getSalaryWay());
//    inquiryForm.setSalaryAmount(byJobBoardIdPk.get().getSalaryAmount());
//    inquiryForm.setWorkPeriod(byJobBoardIdPk.get().getWorkPeriod());
//    inquiryForm.setWorkDay(byJobBoardIdPk.get().getWorkDay());
//    inquiryForm.setComeInJob(byJobBoardIdPk.get().getComeInJob());
//    inquiryForm.setComeOutJob(byJobBoardIdPk.get().getComeOutJob());
//    inquiryForm.setWorkTypeJob(byJobBoardIdPk.get().getWorkTypeJob());
//    inquiryForm.setEmployForm(byJobBoardIdPk.get().getEmployForm());
//    inquiryForm.setBenefitJob(byJobBoardIdPk.get().getBenefitJob());
//    inquiryForm.setPlaceName(byJobBoardIdPk.get().getPlaceName());
//    inquiryForm.setPlaceAddress(byJobBoardIdPk.get().getPlaceAddress());
//    inquiryForm.setDetailContent(byJobBoardIdPk.get().getDetailContent());
//    inquiryForm.setManagerName(byJobBoardIdPk.get().getManagerName());
//    inquiryForm.setManagerPhone(byJobBoardIdPk.get().getManagerPhone());
//
//    model.addAttribute("inquiryForm", inquiryForm);
//
//    return "resumeApply/applyJobBoar";
//  }
  @GetMapping("/{idCompany}/List")
  public String applyListCom(HttpServletRequest request,
                             Model model){
    HttpSession session = request.getSession(false);
    LoginCompany loginCompany = (LoginCompany) session.getAttribute(SessionConst.LOGIN_COMPANY);
    log.info("loginCompany.getIdCompany={}",loginCompany.getIdCompany());

    List<ResumeApply> allCompany = resumeApplySVC.findAllCompany(loginCompany.getIdCompany());


    log.info("loginCompany.getIdCompany()={}",loginCompany.getIdCompany());
    log.info("loginCompany.getIdCompany()={}",loginCompany.getIdCompany());
    log.info("allCompany={}",allCompany);
    log.info("allCompany={}",allCompany);


    List<ResumePer> list =  new ArrayList<>();
    for(ResumeApply resumeApply : allCompany) {
      ResumePer resumePer = new ResumePer();
      resumePer.setNamePerson(resumeApply.getNamePerson());
      resumePer.setBirthPerson(resumeApply.getBirthPerson());
      resumePer.setGenderPerson(resumeApply.getGenderPerson());
      resumePer.setResumeApplyId(resumeApply.getResumeApplyId());
      resumePer.setResumeId(resumeApply.getResumeId());
      resumePer.setCDate(resumeApply.getCDate());
      list.add(resumePer);
    }

    log.info("list={}",list);
    model.addAttribute("list",list);


    return "resumeApply/applyListCompany";
  }

  @GetMapping("/{resumeApplyId}/{resumeId}/applyDetail")
  public String applyDetail(@PathVariable("resumeApplyId")Long resumeApplyId,
                            @PathVariable("resumeId")Long resumeId,
                            Model model){


   ResumeApply resumeApply = resumeApplySVC.findApply(resumeApplyId);
    ApplyPersonForm applyPersonForm = new ApplyPersonForm();
    applyPersonForm.setNamePerson(resumeApply.getNamePerson());
    applyPersonForm.setGenderPerson(resumeApply.getGenderPerson());
    applyPersonForm.setBirthPerson(resumeApply.getBirthPerson());
    applyPersonForm.setPhonePerson(resumeApply.getPhonePerson());
    applyPersonForm.setAddressPerson(resumeApply.getAddressPerson());
    applyPersonForm.setDetailAddressPerson(resumeApply.getDetailAddressPerson());
    applyPersonForm.setEmailPerson(resumeApply.getEmailPerson());


    List<ResumeApplyDetail> resumeApplyDetails = resumeApplyDetailSVC.findApply(resumeId);

    ResumeApDetail resumeApDetail = new ResumeApDetail();
    resumeApDetail.setResumeId(resumeApply.getResumeId());
    resumeApDetail.setIdPerson(resumeApply.getIdPerson());
    resumeApDetail.setResumeTitle(resumeApply.getResumeTitle());
    resumeApDetail.setSelfIntro(resumeApply.getSelfIntro());

    List<ApplyItem> applyItems = new ArrayList<>();
    for(ResumeApplyDetail resumeApplyDetail : resumeApplyDetails){
      ApplyItem applyItem = new ApplyItem();

      applyItem.setResumeDetailId(resumeApplyDetail.getResumeDetailId());
      applyItem.setResumeId(resumeApplyDetail.getResumeId());
      applyItem.setResumeItemId(resumeApplyDetail.getResumeItemId());
      applyItem.setBusinessName(resumeApplyDetail.getBusinessName());
      applyItem.setBusinessDate(resumeApplyDetail.getBusinessDate());
      applyItem.setBusinessWork(resumeApplyDetail.getBusinessWork());

      applyItems.add(applyItem);
    }


    resumeApDetail.setResumeItems(applyItems);

    model.addAttribute("applyPersonForm",applyPersonForm);
    model.addAttribute("resumeApDetail",resumeApDetail);



   return "resumeApply/applyResumeDetail";
  }
}
