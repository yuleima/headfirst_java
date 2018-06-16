package me.azno.tools.files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class ModifyFilesName {
    private static final Logger logger = LoggerFactory.getLogger(ModifyFilesName.class);
    private static final String replace = "DRAGON QUEST XI Echoes of an Elusive Age_";

    public static void main(String[] args) throws Exception {
        String folderPath = "G:\\PS4\\SHARE\\Video Clips\\DRAGON QUEST XI Echoes of an Elusive Age";
        folderPath = "G:\\PS4\\SHARE\\Screenshots\\DRAGON QUEST XI Echoes of an Elusive Age";
        ModifyFilesName obj = new ModifyFilesName();
        obj.renameFilesInFolder(folderPath);
    }

    private void renameFilesInFolder(String folderPath) throws Exception {
        File folder = new File(folderPath);
        if (!folder.isDirectory()) {
            throw new Exception(String.format("%s is not a directory", folder.getAbsolutePath()));
        }
        File[] files = folder.listFiles();
        for (File file : files) {
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
