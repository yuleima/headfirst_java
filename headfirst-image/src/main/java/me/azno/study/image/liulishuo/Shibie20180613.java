package me.azno.study.image.liulishuo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 识别跟读图像
 */
public class Shibie20180613 {
    private static final Logger logger = LoggerFactory.getLogger("test");

    public static void main(String[] args) throws Exception {
        Shibie20180613 demo = new Shibie20180613();
        String path = "D:\\temp\\20180613";
        String fileName = "Screenshot_2018-05-30-07-56-08-613_com.liulishuo.engzo";

        String ext = "png";

        String filePath = String.format("%s\\%s.%s", path, fileName, ext);
        File file = new File(filePath);
        BufferedImage image = ImageIO.read(file);
        demo.shibie(image);

//        BufferedImage cropImg = demo.cropImage(image, box);
//        demo.writeImage(cropImg, new File(demo.getOutputFileName(file, "cut")));
    }

    public void shibie(BufferedImage image) {
        final int match = 255;
        int width = image.getWidth();
        int height = image.getHeight();
        logger.info("width {}, height {}", width, height);
        int startX = 0, startY = 0,
                endX = 0, endY = 0;
        // r=27,g=156,b=253
        Color color00 = new Color(image.getRGB(startX, startY));
        logger.info("{}", color00);
        for (int y = startY; y < height; y++) {
            for (int x = startX; x < width; x++) {
                Color color = new Color(image.getRGB(x,y));
//                logger.info("x {} color {}",x,color);
                if(!color.equals(color00)) {
                    endX = x;
                    logger.info("{}",endX);
                    break;
                }
            }
            endY = y;
            if (endY !=0 && endX == 0) {
                break;
            }
        }
        int w = endX - startX;
        int h = endY - startY;
        logger.info("{}, {}", w,h );


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
