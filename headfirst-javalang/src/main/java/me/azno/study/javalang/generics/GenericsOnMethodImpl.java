/*
 * Copyright 2018, Zetyun StreamTau All rights reserved.
 */

package me.azno.study.javalang.generics;

public class GenericsOnMethodImpl implements GenericsOnMethod {
    private Object obj;

    @Override
    @SuppressWarnings("unchecked")
    public <T> T get() {
        return (T) obj;
    }

    @Override
    public <T> void set(T tmp) {
        obj = tmp;
    }

    /**
     * main method.
     * @param args args
     */
    public static void main(String[] args) {
        GenericsOnMethod tmp = new GenericsOnMethodImpl();
        String contents = "abc123";
        tmp.set(contents);
        System.out.println(tmp.<String>get());
    }
}
