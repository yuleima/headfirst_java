package me.azno.study.image.tmp;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class CropDemo {
    private static final Logger logger = LoggerFactory.getLogger("test");

    public static void main(String[] args) throws Exception {
        String fileName = "20180602025455";
        fileName = "20180527155615";
        fileName = "20180527192210";
        String path = "D:\\tmp\\20180531\\demos\\demo_files";

        String filePath = String.format("%s\\%s.jpg", path, fileName);
        File file = new File(filePath);
        BufferedImage image = ImageIO.read(file);
        String boxString = "";
        // bottom outer
        boxString = "{\"point\":{\"x\":\"520\",\"y\":\"845\"},\"dimension\":{\"width\":\"880\",\"height\":\"195\"}}";
        // bottom inner
        boxString = "{\"point\":{\"x\":\"550\",\"y\":\"870\"},\"dimension\":{\"width\":\"820\",\"height\":\"140\"}}";
        // min map
        boxString = "{\"point\":{\"x\":\"90\",\"y\":\"760\"},\"dimension\":{\"width\":\"270\",\"height\":\"270\"}}";
        // lvl up main
        boxString = "{\"point\":{\"x\":\"1200\",\"y\":\"210\"},\"dimension\":{\"width\":\"600\",\"height\":\"520\"}}";
        Gson gson = new Gson();
        Box box = gson.fromJson(boxString, Box.class);
//        logger.info("{}", box);

        BufferedImage bufferedImage = image.getSubimage(box.point.x, box.point.y, box.dimension.width, box.dimension.height);
        String ext = "_minmap";
        ext = "_lvlupmain";
        String outputFileName = String.format("%s\\%s_crop%s.jpg", path, fileName, ext);
        // todo zhiliang of output jpg
        ImageIO.write(bufferedImage, "jpg", new File(outputFileName));
    }

    // todo use guava simply
    class Point {
        int x;
        int y;

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    class Dimension {
        int width;
        int height;

        @Override
        public String toString() {
            return "Dimension{" +
                    "width=" + width +
                    ", height=" + height +
                    '}';
        }
    }

    class Box {
        Point point;
        Dimension dimension;

        @Override
        public String toString() {
            return "Box{" +
                    "point=" + point +
                    ", demension=" + dimension +
                    '}';
        }
    }
}
