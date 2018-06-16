package me.azno.study.image.tmp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PixleGreyDemo {
    private static final Logger logger = LoggerFactory.getLogger("test");
    private static final String EXTENSION = "jpg";

    public static void main(String[] args) throws Exception {
        PixleGreyDemo demo = new PixleGreyDemo();
        String path = "D:\\temp\\20180604\\20180531\\demos\\demo_files";
        String fileName = "20180527192210";
        String filePath = String.format("%s\\%s.%s", path, fileName, EXTENSION);
        File file = new File(filePath);
        BufferedImage image = ImageIO.read(file);

        BufferedImage greyImg = demo.grey(image);
        demo.writeImage(greyImg, new File(demo.getOutputFileName(file, "grey_h")));
    }

    public BufferedImage grey(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage greyImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int y = image.getMinY(); y < height; y++) {
            for (int x = image.getMinX(); x < width; x++) {
//                int grey = getGrey(image, x, y);
//                greyImg.setRGB(x, y, new Color(grey, grey, grey).getRGB());
                // 九宫格平均像素
                // 类似模糊效果
                int n = 1;
                int sum = 0;
                for (int i = -n; i <= n; i++) {
                    for (int j = -n; j <= n; j++) {
                        sum += getGrey(image, x + i, y + j);
                    }
                }
                int grey = sum / (int) Math.pow((2 * n + 1), 2);
                greyImg.setRGB(x, y, new Color(grey, grey, grey).getRGB());

            }
        }
        return greyImg;
    }


    private int getGrey(BufferedImage image, int x, int y) {
        int w = image.getWidth();
        int h = image.getHeight();
        if (x < 0 || y < 0 || x > w - 1 || y > h - 1) {
            return 255;
        }
        Color color = new Color(image.getRGB(x, y));
        return (color.getRed() + color.getGreen() + color.getBlue()) / 3;
    }

    public int getAverageColor(int[][] gray, int x, int y, int w, int h) {
        int rs = gray[x][y]
                + (x == 0 ? 255 : gray[x - 1][y])
                + (x == 0 || y == 0 ? 255 : gray[x - 1][y - 1])
                + (x == 0 || y == h - 1 ? 255 : gray[x - 1][y + 1])
                + (y == 0 ? 255 : gray[x][y - 1])
                + (y == h - 1 ? 255 : gray[x][y + 1])
                + (x == w - 1 ? 255 : gray[x + 1][y])
                + (x == w - 1 || y == 0 ? 255 : gray[x + 1][y - 1])
                + (x == w - 1 || y == h - 1 ? 255 : gray[x + 1][y + 1]);
        return rs / 9;
    }

    private void writeImage(BufferedImage image, File file) throws IOException {
        ImageIO.write(image, "png", file);
    }

    private String getOutputFileName(File imgFile, String ext) {
        return String.format("%s\\%s_%s.%s", imgFile.getParent(), imgFile.getName(), ext, EXTENSION);
    }

    class Offset {
        int ofsX;
        int ofsY;
    }
}
