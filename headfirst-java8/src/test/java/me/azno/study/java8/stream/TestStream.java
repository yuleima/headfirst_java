package me.azno.study.java8.stream;

import org.junit.Test;

import java.util.stream.IntStream;

public class TestStream {
    @Test
    public void test() {
        int[] array = new int[]{1, 2, 3, 4};
        IntStream.of(array).map(i -> i > 2 ? i : 2).forEach(System.out::println);
    }
}
