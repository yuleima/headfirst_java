package me.azno.study.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Java8 Stream原理深度解析
 * https://www.cnblogs.com/Dorae/p/7779246.html
 */
public class Stream1 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList(
                "bcd", "cde", "def", "abc");
        List<String> result = list.stream()
                //.parallel()
                .filter(e -> e.length() >= 3)
                .map(e -> e.charAt(0))
                //.peek(System.out :: println)
                //.sorted()
                //.peek(e -> System.out.println("++++" + e))
                .map(e -> String.valueOf(e))
                .collect(Collectors.toList());
        System.out.println("----------------------------");
        System.out.println(result);

    }
}
