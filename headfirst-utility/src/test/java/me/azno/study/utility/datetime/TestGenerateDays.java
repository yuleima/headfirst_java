package me.azno.study.utility.datetime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class TestGenerateDays {
    private static final Logger logger = LoggerFactory.getLogger(TestGenerateDays.class);
    private GenerateDays generator;
    private Class<?> claz;

    @Before
    public void before() {
        String writeFile = "D:\\temp\\20180321\\days";
        try {
            claz = (Class<GenerateDays>) Class.forName("me.azno.study.utility.datetime.GenerateDays");
            generator = (GenerateDays) claz.getConstructor(int.class, int.class, String.class)
                    .newInstance(2015, 5, writeFile);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testGetHolidaysMap() {
        Map<String, Integer> map = null;
        try {
            Method method = claz.getDeclaredMethod("getHolidaysMap", null);
            method.setAccessible(true);
            map = (Map<String, Integer>) method.invoke(generator, null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            logger.info("{} {}", entry.getKey(), entry.getValue());
        }

    }

    @Test
    public void testGenerate() {
        generator.generate();
    }

    @After
    public void after() {

    }
}
