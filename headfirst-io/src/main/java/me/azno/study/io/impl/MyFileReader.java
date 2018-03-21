package me.azno.study.io.impl;

import me.azno.study.io.IFileReader;

import java.io.*;

public class MyFileReader implements IFileReader {
    private FileReader fileReader;
    private BufferedReader bufferedReader;

    public MyFileReader(String filePath) {
        try {
            fileReader = new FileReader(new File(filePath));
            bufferedReader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            close();
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String readLine() {
        String line = null;
        try {
            line = bufferedReader.readLine();
        } catch (IOException e) {
            close();
            e.printStackTrace();
        }
        return line;
    }
}
