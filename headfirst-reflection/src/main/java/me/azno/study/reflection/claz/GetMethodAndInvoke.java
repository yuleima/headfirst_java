package me.azno.study.reflection.claz;

import java.lang.reflect.Method;

public class GetMethodAndInvoke {
    public static void main(String[] args) throws Exception {
        // 获得类的所有方法
        // 获得指定方法
        // 获得set get 方法
        // 获得不可见方法

        Class<?> mainMethodClass = Class.forName("me.azno.study.reflection.claz.MainSayHello");
        // 获得静态方法
        Method staticMethod = mainMethodClass.getMethod("staticAddMethod", int.class, int.class);
        Object result = staticMethod.invoke(null, 1, 2);
        System.out.println(result);
        // 注意，在执行静态方法时，obj参数置空
        // last. get main method and invoke

        Method mainMethod = mainMethodClass.getMethod("main", String[].class);
        mainMethod.invoke(null, (Object) new String[0]);
        // 注意，在执行静态方法时，obj参数置空
        // 如果写成mainMethod.invoke(null, new String[0]);会报以下异常，原因是可变参数列表
        // Exception in thread "main" java.lang.IllegalArgumentException: wrong number of arguments
    }
}
