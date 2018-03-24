package me.azno.study.java8.lambda;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by yulei.ma on 2017/7/7.
 */
public class LambdaExpression {
    public static void main(String[] args) {
        Comparator<Apple> cmpByColor = new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getColor().compareTo(o2.getColor());
            }
        };
        List<Apple> list = Collections.emptyList();
        list.sort(cmpByColor);
        Comparator<Apple> cmpByColorLambda = (o1, o2) -> o1.getColor().compareTo(o2.getColor());
        // 还能进一步简化
        Comparator<Apple> cmpByColorLambdaSimple = Comparator.comparing(Apple::getColor);
        // 定义一个函数
        Function<String, Integer> fLambda = s -> s.length();
        // 定义一个判断
        Predicate<Apple> p = a -> a.getColor().equals("green");
    }
}
