package com.mhsk.wordssak.classroom.util;

import java.util.Base64;

public class CodeGenerator {
    public static String generate() {
        return Base64.getEncoder().encodeToString(String.valueOf(System.currentTimeMillis()).getBytes());
    }
}
