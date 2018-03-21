package me.azno.study.io;

public interface IFileWriter extends Closeable {
    void writeLine(String line);
}
