package me.azno.study.image.tmp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 模仿PS的裁切
 */
public class CutImgDemo {
    private static final Logger logger = LoggerFactory.getLogger("test");

    public static void main(String[] args) throws Exception {
        CutImgDemo demo = new CutImgDemo();
        String path = "D:\\temp\\20180604\\ocr\\cut";
        String fileName = "1";

        String ext = "png";

        String filePath = String.format("%s\\%s.%s", path, fileName, ext);
        File file = new File(filePath);
        BufferedImage image = ImageIO.read(file);
        Box box = demo.cut(image);

        BufferedImage cropImg = demo.cropImage(image, box);
        demo.writeImage(cropImg, new File(demo.getOutputFileName(file, "cut")));
    }

    public Box cut(BufferedImage image) {
        final int match = 255;
        int width = image.getWidth();
        int height = image.getHeight();
        logger.info("width {}, height {}", width, height);
        int sX = 0, sY = 0, sW = 0, sH = 0;
        // 这两个数组命名有歧义
        int[] arrayX = new int[width];
        int[] arrayY = new int[height];
        // 按列扫描, y在height上递增，
        // x在width上递增，获取每列的match数
        for (int x = 0; x < width; x++) {
            int count = 0;
            for (int y = 0; y < height; y++) {
                Color color = new Color(image.getRGB(x, y));
                int grey = getGrey(color);
                if (grey == match) {
                    count += 1;
                }
            }
            arrayX[x] = count;
//            logger.info("x列 {}, count {}", x, count);
        }

        // x start，从0开始，
        for (int x = 0; x < width; x++) {
            if (arrayX[x] != height) {
                sX = x;
                break;
            }
        }
        // y end
        for (int x = width - 1; x >= 0; x--) {
            if (arrayX[x] != height) {
                sW = x - sX + 1;
                break;
            }
        }
        // 按行扫描
        for (int y = 0; y < height; y++) {
            int count = 0;
            for (int x = 0; x < width; x++) {
                Color color = new Color(image.getRGB(x, y));
                int grey = getGrey(color);
                if (grey == match) {
                    count += 1;
                }
            }
            arrayY[y] = count;
//            logger.info("y行 {}, count {}", y, count);
        }
        // y start，找到第一个不等于width的
        for (int y = 0; y < height; y++) {
            if (arrayY[y] != width) {
                sY = y;
                break;
            }
        }
        // x end
        for (int y = height - 1; y >= 0; y--) {
            if (arrayY[y] != width) {
                sH = y - sY + 1;
                break;
            }
        }
        logger.info("point ({}, {}) , w {}, h {}", sX, sY, sW, sH);
        return new Box(sX, sY, sW, sH);

    }

    private int getGrey(Color rgb) {
        return (rgb.getRed() + rgb.getGreen() + rgb.getBlue()) / 3;
    }

    public BufferedImage cropImage(BufferedImage image, Box box) throws IOException {
        return image.getSubimage(box.x, box.y, box.w, box.h);
    }

    private void writeImage(BufferedImage image, File file) throws IOException {
        ImageIO.write(image, "png", file);
    }

    private String getOutputFileName(File imgFile, String cropType) {
        return String.format("%s\\%s_crop_%s.png", imgFile.getParent(), imgFile.getName(), cropType);
    }

    class Box {
        int x;
        int y;
        int w;
        int h;

        public Box(int x, int y, int w, int h) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }
    }

}
