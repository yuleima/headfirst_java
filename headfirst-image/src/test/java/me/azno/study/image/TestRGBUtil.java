package me.azno.study.image;

import me.azno.study.image.util.RGBUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TestRGBUtil {
    private static Logger logger = LoggerFactory.getLogger(TestRGBUtil.class);
    private String filePath;
    private BufferedImage image;

    @Before
    public void befeor() {
        filePath = "D:\\tmp\\20180501\\DRAGON QUEST XI Echoes of an Elusive Age_20180430140941.jpg";
        File file = new File(filePath);
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        int pixel_0_0 = image.getRGB(image.getMinX(), image.getMinY());
        int[] rgb = RGBUtil.getRGB(pixel_0_0);
        logger.info("{}", rgb);
        Color color = new Color(pixel_0_0);
        logger.info("{}", color);
        Color.RGBtoHSB(color.getRed(),color.getGreen(),color.getBlue(),null);

    }

    @After
    public void after() {

    }
}
