package com.back.endTest.web;

import com.back.endTest.domain.common.paging.FindCriteria;
import com.back.endTest.domain.entity.JobBoard;
import com.back.endTest.domain.jobBoard.svc.JobBoardSVC;
import com.back.endTest.domain.search.svc.SearchSVC;
import com.back.endTest.web.form.jobBoard.ListForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/searches")
@RequiredArgsConstructor
public class SearchController {

  private final SearchSVC searchSVC;
  private final JobBoardSVC jobBoardSVC;

  @Autowired
  @Qualifier("fc10")
  private FindCriteria fc;

  //검색-텍스트
  @GetMapping({"/{keyword}","/{keyword}/{reqPage}","/{keyword}/{reqPage}//"})
  public String searchWord(
    @PathVariable("keyword") String keyword,
    @PathVariable(required = false) Optional<Integer> reqPage,
    Model model
  ) {

    fc.getRc().setReqPage(reqPage.orElse(1));
    fc.setTotalRec(jobBoardSVC.totalCount());
    List<JobBoard> searchWords = searchSVC.searchWord(keyword, fc.getRc().getStartRec(), fc.getRc().getEndRec());

    List<ListForm> partOfList = new ArrayList<>();
    for (JobBoard jobBoard : searchWords) {
      ListForm listForm = new ListForm();
      BeanUtils.copyProperties(jobBoard, listForm);
      partOfList.add(listForm);
    }
    model.addAttribute("jobLists", partOfList);
    model.addAttribute("fc", fc);
    model.addAttribute("searchWords", searchWords);

    return "search/resultListAll";
  }

}
