package com.mhsk.wordssak.classroom.classwordbook.service;

import com.mhsk.wordssak.classroom.classwordbook.dto.ActiveStatusResponse;
import com.mhsk.wordssak.classroom.classwordbook.dto.RegisterClassWordBookRequest;

public interface ClassWordBookService {
    void registerClassWordBook(RegisterClassWordBookRequest registerClassWordBookRequest);

    ActiveStatusResponse getActiveStatusByClassCode(String classCode);

    ActiveStatusResponse toggleActiveStatus(String classCode);
}
