package me.azno.study.java8.stream;

import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * Created by leigh on 2017-07-16.
 */
public class SimpleStream {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH) );
        List<String> dishNamesByCollections = getDishNamesByCollections(menu);
        System.out.println(dishNamesByCollections);

        List<String> dishNamesByStream = getDishNamesByStream(menu);
        System.out.println(dishNamesByStream);
    }

    private static List<String> getDishNamesByStream(List<Dish> menu) {
        return menu.stream().filter(d->d.getCalories() < 400).sorted(comparing(Dish::getCalories))
                .map(Dish::getName).collect(toList());
        // toList()是collecter的工具
    }

    private static List<String> getDishNamesByCollections(List<Dish> menu) {
        List<Dish> lowColaies = new ArrayList<>();
        for (Dish d:
             menu) {
            if(d.getCalories() < 400)
                lowColaies.add(d);

        }
        Collections.sort(lowColaies, Comparator.comparingInt(Dish::getCalories));
        List<String> dishNameList = new ArrayList<>();
        for (Dish d:
                lowColaies) {
            dishNameList.add(d.getName());

        }
        return dishNameList;
    }
}
