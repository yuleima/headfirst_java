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
public class Shibie20180614 {
    private static final Logger logger = LoggerFactory.getLogger("test");

    public static void main(String[] args) throws Exception {
        Shibie20180614 demo = new Shibie20180614();
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
        int width = image.getWidth();
        int height = image.getHeight();
        logger.info("width {}, height {}", width, height);
        int[] outerBorderStartPixel = {0, 0};
        int[] outerBorderEndPixel = {width - 1, height - 1};
        int[] innerBorderStartPixel = {0, 0};
        int[] innerBorderEndPixel = {0, 0};

        outerBorderEndPixel[1] = 30;

        Pixel pixel = new Pixel(0,0,0);


        // r=27,g=156,b=253 -14967555
        int rgbStrip = image.getRGB(outerBorderStartPixel[0], outerBorderStartPixel[1]);
        // r=29,g=42,b=66 -14865854
        int rgbBackground = image.getRGB(outerBorderEndPixel[0], outerBorderEndPixel[1]);
        int rgbLast = 0;
        logger.info("({},{}):{},{},({},{}):{},{}",
                outerBorderStartPixel[0], outerBorderStartPixel[1], rgbStrip, new Color(rgbStrip),
                outerBorderEndPixel[0], outerBorderEndPixel[1], rgbBackground, new Color(rgbBackground)
        );
        for (int y = outerBorderStartPixel[1]; y < outerBorderEndPixel[1]; y++) {
            for (int x = outerBorderStartPixel[0]; x < outerBorderEndPixel[0]; x++) {
                int rgb = image.getRGB(x, y);
                if (rgb == rgbStrip && rgbLast == 0) {
                    innerBorderStartPixel[0] = x;
                    innerBorderStartPixel[1] = y;
                }
                if (rgb != rgbStrip && rgbLast == rgbStrip) {
                    innerBorderEndPixel[0] = x;
                    innerBorderEndPixel[1] = y;
                }
                rgbLast = rgb;
            }
        }
        int w = innerBorderEndPixel[0] - innerBorderStartPixel[0];
        int h = innerBorderEndPixel[1] - innerBorderStartPixel[1];
        logger.info("{}, {}", w, h);
        logger.info("{}",new Color(image.getRGB(innerBorderEndPixel[0],innerBorderEndPixel[1])));


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
