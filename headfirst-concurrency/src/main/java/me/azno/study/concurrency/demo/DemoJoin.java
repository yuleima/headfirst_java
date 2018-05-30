package me.azno.study.concurrency.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;

public class DemoJoin {
    private static final Logger logger = LoggerFactory.getLogger(DemoJoin.class);

    public static void main(String[] args) {
        Runnable runnable = () -> IntStream.range(1, 1000).forEach(i -> logger.info("{} -> {}", Thread.currentThread().getName(), i));
        Thread t1 = new Thread(runnable);
        t1.start();
        Thread t2 = new Thread(runnable);
        t2.start();
    }
}
