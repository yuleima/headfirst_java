package me.azno.study.reflection.claz;

public class SomeOne {
    private String name;
    private int age;

    public SomeOne(String name) {
        this.name = name;
        System.out.println("single parameter Constructor");
    }

    public SomeOne(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("multiple parameter Constructor");
    }

    protected SomeOne(boolean n) {
        System.out.println("protected Constructor");
    }

    private SomeOne() {
        System.out.println("private Constructor");
    }

    @Override
    public String toString() {
        return "SomeOne{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
