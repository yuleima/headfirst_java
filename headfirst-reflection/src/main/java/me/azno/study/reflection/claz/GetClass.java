package me.azno.study.reflection.claz;

public class GetClass {
    /**
     * 本例演示获得Class的对象的三种方法.
     * @param args
     */
    public static void main(String[] args) {
        // 方法一，通过instance的.getClass()方法
        Object obj = new Object();
        Class<?> class1 = obj.getClass();
        // 方法二，通过Class的“静态属性”（只是貌似，实质不是）.class获取
        Class<?> class2 = Object.class;
        // 方法三，通过Class的静态方法.forName获取，常用
        Class<?> class3 = null;
        try {
            class3 = Class.forName("java.lang.Class");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        assert class1 instanceof Class<?>;
        assert class2 instanceof Class<?>;
        assert class3 instanceof Class<?>;
        // 分析
        // Class.class需要在编写代码时就确定Class，编译时必须有
        // Class.forName(String className)的优势，不必在编译时就指定类别，比较灵活
    }
}
