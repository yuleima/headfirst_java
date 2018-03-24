package me.azno.study.java8.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yulei.ma on 2017/7/7.
 */
public class FilterApple {
    public static List<Apple> findGreenApple(List<Apple> apples) {
        List<Apple> list = new ArrayList<>();
        for (Apple apple : apples) {
            if ("green".equals(apple.getColor()))
                list.add(apple);
        }
        return list;
    }

    public static List<Apple> findAppleByColor(List<Apple> apples, String color) {
        List<Apple> list = new ArrayList<>();
        for (Apple apple : apples) {
            if (color.equals(apple.getColor()))
                list.add(apple);
        }
        return list;
    }

    public static List<Apple> findAppleByFilter(List<Apple> apples, AppleFilter filter) {
        List<Apple> list = new ArrayList<>();
        for (Apple apple : apples) {
            if (filter.filter(apple))
                list.add(apple);
        }
        return list;
    }

    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(new Apple("green", 150),
                new Apple("green", 140),
                new Apple("red", 160), new Apple("red", 165));
        // 固定功能
        List<Apple> lst = FilterApple.findGreenApple(apples);
        // 单条件等于的筛选
        lst = FilterApple.findAppleByColor(apples, "red");
        // 过滤器筛选，传入（实现）筛选策略的类
        lst = FilterApple.findAppleByFilter(apples, new AppleFilterRedMorethan160());
        // 过滤器筛选，传入匿名筛选策略类
        lst = FilterApple.findAppleByFilter(apples, new AppleFilter() {
            @Override
            public boolean filter(Apple apple) {
                if ("green".equals(apple.getColor()) && apple.getWeight() < 150)
                    return true;
                return false;
            }
        });
        // 过滤器筛选，lambda
        lst = FilterApple.findAppleByFilter(apples, apple -> {
            if ("yellow".equals(apple.getColor()))
                return true;
            return false;
        });
        // Runable 的run方法没参数，没返回值，可以最简化写，()空括号是参数，无返回值不用方法的{}了
        new Thread(() ->
                System.out.println(Thread.currentThread().getName())
        ).start();

        for (Apple apple :
                lst) {
            System.out.println(apple.getColor() + "," + apple.getWeight());

        }
    }

    public interface AppleFilter {
        boolean filter(Apple apple);
    }

    public static class AppleFilterRedMorethan160 implements AppleFilter {

        @Override
        public boolean filter(Apple apple) {
            if ("red".equals(apple.getColor()) && apple.getWeight() > 160)
                return true;
            return false;
        }
    }
}
