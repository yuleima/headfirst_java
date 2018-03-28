package me.azno.study.java8.stream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 使用流做词频统计。
 * 输入，一篇英文文章
 * 输出，单词词频的计数
 * 行流 -> 单词流，flatMap转
 * 过滤符号
 * 分组
 */
public class WordFrequencyCount {
    private static String fileName = "rolling_in_the_deep.txt";
    public static void main(String[] args) {
        File file = new File(getFilePath());
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file);

            bufferedReader = new BufferedReader(fileReader);
            Pattern whitespace = Pattern.compile("\\s+");
            Map<String, Long> wordFrequencies =
                    bufferedReader.lines()
                            .flatMap(s -> whitespace.splitAsStream(s))
                            .map(w -> w.replaceAll("\\pP|\\pS", ""))
                            .filter(w -> !"".equals(w))
                            .collect(Collectors.groupingBy(String::toLowerCase,
                                    Collectors.counting()));
            wordFrequencies.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue))
                    .forEach(System.out::println)
            ;

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

    private static String getFilePath() {
        return WordFrequencyCount.class.getClassLoader().getResource(fileName).getFile();
    }
}
