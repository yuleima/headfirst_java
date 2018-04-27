/*
 * Copyright 2018, Zetyun StreamTau All rights reserved.
 */

package me.azno.study.javalang.generics;

public interface GenericsOnMethod {
    <T> T get();

    <T> void set(T tmp);
}
