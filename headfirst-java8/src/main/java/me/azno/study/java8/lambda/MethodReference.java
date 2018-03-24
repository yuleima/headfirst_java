package me.azno.study.java8.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by yulei.ma on 2017/7/14.
 */
public class MethodReference {
    public static void main(String[] args) {
        // 方式1
        Consumer<String> consumer = s -> System.out.println(s);
        useConsumer(consumer, "Hello Consumer1");
        // 方式2
        useConsumer(s -> System.out.println(s), "Hello Consumer2");
        // 方式3
        useConsumer(System.out::println, "Hello Consumer3");

        List<Apple> list = Arrays.asList(new Apple("yellow", 150), new Apple("green", 200), new Apple("red", 150));
        System.out.println(list);

        // 比较1
        list.sort((a1, a2) -> a1.getColor().compareTo(a2.getColor()));
        System.out.println(list);

        // 对比较1简化
        list.sort(Comparator.comparing(Apple::getColor));
        System.out.println(list);

        Function<String, Integer> function = Integer::parseInt;
        Integer res = function.apply("123");
        System.out.println(res);

        // 比较1 实例方法
        BiFunction<String, Integer, Character> f1 = String::charAt;
        System.out.println(f1.apply("hello", 2));
        // 比较2 对象方法
        String str = "hello";
        Function<Integer, Character> f2 = str::charAt;
        System.out.println(f2.apply(2));

        // 无参构造函数
        Supplier<String> c1 = String::new;
        System.out.println(c1.get());

        // 双参构造函数
        BiFunction<String, Long, Apple> c2 = Apple::new;
        System.out.println(c2.apply("green", 200l));

        // 三个参数以上的自己定义接口
        MultiParameterSupplier<String, String, Long, Apple> c3 = Apple::new;
        System.out.println(c3.get("appName", "red", 230l));

    }

    private static <T> void useConsumer(Consumer<T> consumer, T t) {
        consumer.accept(t);
        consumer.accept(t);
    }

    @FunctionalInterface
    interface MultiParameterSupplier<T1, T2, T3, R> {
        R get(T1 t1, T2 t2, T3 t3);
    }
}
