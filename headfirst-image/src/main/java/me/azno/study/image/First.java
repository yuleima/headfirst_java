package me.azno.study.image;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class First {
    private static Logger logger = LoggerFactory.getLogger(First.class);

    public static void main(String[] args) throws Exception {
        String fileName = "D:\\tmp\\20180501\\DRAGON QUEST XI Echoes of an Elusive Age_20180430140941.jpg";
        File file = new File(fileName);
        BufferedImage image = ImageIO.read(file);
        int width = image.getWidth();
        int height = image.getHeight();
        int rgb0_0 = image.getRGB(0, 0);
        logger.info("{}", rgb0_0);
    }
}
