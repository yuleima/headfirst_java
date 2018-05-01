package me.azno.study.image.util;

public class RGBUtil {
    public static int[] getRGB(int pixel) {
        int[] rgb = new int[3];
        rgb[0] = (pixel & 0xff0000) >> 16;
        rgb[1] = (pixel & 0xff00) >> 8;
        rgb[2] = (pixel & 0xff);
        return rgb;
    }

    public static int rgb2Int(int[] rgb) {
        return rgb[0] << 16 + rgb[1] << 8 + rgb[2];
    }
}
