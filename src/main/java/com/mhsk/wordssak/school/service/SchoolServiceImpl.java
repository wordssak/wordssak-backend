package com.mhsk.wordssak.school.service;

import com.mhsk.wordssak.common.csv.CsvReader;
import com.mhsk.wordssak.school.entity.School;
import com.mhsk.wordssak.school.repository.SchoolRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.transaction.annotation.Isolation.READ_UNCOMMITTED;

@Service
@Transactional(isolation = READ_UNCOMMITTED, timeout = 60)
public class SchoolServiceImpl implements SchoolService {
    private final SchoolRepository schoolRepository;

    public SchoolServiceImpl(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Override
    public void saveSchoolsFromCsv(String filePath) {
        List<String> schoolNames = CsvReader.readCsv(filePath);
        List<School> schools = schoolNames.stream().map(School::of).collect(Collectors.toList());
        schoolRepository.saveAll(schools);
    }
}
