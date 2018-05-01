package me.azno.study.image.tmp;

import me.azno.study.image.util.RGBUtil;
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
        pixelRGB(image);
    }

    private static void pixelRGB(BufferedImage image) {
        int rgb0_0 = image.getRGB(image.getMinX(), image.getMinY());
        logger.info("{}", RGBUtil.getRGB(rgb0_0));
    }

    private static void toAnotherImage(BufferedImage image) {
        int rgb0_0 = image.getRGB(image.getMinX(), image.getMinY());

        logger.info("{}", RGBUtil.getRGB(rgb0_0));
    }
}
