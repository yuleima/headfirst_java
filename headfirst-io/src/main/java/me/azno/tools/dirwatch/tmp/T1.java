package me.azno.tools.dirwatch.tmp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class T1 {
    private static final Logger logger = LoggerFactory.getLogger("test");

    public static void main(String[] args) throws Exception {
        String path = "C:\\Users\\leigh";
        path = "C:\\Users\\leigh\\AppData\\Local\\Microsoft\\Windows\\INetCache\\Low";
        path = "C:\\Users\\leigh\\AppData\\Local\\Microsoft\\Windows";
        path = "C:\\Users\\leigh\\AppData\\Roaming\\Citra";
//        path = "C:\\Users\\leigh\\AppData\\Roaming\\.mono";
        File root = new File(path);
//        list(root);
//        listFiles(root);
//        space(root);
        Map<String, File> map = new HashMap<>();
//        recursion(root, map);
//        recursion2(root, map);
        recursion3(root, map);
        logger.info("{}", map.size());
//        for (Map.Entry<String, File> entry : map.entrySet()) {
//            logger.info("{}", entry.getKey());
//        }
        long fileCount = map.values().stream().filter(File::isFile).count();
        long dirCount = map.values().stream().filter(File::isDirectory).count();
        logger.info("{} {} ", fileCount, dirCount);
    }

    private static void space(File file) {
        logger.info("TotalSpace:{} , UsableSpace:{}", file.getTotalSpace(), file.getUsableSpace());
    }

    private static void list(File file) {
        for (String fileName : file.list()) {
            logger.info("{}", fileName);
        }
    }

    private static void listFiles(File path) {
        for (File file : path.listFiles()) {
            logger.info(
                    "{} {} {} {}",
                    file.getName(),
                    file.isFile() ? "file" : "-",
                    file.isDirectory() ? "dir" : "-",
                    file.isHidden() ? "hidden" : "-"
            );
        }

    }

    private static void recursion(File path, Map<String, File> map) throws Exception {
        if (map == null)
            throw new Exception("map is null");
//        map.put(path.getName(), path);
        if (path.isDirectory()) {
            try {
                File[] files = path.listFiles();
                if (files != null) {
                    for (File file : files) {
                        map.put(file.getAbsolutePath(), file);
                        if (file.isDirectory())
                            recursion(file, map);
                    }
                }
            } catch (Throwable e) {
                logger.error("path {} ", path);
                e.printStackTrace();
            }

        }

    }

    private static void recursion2(File path, Map<String, File> map) throws Exception {
        if (map == null)
            throw new Exception("map is null");
        File[] files = path.listFiles();
        if (files != null) {
            for (File file : files) {
                map.put(file.getAbsolutePath(), file);
                if (file.isDirectory())
                    recursion2(file, map);

            }
        }
    }

    private static void recursion3(File path, Map<String, File> map) throws Exception {
        if (map == null)
            throw new Exception("map is null");
        map.put(path.getAbsolutePath(), path);
        if (path.isDirectory()) {
            File[] files = path.listFiles();
            if (files != null)
                for (File file : files) {
                    if (path.isDirectory()) {
                        recursion3(file, map);
                    }
                }
        }
    }

}
