package com.mhsk.wordssak.classroom.service;

import com.mhsk.wordssak.classroom.dto.RegisterClassInfoRequest;
import org.springframework.stereotype.Service;

@Service
public interface ClassService {
    void register(String email, RegisterClassInfoRequest registerClassInfoRequest);
}
