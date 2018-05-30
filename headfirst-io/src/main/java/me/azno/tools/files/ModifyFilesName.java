package me.azno.tools.files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class ModifyFilesName {
    private static final Logger logger = LoggerFactory.getLogger(ModifyFilesName.class);
    private static final String replace = "abc_";

    public static void main(String[] args) throws Exception {
        // 参数，一个目录
        String folderPath = "D:\\temp\\20180530";
        ModifyFilesName obj = new ModifyFilesName();
        obj.renameFilesInFolder(folderPath);
    }

    private void renameFilesInFolder(String folderPath) throws Exception {
        // 遍历目录，获得文件
        File folder = new File(folderPath);
        if (!folder.isDirectory()) {
            throw new Exception(String.format("%s is not a directory", folder.getAbsolutePath()));
        }
        File[] files = folder.listFiles();
        for (File file : files) {
            // 修改文件名
            rename(file);
        }
    }

    private void rename(File file) {
        String destName = getDestName(file);
        if (!destName.equals(file.getName())) {
            file.renameTo(new File(
                    String.format("%s%s%s", file.getParent(), File.separatorChar, destName)
            ));
        }
    }

    private String getDestName(File file) {
        String[] array = file.getName().split("\\.");
        String fileName = array[0];
        if (fileName.indexOf(replace) == 0) {
            fileName = fileName.substring(replace.length());
        }
        String extension = array.length > 1 ? "." + array[1] : "";
        return String.format("%s%s", fileName, extension);
    }
}
