package com.mhsk.wordssak.common.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {
    public static List<String> readCsv(String filePath) {
        List<String> schoolNames = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                schoolNames.add(line.trim());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return schoolNames;
    }
}
