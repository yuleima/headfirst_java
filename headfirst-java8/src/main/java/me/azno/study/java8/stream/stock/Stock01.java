package me.azno.study.java8.stream.stock;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Stock01 {
    private static String filePath = "D:\\temp\\py_tmp\\data\\stock_basics_20180328.csv";
    private static String delimiter = ",";

    /**
     * BufferedReader可以通过mark和reset的方式保证每个主题流还可以从头消费
     *
     * @param args
     */
    public static void main(String[] args) {
        File file = new File(filePath);
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file);

            bufferedReader = new BufferedReader(fileReader);
            bufferedReader.mark((int) file.length() + 1);
            // 从文件获得流（每行）
            Stream<String> lines = bufferedReader.lines();
            // 跳过第一行（表头）lines.skip(1)
//            Stream<String> linesSkip = lines.skip(1);
            // 获得流大小（有多少行）
            System.out.println(bufferedReader.lines().skip(1).count());
            // 获得某些字段
//            linesSkip.forEach(System.out::println);
            bufferedReader.reset();
            bufferedReader.lines().skip(1)
                    .map(line -> {
                        String[] array = line.split(delimiter);
                        return array[0] + delimiter + array[1];
                    })
//                    .forEach(System.out::println)
            ;
            bufferedReader.reset();
            System.out.println(bufferedReader.lines().skip(1).map(line -> line.split(delimiter)).count());
            // 方法推导造型 map
            bufferedReader.reset();
            bufferedReader.lines().skip(1)
                    .map(line -> line.split(delimiter)) // 映射 String -> String[]，分字段存放
                    .map(StockBasics::get) // 映射 String[] -> StockBasic
//                    .forEach(System.out::println)
            ;
            // 获得全部分类
            bufferedReader.reset();
            bufferedReader.lines().skip(1)
                    .map(line -> line.split(delimiter))
                    .map(StockBasics::get)
                    .map(stock -> stock.getIndustry()) // 获得分类
                    .distinct() // 去重
//                    .forEach(System.out::println)
            ;
            // 获得分类是互联网的
            bufferedReader.reset();
            bufferedReader.lines().skip(1)
                    .map(line -> line.split(delimiter))
                    .map(StockBasics::get)
                    .filter(stock -> "互联网".equals(stock.getIndustry()))
//                    .forEach(System.out::println)
            ;
            // 在影视音像中按照市值排序
            bufferedReader.reset();
            bufferedReader.lines().skip(1)
                    .map(line -> line.split(delimiter))
                    .map(StockBasics::get)
                    .filter(stock -> "影视音像".equals(stock.getIndustry()))
                    .sorted(Comparator.comparing(StockBasics::getTotalAssets)) // 排序方法
//                    .forEach(System.out::println)
            ;
            // 按分类分组
            bufferedReader.reset();
            Map<String, List<StockBasics>> map = bufferedReader.lines().skip(1)
                    .map(line -> line.split(delimiter))
                    .map(StockBasics::get)
                    .collect(Collectors.groupingBy(StockBasics::getIndustry));
//            for (Map.Entry<String, List<StockBasics>> entry : map.entrySet()) {
//                System.out.println(entry.getValue());
//            }
            // 按分类分组，选出每个分类中市值最高的
            bufferedReader.reset();
            Map<String, ?> mapSB =
                    bufferedReader.lines().skip(1)
                            .map(line -> line.split(delimiter))
                            .map(StockBasics::get)
                            .collect(
                                    Collectors.groupingBy(
                                            StockBasics::getIndustry,
                                    Collectors.maxBy(Comparator.comparing(StockBasics::getTotalAssets))
                                    )
                            );
            for (Map.Entry<String, ?> entry : mapSB.entrySet()) {
                System.out.println(entry.getValue());
            }
            // 多级分类，多级操作嵌套

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
