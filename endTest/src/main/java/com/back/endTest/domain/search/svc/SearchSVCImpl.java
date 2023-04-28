package com.back.endTest.domain.search.svc;

import com.back.endTest.domain.entity.JobBoard;
import com.back.endTest.domain.search.dao.SearchDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchSVCImpl implements SearchSVC {

  private final SearchDAO searchDAO;

  @Override
  public List<JobBoard> searchWord(String keyword, int startRec, int endRec) {
    return searchDAO.searchWord(keyword,startRec,endRec);
  }
}
