package me.azno.study.image.tmp;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

// util
public class CropDemo20180602 {
    private static final Logger logger = LoggerFactory.getLogger("test");
    private Gson gson;

    public CropDemo20180602() {
        gson = new Gson();
    }

    public static void main(String[] args) throws Exception {
        CropDemo20180602 crop = new CropDemo20180602();
        String fileName = "20180602025455";
        fileName = "20180527192210";
        String path = "D:\\tmp\\20180531\\demos\\demo_files";
        String filePath = String.format("%s\\%s.jpg", path, fileName);

        String boxString = "";
        boxString = "{\"x\":\"1200\",\"y\":\"210\",\"w\":\"600\",\"h\":\"520\"}";

        String cropType = "_minmap";
        cropType = "lvlupmain_hq";
//        crop.cropImage(filePath, boxString, cropType);
        Map<String, String> mapBox = new HashMap<>();
        // u up, d down, l left, r right
        mapBox.put("u", "{\"x\":\"590\",\"y\":\"846\",\"w\":\"128\",\"h\":\"3\"}");
        mapBox.put("d", "{\"x\":\"590\",\"y\":\"1035\",\"w\":\"128\",\"h\":\"3\"}");
        mapBox.put("l", "{\"x\":\"523\",\"y\":\"890\",\"w\":\"3\",\"h\":\"128\"}");
        mapBox.put("r", "{\"x\":\"1394\",\"y\":\"890\",\"w\":\"3\",\"h\":\"128\"}");
        mapBox.put("x", "{\"x\":\"600\",\"y\":\"950\",\"w\":\"24\",\"h\":\"24\"}");
        crop.cropImage(filePath, mapBox);

        String pointString = "{\"x\":\"600\",\"y\":\"950\"}";
        cropType = "x";
//        crop.check4Point(filePath, pointString, cropType);

        Map<String, String> mapPoint = new HashMap<>();
        // u up, d down, l left, r right
        mapPoint.put("u", "{\"x\":\"590\",\"y\":\"846\"}");
        mapPoint.put("d", "{\"x\":\"590\",\"y\":\"1035\"}");
        mapPoint.put("l", "{\"x\":\"523\",\"y\":\"890\"}");
        mapPoint.put("r", "{\"x\":\"1394\",\"y\":\"890\"}");
//        mapPoint.put("x", "{\"x\":\"600\",\"y\":\"950\"}");
//        crop.check4Point(filePath, mapPoint);

        crop.m1(filePath, mapPoint);


    }

    public void m1(String imgFileName, Map<String, String> map) {
        ImgComputer computer = new ImgComputer(new File(imgFileName));
        X xH = new X();
        X xS = new X();
        X xB = new X();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            Point point = gson.fromJson(entry.getValue(), Point.class);
            HSB hsb = computer.getHSB(point);
            xH.add(hsb.h);
            xS.add(hsb.s);
            xB.add(hsb.b);
        }
        logger.info("H {}", xH);
        logger.info("S {}", xS);
        logger.info("B {}", xB);

    }

    public void check4Point(String imgFileName, Map<String, String> map) throws IOException {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            check4Point(imgFileName, entry.getValue(), entry.getKey());
        }
    }

    public void check4Point(String imgFileName, String pointStr, String cropType) throws IOException {
        File imgFile = new File(imgFileName);
//        String outputFileName = getOutputFileName(imgFile, cropType);
        BufferedImage image = ImageIO.read(imgFile);
//        logger.info("getType {}", image.getType());
        Point point = gson.fromJson(pointStr, Point.class);
        Color color = new Color(image.getRGB(point.x, point.y));
        RGB rgb = new RGB(color.getRed(), color.getGreen(), color.getBlue());
        float[] arrayHSB = new float[3];
        Color.RGBtoHSB(rgb.r, rgb.g, rgb.b, arrayHSB);
        HSB hsb = new HSB(arrayHSB[0], arrayHSB[1], arrayHSB[2]);
        logger.info("{} : {} {}", cropType, gson.toJson(hsb), gson.toJson(rgb));
        // 相似性判断
        // 训练集 最大最小均值

    }

    public void cropImage(String imgFileName, Map<String, String> map) throws IOException {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            cropImage(imgFileName, entry.getValue(), entry.getKey());
        }
    }

    public void cropImage(String imgFileName, String boxString, String cropType) throws IOException {
        File imgFile = new File(imgFileName);
        BufferedImage image = ImageIO.read(imgFile);
        Box box = gson.fromJson(boxString, Box.class);
        BufferedImage subImage = image.getSubimage(box.x, box.y, box.w, box.h);
        String outputFileName = getOutputFileName(imgFile, cropType);
        writeImage(subImage, new File(outputFileName));
    }

    // todo zhiliang of output jpg
    private void writeImage(BufferedImage image, File file) throws IOException {
        ImageIO.write(image, "jpg", file);
//        Iterator<ImageWriter> iterator = ImageIO.getImageWritersByFormatName("jpeg");
//        ImageWriter imageWriter = iterator.next();
//        ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();
//        imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
//        imageWriteParam.setCompressionQuality(1);
//
//        imageWriter.setOutput(file);// wrong
//        IIOImage iio_image = new IIOImage(image, null, null);
//        imageWriter.write(null, iio_image, imageWriteParam);
//        imageWriter.dispose();
    }

    private String getOutputFileName(File imgFile, String cropType) {
        return String.format("%s\\%s_crop_%s.jpg", imgFile.getParent(), imgFile.getName(), cropType);
    }

    // todo use guava simply
    class Box {
        int x;
        int y;
        int w;
        int h;
    }

    class Point {
        int x;
        int y;
    }

    class HSB {
        float h;
        float s;
        float b;

        public HSB(float h, float s, float b) {
            this.h = h;
            this.s = s;
            this.b = b;
        }
    }

    class RGB {
        int r;
        int g;
        int b;

        public RGB(int r, int g, int b) {
            this.r = r;
            this.g = g;
            this.b = b;
        }
    }

    class X {
        float sum;
        float min;
        float max;
        float mean;
        int counts = 0;

        public void add(float f) {
            if (f > max)
                max = f;
            else if (f < min)
                min = f;
            sum += f;
            counts++;
            mean = sum / counts;
        }

        @Override
        public String toString() {
            return "X{" +
                    "min=" + min +
                    ", max=" + max +
                    ", mean=" + mean +
                    '}';
        }
    }

    /**
     * 图像分析器
     */
    class ImgComputer {
        private BufferedImage image;

        public ImgComputer(File file) {
            try {
                image = ImageIO.read(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public RGB getRGB(Point point) {
            Color color = new Color(image.getRGB(point.x, point.y));
            return new RGB(color.getRed(), color.getGreen(), color.getBlue());
        }

        public HSB getHSB(Point point) {
            RGB rgb = getRGB(point);
            float[] arrayHSB = new float[3];
            Color.RGBtoHSB(rgb.r, rgb.g, rgb.b, arrayHSB);
            return new HSB(arrayHSB[0], arrayHSB[1], arrayHSB[2]);
        }
    }
}
