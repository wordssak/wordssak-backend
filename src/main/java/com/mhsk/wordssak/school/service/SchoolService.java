package com.mhsk.wordssak.school.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SchoolService {
    void saveSchoolsFromCsv(String filePath);

    List<String> getThreeSchoolNames(String keyword);
}
