package me.azno.study.io;

public interface IFileReader extends Closeable {
    String readLine();
}
