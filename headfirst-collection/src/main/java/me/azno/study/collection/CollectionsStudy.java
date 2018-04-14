package me.azno.study.collection;

import java.util.Collections;
import java.util.List;

public class CollectionsStudy {
    public static void main(String[] args) {
        List<String> list = Collections.singletonList("hello");
        System.out.println(list.size());
        System.out.println(list.get(0));
    }
}
