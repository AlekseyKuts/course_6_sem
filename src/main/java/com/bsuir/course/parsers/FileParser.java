package com.bsuir.course.parsers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public final class FileParser {
    private static FileParser instance;
    private String filename;
    private static Map<String, String> map;

    private FileParser() {
        // Этот код эмулирует медленную инициализацию.


    }

    public static FileParser getInstance() {
        if (instance == null) {
            instance = new FileParser();
        }
        return instance;
    }

    public void loadFile(String filename){
        this.filename = filename;
        map = new HashMap<>();
        String[] input = new String[2];
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                input = line.split("===");
                map.put(input[0], input[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getValue(String parameter){
        return map.get(parameter);
    }
}