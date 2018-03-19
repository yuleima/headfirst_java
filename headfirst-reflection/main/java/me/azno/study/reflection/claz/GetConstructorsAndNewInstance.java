package me.azno.study.reflection.claz;


import java.lang.reflect.Constructor;

/**
 * 通过reflection获取Constructors.
 * 通过Constructor创建instance.
 */
public class GetConstructorsAndNewInstance {
    public static void main(String[] args) throws Exception {
        Class<?> someOneClass = Class.forName("me.azno.study.reflection.claz.SomeOne");
        // 1. 获取所有的可见Constructors的方法
        Constructor<?>[] constructors = someOneClass.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }
        // 1.1 getConstructors只能获得public的Constructor
        // 1.2 如果要获得所有的Constructor需要使用getDeclaredConstructors()，获取所有声明的Constructors，这里不继续演示了

        // 2. 获取符合Parameter规则的Constructor，getConstructor()的参数是可变参数列表
        // 获取一个Parameter的Constructor
        Constructor<?> singleParamConstructor = someOneClass.getConstructor(String.class);
        // 获取符合Parameter规则的Constructor
        Constructor<?> multipleParamConstructor = someOneClass.getConstructor(String.class, int.class);
        System.out.println(singleParamConstructor);
        System.out.println(multipleParamConstructor);
        // 3. 获取不可见Constructor
        // 获取私有的Constructor
//        Constructor<?> privateConstructor = someOneClass.getConstructor(null);
        // 如果不是getDeclaredConstructor则会报以下Exception，找不到Constructor的异常
        // Exception in thread "main" java.lang.NoSuchMethodException: me.azno.study.reflection.claz.SomeOne.<init>()

        Constructor<?> privateConstructor = someOneClass.getDeclaredConstructor(null);
        // 也可以不写null，.getDeclaredConstructor()不传参是一样的
        System.out.println(privateConstructor);
        // 获取受保护的Constructor
        Constructor<?> protectedConstructor = someOneClass.getDeclaredConstructor(boolean.class);
        System.out.println(protectedConstructor);
        // 4. 使用Constructor instance 构建Class的instance
        // 无参的构建
        privateConstructor.setAccessible(true);
        // 注意，setAccessible(true)在构建时是需要设置的，否则以下异常
        // Exception in thread "main" java.lang.IllegalAccessException: Class me.azno.study.reflection.claz.GetConstructors can not access a member of class me.azno.study.reflection.claz.SomeOne with modifiers "private"
        Object someOneNoParam = privateConstructor.newInstance();
        // 有参的构建
        Object someOneWithParam = multipleParamConstructor.newInstance("someone", 18);
        // protected的不需要设置setAccessible
        System.out.println(someOneNoParam);
        System.out.println(someOneWithParam);
    }
}
