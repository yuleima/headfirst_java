package me.azno.study.utility.tess;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class TessDemo20180602 {
    private static final Logger logger = LoggerFactory.getLogger("test");
    private static final String TESS_DATAPATH = "D:\\dev\\IdeaProjects\\headfirst_java\\headfirst-utility\\src\\main\\resources\\tessdata";

    public static void main(String[] args) {
        String fileName = "20180602025455";
        fileName = "20180527192210";
        String path = "D:\\tmp\\20180531\\demos\\demo_files";
        String ext = "_buttom";
        ext = "_lvlupmain";
        String filePath = String.format("%s\\%s_crop%s.jpg", path, fileName, ext);
        logger.info("{}", filePath);
        testTess4j(filePath);
    }

    public static void testTess4j(String filePath) {
        File file = new File(filePath);
        ITesseract instance = new Tesseract();

        String lib = "chi_tra";
//        lib = "eng";
//        lib = "chi_sim_vert";
//        lib = "chi_tra_vert";

        instance.setLanguage(lib);

        instance.setDatapath(TESS_DATAPATH);
        // 7 单行文本
        // 6 多行文本
        // 4

        instance.setPageSegMode(6);

        try {
            String result = instance.doOCR(file);
            logger.info("{}", result);
        } catch (TesseractException e) {
            e.printStackTrace();
        }
    }
}
