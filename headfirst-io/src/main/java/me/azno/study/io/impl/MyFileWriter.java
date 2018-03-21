package me.azno.study.io.impl;

import me.azno.study.io.Closeable;
import me.azno.study.io.IFileWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MyFileWriter implements IFileWriter, Closeable {
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;

    public MyFileWriter(String filePath) {
        try {
            fileWriter = new FileWriter(new File(filePath));
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch (IOException e) {
            close();
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeLine(String line) {
        try {
            bufferedWriter.write(line);
            bufferedWriter.newLine();
        } catch (IOException e) {
            close();
            e.printStackTrace();
        }
    }
}
