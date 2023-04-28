package com.back.endTest.domain.search.dao;

import com.back.endTest.domain.entity.JobBoard;

import java.util.List;

public interface SearchDAO {

  //검색
  List<JobBoard> searchWord(String keyword, int startRec, int endRec);
}
