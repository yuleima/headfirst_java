package me.azno.study.datastructure;

import java.util.Arrays;

public class ArrayReversal {
    public static void main(String[] args) {
        // 使用Integer为的是输出方便
        Integer[] array = new Integer[]{1, 2, 3, 4, 5};
        System.out.println(Arrays.asList(array));
        // 索引,临时值
        int revIndex, tmp;
        int arrayLen = array.length;
        for (int i = 0; i < arrayLen / 2; i++) {
            revIndex = arrayLen - i - 1;
            tmp = array[i];
            array[i] = array[revIndex];
            array[revIndex] = tmp;
        }
        System.out.println(Arrays.asList(array));

    }
}
