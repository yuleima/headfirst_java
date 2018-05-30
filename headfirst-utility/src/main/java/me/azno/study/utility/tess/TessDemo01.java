package me.azno.study.utility.tess;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class TessDemo01 {
    private static final Logger logger = LoggerFactory.getLogger("test");

    public static void main(String[] args) {
        String file = "D:\\tmp\\20180501\\tmp-3.png";
        testTess4j(file);
    }

    public static void testTess4j(String filePath) {
        File file = new File(filePath);
        ITesseract instance = new Tesseract();
        File tessDataFolder = LoadLibs.extractTessResources("tessdata");

        String lib = "chi_tra";
        lib = "eng";
        lib = "chi_sim_vert";
//        lib = "chi_tra_vert";

        instance.setLanguage(lib);

        instance.setDatapath(tessDataFolder.getAbsolutePath());

        try {
            String result = instance.doOCR(file);
            logger.info("{}", result);
        } catch (TesseractException e) {
            e.printStackTrace();
        }
    }
}
