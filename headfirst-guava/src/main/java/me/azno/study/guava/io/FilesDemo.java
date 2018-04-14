package me.azno.study.guava.io;


//import com.google.common.base.Preconditions;

import com.google.common.io.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// static import 静态引用可以直接使用方法调用
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class FilesDemo {
    private static final Logger logger = LoggerFactory.getLogger(FilesDemo.class);

    public static void main(String[] args) {
        FilesDemo demo = new FilesDemo();
        String filePath = "files/test_file_01";
        String file = getAbsolutePath(filePath);
        String contends = "have a test.";
        try {
            demo.fileWrite(file, contends);
//            loopList(demo.fileRead(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void loopList(List<String> lines) {
        for (String line :
                lines) {
            logger.info("{}", line);
        }
    }

    private static String getAbsolutePath(String filePath) {
        return checkNotNull(
                FilesDemo.class.getClassLoader().getResource(filePath), "filePath null."
        ).getFile();
    }

    private void fileWrite(final String fileName, final String contents) throws IOException {
        File file = new File(fileName);
        Files.write(checkNotNull(contents, "contents null").getBytes(), file);
        // todo 这个方法写不进去内容。待查

    }

    private List<String> fileRead(final String fileName) throws IOException {
        return Files.readLines(new File(fileName), Charset.defaultCharset());
    }
}
