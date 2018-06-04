package me.azno.study.utility.tess;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class TessDemo20180602 {
    private static final Logger logger = LoggerFactory.getLogger("test");
    private static final String TESS_DATAPATH = "D:\\Program Files\\Tesseract-OCR\\tessdata";

    private ITesseract instance;

    public TessDemo20180602() {
        instance = new Tesseract();
        String lib = "chi_tra";
        lib = "eng";
//        lib = "chi_sim_vert";
//        lib = "chi_tra_vert";
//        lib = "dqt";

        instance.setLanguage(lib);
        instance.setDatapath(TESS_DATAPATH);
        // 7 单行文本
        // 6 多行文本
        // 4
        // 8 9 10
        instance.setPageSegMode(6);
    }

    public static void main(String[] args) {
        TessDemo20180602 demo = new TessDemo20180602();
        String fileName = "20180602025455";
        String path = "D:\\temp\\20180604\\ocr\\dz";
        String ext = "png";
        for (int i = 6; i <= 9; i++) {
            String filePath = String.format("%s\\%s.%s", path, i, ext);
            logger.info("{}", filePath);
            demo.testTess4j(filePath);
        }
    }

    public void testTess4j(String filePath) {
        File file = new File(filePath);
        try {
            String result = instance.doOCR(file);
            logger.info("{}", result);
        } catch (TesseractException e) {
            e.printStackTrace();
        }
    }
}
