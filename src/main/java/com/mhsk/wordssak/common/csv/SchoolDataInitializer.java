package com.mhsk.wordssak.common.csv;

import com.mhsk.wordssak.school.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchoolDataInitializer implements CommandLineRunner {
    private final SchoolService schoolService;

    @Override
    public void run(String... args) {
        schoolService.saveSchoolsFromCsv("src/main/resources/school_names.csv");
    }
}
