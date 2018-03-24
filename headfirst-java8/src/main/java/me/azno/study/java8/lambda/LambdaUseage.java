package me.azno.study.java8.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

/**
 * Created by yulei.ma on 2017/7/14.
 */
public class LambdaUseage {

    public static void main(String[] args) {
//        Runnable r1 = ()-> System.out.println("Hello");
//        Runnable r2 = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Hello");
//            }
//        };


        List<Apple> list = Arrays.asList(new Apple("green", 200), new Apple("red", 150));
        List<Apple> result = filter(list, apple -> apple.getColor().equals("green"));
        System.out.println(result);
        List<Apple> resultWeight = filterWeight(list, weight -> weight > 160);
        System.out.println(resultWeight);
        System.out.println("--------------");
        handlerConsumer(list, apple -> System.out.println(apple));
        System.out.println("--------------");
        String resultFunc = handlerFunction(new Apple("yellow", 180), apple -> apple.getColor() + "," + apple.getWeight());
        System.out.println(resultFunc);
        IntFunction<Integer> f = i -> i * 100;
        int resultInt = f.apply(10);
        System.out.println(resultInt);

    }

    /**
     * 过滤
     *
     * @param source    过滤源
     * @param predicate 判断条件
     * @return
     */
    private static List<Apple> filter(List<Apple> source, Predicate<Apple> predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple a :
                source) {
            if (predicate.test(a))
                result.add(a);
        }
        return result;
    }

    private static List<Apple> filterWeight(List<Apple> source, LongPredicate predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple a :
                source) {
            if (predicate.test(a.getWeight()))
                result.add(a);
        }
        return result;
    }

    private static void handlerConsumer(List<Apple> source, Consumer<Apple> consumer) {
        for (Apple a :
                source) {
            consumer.accept(a);
        }
    }

    private static String handlerFunction(Apple apple, Function<Apple, String> function) {
        return function.apply(apple);
    }
}
