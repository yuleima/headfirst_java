package me.azno.study.datastructure;

import java.util.Arrays;

public class StringReversal {
    public static void main(String[] args) {
        String string = "123456";
        System.out.println(string);
        String revString1 = reversalString(string);
        System.out.println(revString1);
    }

    private static String reversalString(String string) {
        byte[] bytes = string.getBytes();
        int len = bytes.length;
        byte tmp;
        int index;
        for (int i = 0; i < len/2; i++) {
            index = len -i -1;
            tmp = bytes[i];
            bytes[i] = bytes[index];
            bytes[index] = tmp;
        }
        return new String(bytes);
    }

    private static String reversalString2(String string) {
        byte[] bytes = string.getBytes();
        int len = bytes.length;
        byte[] result = new byte[len];
        // 找中点
        int half = Math.round(len / 2 );
        // 分左右
        byte[] left = Arrays.copyOf(bytes,half);
        byte[] right = Arrays.copyOfRange(bytes,half,len);
        System.arraycopy(right,0,result,0,right.length);
        System.arraycopy(left,0,result,right.length,left.length);

        return new String(result);
    }

    private void temp(){
        StringBuilder sb = new StringBuilder();
        sb.append("abc");
        String s = new String();
        s.substring(1);
    }
}
