package com.mhsk.wordssak.school.controller;

import com.mhsk.wordssak.school.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/schools")
@RequiredArgsConstructor
public class SchoolController {
    private final SchoolService schoolService;

    @GetMapping()
    public ResponseEntity<List<String>> getThreeSchoolNamesByKeyword(@RequestParam String keyword) {
        List<String> schoolNames = schoolService.getThreeSchoolNames(keyword);

        return ResponseEntity.status(OK).body(schoolNames);
    }
}
