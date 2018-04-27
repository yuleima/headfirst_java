package me.azno.study.algorithm;

import java.util.Arrays;
import java.util.List;

/**
 * 查找
 */
public class Search {
    public static void main(String[] args) {
        List<Integer> array = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);

    }

    /**
     * 二分查找递归1
     *
     * @return
     */
    static int binarySearchRecurision1(int[] array, int idxLow, int idxHigh, int key) {
        if (idxLow <= idxHigh) {
            int idxMid = (idxLow + idxHigh) / 2;
            if (key == array[idxMid])
                return idxMid;
            if (key < array[idxMid])
                return binarySearchRecurision1(array, idxLow, idxMid - 1, key);
            if (key > array[idxMid])
                return binarySearchRecurision1(array, idxMid + 1, idxHigh, key);
        }
        return -1;
    }

    /**
     * 二分查找非递归1
     *
     * @param array
     * @param size
     * @param key
     * @return
     */
    static int binarySearchNoRecurision1(int[] array, int size, int key) {
        int idxLow = 0, idxHigh = size - 1, idxMid;
        while (idxLow <= idxHigh) {
            idxMid = (idxLow + idxHigh) / 2;
            if (key == array[idxMid])
                return idxMid;
            if (key < array[idxMid])
                idxHigh = idxMid - 1;
            if (key > array[idxMid])
                idxLow = idxMid + 1;
        }
        return -1;
    }
}
