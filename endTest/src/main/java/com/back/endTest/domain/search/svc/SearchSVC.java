package com.back.endTest.domain.search.svc;


import com.back.endTest.domain.entity.JobBoard;

import java.util.List;

public interface SearchSVC {

  //검색
  List<JobBoard> searchWord(String keyword, int startRec, int endRec);
}
