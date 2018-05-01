package me.azno.study.image.tmp;

import me.azno.study.image.util.RGBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Second {
    private static Logger logger = LoggerFactory.getLogger(Second.class);

    public static void main(String[] args) throws Exception {
        String fileName = "D:\\tmp\\20180501\\DRAGON QUEST XI Echoes of an Elusive Age_20180430140941.jpg";
        File file = new File(fileName);
        BufferedImage image = ImageIO.read(file);
        int width = image.getWidth();
        int height = image.getHeight();
        // pixelRGB(image);
        String outFileName = "D:\\tmp\\20180501\\ex_20180501001.jpg";
//        toAnotherImage(image, new File(outFileName));
        toAnotherImageWithHSB(image, new File(outFileName));
    }

    private static void pixelRGB(BufferedImage image) {
        int rgb0_0 = image.getRGB(image.getMinX(), image.getMinY());
        logger.info("{}", RGBUtil.getRGB(rgb0_0));
    }

    private static void toAnotherImage(BufferedImage image, File outFile) throws IOException {
        Color chooseColor = new Color(237, 237, 237);
        BufferedImage outImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = image.getMinY(); y < image.getHeight(); y++) {
            for (int x = image.getMinX(); x < image.getWidth(); x++) {
                Color color = new Color(image.getRGB(x, y));
                if (isEquals(chooseColor, color)) {
                    outImage.setRGB(x, y, Color.WHITE.getRGB());
                }
            }
        }
        ImageIO.write(outImage, "jpg", outFile);
    }

    private static boolean isEquals(Color chooseColor, Color color) {
        int range = 10;
        return choose(chooseColor.getRed(), color.getRed(), range)
                && choose(chooseColor.getGreen(), color.getGreen(), range)
                && choose(chooseColor.getBlue(), color.getBlue(), range)
                ;
    }

    private static boolean choose(int chooseColor, int color, int range) {
        return color > chooseColor - range
                && color < chooseColor + range;
    }

    private static void toAnotherImageWithHSB(BufferedImage image, File outFile) throws IOException {
        Color chooseColor = new Color(237, 237, 237);
        BufferedImage outImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = image.getMinY(); y < image.getHeight(); y++) {
            for (int x = image.getMinX(); x < image.getWidth(); x++) {
                Color color = new Color(image.getRGB(x, y));
                if (isSelected(color)) {
                    outImage.setRGB(x, y, Color.WHITE.getRGB());
                }
            }
        }
        ImageIO.write(outImage, "jpg", outFile);
    }

    private static boolean isSelected(Color color) {
        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        // return hsb[1] < 0.005 && hsb[2] > 0.90;
        // return (hsb[1] < 0.005 || hsb[2] > 0.99) && hsb[2] > 0.93;
        // && hsb[1] < 0.005&& hsb[2] > 0.93
        // return (hsb[0] <= 330 / 360 || (hsb[0] >= 240 / 360 && hsb[0] <= 220 / 360)) ;
        logger.info("{}", hsb);
        return hsb[0] == 20 / 360;
    }
}
