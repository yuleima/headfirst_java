package me.azno.study.reflection.genericparadigm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ParameterizedTypeDemo<T, S> {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public ParameterizedTypeDemo() {
        // me.azno.study.reflection.genericparadigm.ParameterizedTypeDemo
        Class cls = getClass();
        // java.lang.Object
        Type type = cls.getGenericSuperclass();
        logger.info("{}", cls);
        logger.info("{}", type);
        Type trueType = ((ParameterizedType) type).getActualTypeArguments()[0];
//        logger.info("{} ", trueType);
    }

    public static void main(String[] args) {
        ParameterizedTypeDemo demo = new ParameterizedTypeDemo();
//        demo.param();
    }

    public void param() {
        // me.azno.study.reflection.genericparadigm.ParameterizedTypeDemo
        Class cls = getClass();
        // java.lang.Object
        Type type = cls.getGenericSuperclass();
        logger.info("{}", cls);
        logger.info("{}", type);
        Type trueType = ((ParameterizedType) type).getActualTypeArguments()[0];
        logger.info("{} ", trueType);
    }
}
