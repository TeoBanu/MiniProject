package com.msg.proj.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class FileHandler {
    private static final String FILE_PATH = "C:/Users/banut/Desktop/CVs/";

    public String read(String filename) throws Exception {
        filename = FILE_PATH + filename;
        StringBuilder allLines = new StringBuilder();
        String line;
        try (FileReader fr = new FileReader(filename);
             BufferedReader br = new BufferedReader(fr)) {
            while ((line = br.readLine()) != null) {
                allLines.append(line + " ");
            }
        } catch (FileNotFoundException e) {
            throw new Exception("Insert the name of an existing file.");
        }
        return allLines.toString();
    }
}
