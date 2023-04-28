package com.back.endTest.web.form.jobReview;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class InquiryReviewForm {
  private Long reviewIdPk;
  private String titleReview;
  private String idReview;
  private String contentReview;
  private String workTypeReview;
  private String workPeriodReview;
  private String workDayReview;
  private String comeInReview;
  private String comeOutReview;
  private double assessReview;
  private Long focusJobBoardId;

  private MultipartFile attachFile; //일반파일
  private List<MultipartFile> imageFiles; //이미지파일
}
