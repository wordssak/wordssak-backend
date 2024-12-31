package com.mhsk.wordssak.school.service;

import org.springframework.stereotype.Service;

@Service
public interface SchoolService {
    void saveSchoolsFromCsv(String filePath);
}
